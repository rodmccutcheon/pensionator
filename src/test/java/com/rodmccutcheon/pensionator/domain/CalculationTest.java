package com.rodmccutcheon.pensionator.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

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
    public void getTotalAssets_whenNoAssets_shouldReturnZero() {
        Calculation calculation = new Calculation();
        calculation.setAssets(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, calculation.getTotalAssets());
    }

    @Test
    public void getTotalAssets_shouldSumTotalAssets() {
        assertEquals(BigDecimal.valueOf(559_200), calculation.getTotalAssets());
    }

    @Test
    public void givenACalculationWithNoIncomeStreams_whenSummingRegularIncome_thenZero() {
        Calculation calculation = new Calculation();
        calculation.setIncomeStreams(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, calculation.getRegularIncome());
    }

    @Test
    @Ignore
    public void getRegularIncome_shouldSumRegularIncome() {
        assertEquals(BigDecimal.valueOf(7_050), calculation.getRegularIncome());
    }

    @Test
    public void getDeemableAssets_whenNoDeemableAssets_shouldReturnZero() {
        Calculation calculation = new Calculation();
        calculation.setAssets(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, calculation.getDeemableAssets());
    }

    @Test
    public void getDeemableAssets_shouldSumDeemableAssets() {
        assertEquals(BigDecimal.valueOf(550_200), calculation.getDeemableAssets());
    }

    @Test
    @Ignore
    public void getDeemedIncome_shouldCalculateDeemedIncome() {
        DeemingRate deemingRate1 = new DeemingRate(null, BigDecimal.ZERO, BigDecimal.valueOf(49_200), BigDecimal.valueOf(1.75));
        DeemingRate deemingRate2 = new DeemingRate(null, BigDecimal.valueOf(49_200), null, BigDecimal.valueOf(3.25));
        DeemingRateGroup deemingRateGroup = new DeemingRateGroup(new Date(), new Date(), Arrays.asList(deemingRate1, deemingRate2));
        assertEquals(BigDecimal.ZERO, calculation.getDeemedIncome(deemingRateGroup));
    }
}
