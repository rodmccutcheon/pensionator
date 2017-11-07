package com.rodmccutcheon.pensionator.domain;

import java.math.BigDecimal;

public class DeemedIncomeRow {

    private BigDecimal deemableAmount;
    private BigDecimal deemingRate;
    private BigDecimal deemedIncome;

    public DeemedIncomeRow() { }

    public DeemedIncomeRow(BigDecimal deemableAmount, BigDecimal deemingRate, BigDecimal deemedIncome) {
        this.deemableAmount = deemableAmount;
        this.deemingRate = deemingRate;
        this.deemedIncome = deemedIncome;
    }

    public BigDecimal getDeemableAmount() {
        return deemableAmount;
    }

    public void setDeemableAmount(BigDecimal deemableAmount) {
        this.deemableAmount = deemableAmount;
    }

    public BigDecimal getDeemingRate() {
        return deemingRate;
    }

    public void setDeemingRate(BigDecimal deemingRate) {
        this.deemingRate = deemingRate;
    }

    public BigDecimal getDeemedIncome() {
        return deemedIncome;
    }

    public void setDeemedIncome(BigDecimal deemedIncome) {
        this.deemedIncome = deemedIncome;
    }
}
