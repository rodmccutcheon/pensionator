package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "payment_rate_groups")
public class PaymentRateGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_rate_group_id")
    private List<PaymentRate> paymentRates;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<PaymentRate> getPaymentRates() {
        return paymentRates;
    }

    public void setPaymentRates(List<PaymentRate> paymentRates) {
        this.paymentRates = paymentRates;
    }

    public PaymentRate getPaymentRateByRelationshipStatus(RelationshipStatus relationshipStatus) {
        return paymentRates
                .stream()
                .filter(i -> i.getRelationshipStatus().equals(relationshipStatus))
                .findFirst()
                .get();
    }
}
