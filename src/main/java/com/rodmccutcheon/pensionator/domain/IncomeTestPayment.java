package com.rodmccutcheon.pensionator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "income_test_payments")
public class IncomeTestPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @JsonIgnore
    private BigDecimal maximumPayment;

    @JsonIgnore
    private BigDecimal assessableIncome;

    @JsonIgnore
    private BigDecimal threshold;

    @JsonIgnore
    private BigDecimal excessIncome;

    @JsonIgnore
    private BigDecimal paymentReduction;

    @JsonProperty("incomeTestPayment")
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
