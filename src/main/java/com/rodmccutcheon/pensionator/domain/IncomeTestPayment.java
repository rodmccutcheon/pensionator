package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "income_test_payments")
public class IncomeTestPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal maximumPayment;

    private BigDecimal assessableIncome;

    private BigDecimal threshold;

    private BigDecimal excessIncome;

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

    public BigDecimal getAssessableIncome() {
        return assessableIncome;
    }

    public void setAssessableIncome(BigDecimal assessableIncome) {
        this.assessableIncome = assessableIncome;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getExcessIncome() {
        return excessIncome;
    }

    public void setExcessIncome(BigDecimal excessIncome) {
        this.excessIncome = excessIncome;
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
