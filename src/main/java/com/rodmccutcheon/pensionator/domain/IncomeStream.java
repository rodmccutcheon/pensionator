package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "income_streams")
public class IncomeStream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "income_stream_type_id", nullable = false)
    @NotNull
    private IncomeStreamType incomeStreamType;

    @NotNull
    private String description;

    private BigDecimal deductibleAmount;

    private BigDecimal annualPayment;

    private BigDecimal currentBalance;

    private BigDecimal assessedIncome;

    public IncomeStream() { }

    public IncomeStream(IncomeStreamType incomeStreamType, String description, BigDecimal deductibleAmount, BigDecimal annualPayment) {
        this.incomeStreamType = incomeStreamType;
        this.description = description;
        this.deductibleAmount = deductibleAmount;
        this.annualPayment = annualPayment;
    }

    public IncomeStream(IncomeStreamType incomeStreamType, String description, BigDecimal currentBalance) {
        this.incomeStreamType = incomeStreamType;
        this.description = description;
        this.currentBalance = currentBalance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IncomeStreamType getIncomeStreamType() {
        return incomeStreamType;
    }

    public void setIncomeStreamType(IncomeStreamType incomeStreamType) {
        this.incomeStreamType = incomeStreamType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(BigDecimal deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public BigDecimal getAnnualPayment() {
        return annualPayment;
    }

    public void setAnnualPayment(BigDecimal annualPayment) {
        this.annualPayment = annualPayment;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getAssessedIncome() {
        return assessedIncome;
    }

    public void setAssessedIncome(BigDecimal assessedIncome) {
        this.assessedIncome = assessedIncome;
    }

    public BigDecimal calculateAssessedIncome() {
        if (incomeStreamType.getId() == 2) {
            assessedIncome = annualPayment.compareTo(deductibleAmount) > 0 ? annualPayment.subtract(deductibleAmount) : BigDecimal.ZERO;
        } else if (incomeStreamType.getId() == 3) {
            assessedIncome = BigDecimal.ZERO;
        }
        return assessedIncome;

    }
}
