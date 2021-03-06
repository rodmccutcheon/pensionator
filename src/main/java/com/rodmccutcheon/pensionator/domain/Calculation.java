package com.rodmccutcheon.pensionator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rodmccutcheon.pensionator.domain.exceptions.CalculationException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calculations")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private BigDecimal payment;

    @JsonIgnore
    private String applicableTest;

    @JsonIgnore
    private String comment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "calculation_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Asset> assets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "calculation_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<IncomeStream> incomeStreams;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JsonUnwrapped
    private AssetsTestPayment assetsTestPayment;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonUnwrapped
    private IncomeTestPayment incomeTestPayment;

    @Transient
    @JsonIgnore
    private DeemedIncome deemedIncome;

    public Calculation() {
        assets = new ArrayList<>();
        incomeStreams = new ArrayList<>();
        deemedIncome = new DeemedIncome();
        deemedIncome.setDeemedIncomeRows(new ArrayList<DeemedIncomeRow>());
    }

    public Calculation(Date date, List<Asset> assets, List<IncomeStream> incomeStreams, String comment) {
        this.date = date;
        this.assets = assets;
        this.incomeStreams = incomeStreams;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getApplicableTest() {
        return applicableTest;
    }

    public void setApplicableTest(String applicableTest) {
        this.applicableTest = applicableTest;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Asset> getAssets() {
        if (assets == null) {
            assets = new ArrayList<>();
        }
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<IncomeStream> getIncomeStreams() {
        if (incomeStreams == null) {
            incomeStreams = new ArrayList<>();
        }
        return incomeStreams;
    }

    public void setIncomeStreams(List<IncomeStream> incomeStreams) {
        this.incomeStreams = incomeStreams;
    }

    public AssetsTestPayment getAssetsTestPayment() {
        return assetsTestPayment;
    }

    public void setAssetsTestPayment(AssetsTestPayment assetsTestPayment) {
        this.assetsTestPayment = assetsTestPayment;
    }

    public IncomeTestPayment getIncomeTestPayment() {
        return incomeTestPayment;
    }

    public void setIncomeTestPayment(IncomeTestPayment incomeTestPayment) {
        this.incomeTestPayment = incomeTestPayment;
    }

    public DeemedIncome getDeemedIncome() {
        return deemedIncome;
    }

    public void setDeemedIncome(DeemedIncome deemedIncome) {
        this.deemedIncome = deemedIncome;
    }

    @JsonIgnore
    public BigDecimal getTotalAssets() {
        return assets.stream().map(Asset::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public BigDecimal getRegularIncome() {
        return incomeStreams
                .stream()
                .map(IncomeStream::calculateAssessedIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Financial assets are deemed to earn income.
    @JsonIgnore
    public BigDecimal getDeemableAssets() {
        return assets
                .stream()
                .filter(a -> a.getAssetType().isDeemed())
                .map(Asset::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public BigDecimal getDeemedIncome(DeemingRateGroup deemingRateGroup) {
        List<DeemingRate> deemingRates = deemingRateGroup.getDeemingRatesByRelationshipStatus(client.getRelationshipStatus());
        BigDecimal deemableAssets = getDeemableAssets();
        BigDecimal deemedIncome = BigDecimal.ZERO;
        for (DeemingRate deemingRate : deemingRates) {
            if (deemableAssets.compareTo(deemingRate.getThresholdStart()) > 0) {
                if (deemingRate.getThresholdEnd() != null && deemableAssets.compareTo(deemingRate.getThresholdEnd()) > 0) {
                    deemedIncome = deemedIncome.add(deemingRate.getThresholdEnd()
                                                    .subtract(deemingRate.getThresholdStart())
                                                    .multiply(deemingRate.getDeemingRate())
                                                    .divide(BigDecimal.valueOf(100)));
                } else {
                    deemedIncome = deemedIncome.add(deemableAssets
                                                    .subtract(deemingRate.getThresholdStart())
                                                    .multiply(deemingRate.getDeemingRate())
                                                    .divide(BigDecimal.valueOf(100)));
                }
            }
        }

        return deemedIncome;
    }

    public BigDecimal getTotalIncome(DeemingRateGroup deemingRateGroup) {
        return getRegularIncome().add(getDeemedIncome(deemingRateGroup));
    }

    public void calculatePayment(IncomeTestThresholdGroup incomeTestThresholdGroup,
                                 AssetsTestThresholdGroup assetsTestThresholdGroup,
                                 DeemingRateGroup deemingRateGroup,
                                 PaymentRateGroup paymentRateGroup) throws CalculationException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyy");
        if (incomeTestThresholdGroup == null) {
            throw new CalculationException("Missing reference data: income test threshold for date: "
                    + simpleDateFormat.format(date)
                    + ". Please change the calculation date, or enter the relevant reference values.");
        }
        if (assetsTestThresholdGroup == null) {
            throw new CalculationException("Missing reference data: assets test threshold for date: "
                    + simpleDateFormat.format(date)
                    + ". Please change the calculation date, or enter the relevant reference values.");
        }
        if (deemingRateGroup == null) {
            throw new CalculationException("Missing reference data: deeming rates for date: "
                    + simpleDateFormat.format(date)
                    + ". Please change the calculation date, or enter the relevant reference values.");
        }
        if (paymentRateGroup == null) {
            throw new CalculationException("Missing reference data: payment rates for date: "
                    + simpleDateFormat.format(date)
                    + ". Please change the calculation date, or enter the relevant reference values.");
        }

        calculateAssetsTestPayment(assetsTestThresholdGroup, paymentRateGroup);
        calculateIncomeTestPayment(incomeTestThresholdGroup, deemingRateGroup, paymentRateGroup);

        // The test the results in the lower payment applies
        if (assetsTestPayment.getPayment().compareTo(incomeTestPayment.getPayment()) < 0) {
            applicableTest = "Assets Test";
            payment = assetsTestPayment.getPayment();
        } else {
            applicableTest = "Income Test";
            payment = incomeTestPayment.getPayment();
        }
    }

    public void calculateAssetsTestPayment(AssetsTestThresholdGroup assetsTestThresholdGroup,
                                          PaymentRateGroup paymentRateGroup) {

        assetsTestPayment = new AssetsTestPayment();

        PaymentRate paymentRate = paymentRateGroup.getPaymentRateByRelationshipStatus(client.getRelationshipStatus());
        BigDecimal maximumPayment = paymentRate.getTotalPayment();
        assetsTestPayment.setMaximumPayment(maximumPayment);

        AssetsTestThreshold assetsTestThreshold = assetsTestThresholdGroup.getAssetsTestThresholdByRelationshipStatusAndHomeownerStatus(
                client.getRelationshipStatus(),
                client.getHomeownerStatus());
        assetsTestPayment.setThreshold(assetsTestThreshold.getThreshold());

        BigDecimal totalAssets = getTotalAssets();
        assetsTestPayment.setAssessableAssets(totalAssets);

        // If the total assets value is less than the assets threshold, then the client is eligible for the maximum payment.
        if (totalAssets.compareTo(assetsTestThreshold.getThreshold()) < 1) {
            assetsTestPayment.setExcessAssets(BigDecimal.ZERO);
            assetsTestPayment.setPaymentReduction(BigDecimal.ZERO);
            assetsTestPayment.setPayment(maximumPayment);
        } else {
            BigDecimal excessAssets = totalAssets.subtract(assetsTestThreshold.getThreshold());
            assetsTestPayment.setExcessAssets(excessAssets);
            BigDecimal paymentReduction = excessAssets.divide(BigDecimal.valueOf(1000)).multiply(assetsTestThreshold.getReductionRate());
            assetsTestPayment.setPaymentReduction(paymentReduction);
            BigDecimal payment = maximumPayment.subtract(paymentReduction);
            assetsTestPayment.setPayment(payment.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payment);
        }
    }

    public void calculateIncomeTestPayment(IncomeTestThresholdGroup incomeTestThresholdGroup,
                                          DeemingRateGroup deemingRateGroup,
                                          PaymentRateGroup paymentRateGroup) {

        incomeTestPayment = new IncomeTestPayment();

        PaymentRate paymentRate = paymentRateGroup.getPaymentRateByRelationshipStatus(client.getRelationshipStatus());
        BigDecimal maximumPayment = paymentRate.getTotalPayment();
        incomeTestPayment.setMaximumPayment(maximumPayment);

        IncomeTestThreshold incomeTestThreshold = incomeTestThresholdGroup.getIncomeTestThresholdByRelationshipStatus(
                client.getRelationshipStatus());
        incomeTestPayment.setThreshold(incomeTestThreshold.getThreshold());
        List<DeemingRate> deemingRates = deemingRateGroup.getDeemingRatesByRelationshipStatus(client.getRelationshipStatus());
        BigDecimal deemableAssets = getDeemableAssets();
        for (DeemingRate deemingRate : deemingRates) {
            if (deemableAssets.compareTo(deemingRate.getThresholdStart()) > 0) {
                if (deemingRate.getThresholdEnd() != null && deemableAssets.compareTo(deemingRate.getThresholdEnd()) > 0) {

                    DeemedIncomeRow d = new DeemedIncomeRow();
                    BigDecimal deemableAmount = deemingRate.getThresholdEnd().subtract(deemingRate.getThresholdStart());

                    d.setDeemableAmount(deemableAmount);
                    d.setDeemingRate(deemingRate.getDeemingRate());
                    d.setDeemedIncome(deemableAmount.multiply(deemingRate.getDeemingRate().divide(BigDecimal.valueOf(100))));
                    deemedIncome.getDeemedIncomeRows().add(d);
                } else {
                    DeemedIncomeRow d = new DeemedIncomeRow();
                    BigDecimal deemableAmount = deemableAssets.subtract(deemingRate.getThresholdStart());

                    d.setDeemableAmount(deemableAmount);
                    d.setDeemingRate(deemingRate.getDeemingRate());
                    d.setDeemedIncome(deemableAmount.multiply(deemingRate.getDeemingRate().divide(BigDecimal.valueOf(100))));
                    deemedIncome.getDeemedIncomeRows().add(d);
                }
            }
        }

        BigDecimal totalIncome = getTotalIncome(deemingRateGroup);
        incomeTestPayment.setAssessableIncome(totalIncome);

        if (totalIncome.divide(BigDecimal.valueOf(26), 2, BigDecimal.ROUND_HALF_UP).compareTo(incomeTestThreshold.getThreshold()) < 1) {
            incomeTestPayment.setExcessIncome(BigDecimal.ZERO);
            incomeTestPayment.setPaymentReduction(BigDecimal.ZERO);
            incomeTestPayment.setPayment(maximumPayment);
        } else {
            BigDecimal excessIncome = totalIncome.divide(BigDecimal.valueOf(26), 2, BigDecimal.ROUND_HALF_UP).subtract(incomeTestThreshold.getThreshold());
            incomeTestPayment.setExcessIncome(excessIncome);
            BigDecimal paymentReduction = excessIncome.multiply(incomeTestThreshold.getReductionRate());
            incomeTestPayment.setPaymentReduction(paymentReduction);
            BigDecimal payment = maximumPayment.subtract(paymentReduction);
            incomeTestPayment.setPayment(payment.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : payment);
        }
    }
}
