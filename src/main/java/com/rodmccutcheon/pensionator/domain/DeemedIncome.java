package com.rodmccutcheon.pensionator.domain;

import java.math.BigDecimal;
import java.util.List;

public class DeemedIncome {

    private List<DeemedIncomeRow> deemedIncomeRows;

    class DeemedIncomeRow {
        BigDecimal amount;
        BigDecimal deemingRate;
        BigDecimal deemedIncome;

        DeemedIncomeRow(BigDecimal amount, BigDecimal deemingRate, BigDecimal deemedIncome) {
            this.amount = amount;
            this.deemingRate = deemingRate;
            this.deemedIncome = deemedIncome;
        }
    }

}
