package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "assets_test_payments")
public class AssetsTestPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal maximumPayment;

    private BigDecimal assessableAssets;

    private BigDecimal threshold;

    private BigDecimal excessAssets;

    private BigDecimal paymentReduction;

    private BigDecimal payment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getMaximumPayment() {
        return maximumPayment;
    }

    public void setMaximumPayment(BigDecimal maximumPayment) {
        this.maximumPayment = maximumPayment;
    }

    public BigDecimal getAssessableAssets() {
        return assessableAssets;
    }

    public void setAssessableAssets(BigDecimal assessableAssets) {
        this.assessableAssets = assessableAssets;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getExcessAssets() {
        return excessAssets;
    }

    public void setExcessAssets(BigDecimal excessAssets) {
        this.excessAssets = excessAssets;
    }

    public BigDecimal getPaymentReduction() {
        return paymentReduction;
    }

    public void setPaymentReduction(BigDecimal paymentReduction) {
        this.paymentReduction = paymentReduction;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
}
