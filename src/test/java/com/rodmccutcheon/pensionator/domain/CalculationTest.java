package com.rodmccutcheon.pensionator.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculationTest {

    private Calculation calculation;

    @Before
    public void setUp() throws Exception {
        AssetType homeContents = new AssetType("Home contents", false);
        AssetType vehicle = new AssetType("Vehicle", false);
        AssetType shares = new AssetType("Shares", true);
        AssetType managedFunds = new AssetType("Managed funds", true);
        List<Asset> assets = new ArrayList<>(Arrays.asList(
                new Asset(homeContents, "Centrelink value", BigDecimal.valueOf(5_000)),
                new Asset(vehicle, "2001 Honda CRV", BigDecimal.valueOf(4_000)),
                new Asset(shares, "CBA shares", BigDecimal.valueOf(300_000)),
                new Asset(managedFunds, "Magellan Global Fund", BigDecimal.valueOf(250_200))
        ));

        IncomeStreamType accountBasedPension = new IncomeStreamType("Account based pension");
        IncomeStreamType annuity = new IncomeStreamType("Annuity");
        List<IncomeStream> incomeStreams = new ArrayList<>(Arrays.asList(
                new IncomeStream(accountBasedPension, "Russell ABP", BigDecimal.valueOf(4_250)),
                new IncomeStream(annuity, "Challenger Lifetime Annuity", BigDecimal.valueOf(2_800))
        ));

        calculation = new Calculation();
        calculation.setAssets(assets);
        calculation.setIncomeStreams(incomeStreams);
    }

    @Test
    public void getTotalAssets_shouldSumTotalAssets() {
        assertEquals(BigDecimal.valueOf(559_200), calculation.getTotalAssets());
    }

    @Test
    public void getRegularIncome_shouldSumRegularIncome() {
        assertEquals(BigDecimal.valueOf(7_050), calculation.getRegularIncome());
    }

    @Test
    public void getDeemableAssets_shouldSumDeemableAssets() {
        assertEquals(BigDecimal.valueOf(550_200), calculation.getDeemableAssets());
    }
}