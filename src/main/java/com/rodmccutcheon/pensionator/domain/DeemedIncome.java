package com.rodmccutcheon.pensionator.domain;

import java.math.BigDecimal;
import java.util.List;

public class DeemedIncome {

    private List<DeemedIncomeRow> deemedIncomeRows;

    public List<DeemedIncomeRow> getDeemedIncomeRows() {
        return deemedIncomeRows;
    }

    public void setDeemedIncomeRows(List<DeemedIncomeRow> deemedIncomeRows) {
        this.deemedIncomeRows = deemedIncomeRows;
    }

    public BigDecimal getTotal() {
        return deemedIncomeRows
                .stream()
                .map(DeemedIncomeRow::getDeemedIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
