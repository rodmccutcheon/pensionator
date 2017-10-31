package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calculations")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private BigDecimal payment;

    private String applicableTest;

    private String comment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "calculation_id")
    private List<Asset> assets;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "calculation_id")
    private List<IncomeStream> incomeStreams;

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
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<IncomeStream> getIncomeStreams() {
        return incomeStreams;
    }

    public void setIncomeStreams(List<IncomeStream> incomeStreams) {
        this.incomeStreams = incomeStreams;
    }

    public BigDecimal getTotalAssets() {
        return assets.stream().map(Asset::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getRegularIncome() {
        return incomeStreams
                .stream()
                .map(IncomeStream::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Financial assets are deemed to earn income.
    public BigDecimal getDeemableAssets() {
        return assets
                .stream()
                .filter(a -> a.getAssetType().isDeemed())
                .map(Asset::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

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
                                 PaymentRateGroup paymentRateGroup) {

        BigDecimal assetsTestPayment = calculateAssetsTestPayment(assetsTestThresholdGroup, paymentRateGroup);
        BigDecimal incomeTestPayment = calculateIncomeTestPayment(incomeTestThresholdGroup, deemingRateGroup, paymentRateGroup);

        // The test the results in the lower payment applies
        if (assetsTestPayment.compareTo(incomeTestPayment) < 0) {
            applicableTest = "Assets Test";
            payment = assetsTestPayment;
        } else {
            applicableTest = "Income Test";
            payment = incomeTestPayment;
        }
    }

    public BigDecimal calculateAssetsTestPayment(AssetsTestThresholdGroup assetsTestThresholdGroup,
                                          PaymentRateGroup paymentRateGroup) {

        PaymentRate paymentRate = paymentRateGroup.getPaymentRateByRelationshipStatus(client.getRelationshipStatus());
        AssetsTestThreshold assetsTestThreshold = assetsTestThresholdGroup.getAssetsTestThresholdByRelationshipStatusAndHomeownerStatus(
                client.getRelationshipStatus(),
                client.getHomeownerStatus());

        if (getTotalAssets().compareTo(assetsTestThreshold.getThreshold()) < 1) {
            return paymentRate.getTotalPayment();
        } else {
            BigDecimal thousandsOverThreshold = getTotalAssets().subtract(assetsTestThreshold.getThreshold()).divide(BigDecimal.valueOf(1000));
            double reductionRate = 3.0;
            return paymentRate.getTotalPayment().subtract(thousandsOverThreshold.multiply(BigDecimal.valueOf(reductionRate)));
        }
    }

    public BigDecimal calculateIncomeTestPayment(IncomeTestThresholdGroup incomeTestThresholdGroup,
                                          DeemingRateGroup deemingRateGroup,
                                          PaymentRateGroup paymentRateGroup) {

        PaymentRate paymentRate = paymentRateGroup.getPaymentRateByRelationshipStatus(client.getRelationshipStatus());
        List<IncomeTestThreshold> incomeTestThresholds = incomeTestThresholdGroup.getIncomeTestThresholdsByRelationshipStatus(
                client.getRelationshipStatus());
        List<DeemingRate> deemingRates = deemingRateGroup.getDeemingRatesByRelationshipStatus(client.getRelationshipStatus());

        getTotalIncome(deemingRateGroup);

        return BigDecimal.TEN;
    }
}
