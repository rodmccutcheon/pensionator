package com.rodmccutcheon.pensionator.bdd.stepdefinitions;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.CalculationResultPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientDetailPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.EditCalculationPage;
import com.rodmccutcheon.pensionator.domain.Asset;
import com.rodmccutcheon.pensionator.domain.AssetType;
import com.rodmccutcheon.pensionator.domain.Calculation;
import com.rodmccutcheon.pensionator.domain.IncomeStream;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class CalculationSteps implements En {

    @Autowired
    ClientDetailPage clientDetailPage;

    @Autowired
    EditCalculationPage editCalculationPage;

    @Autowired
    CalculationResultPage calculationResultPage;

    public CalculationSteps() {

        When("^I create a new calculation$", () -> {
            clientDetailPage.navigateToEditCalculationPage();
            List<Asset> assets = new ArrayList<>();
            assets.add(new Asset(new AssetType("Home contents", false), "Centrelink value", BigDecimal.valueOf(5_000)));
            List<IncomeStream> incomeStreams = new ArrayList<>();

            Calculation calculation = new Calculation(new Date(), assets, incomeStreams, "Initial calculation");
            editCalculationPage.saveCalculation(calculation);
        });

        When("^I duplicate a calculation$", () -> {
            clientDetailPage.duplicateCalculation("25 September 2017");
        });

        When("^I delete a calculation$", () -> {
            clientDetailPage.deleteCalculation("25 September 2017");
        });

        Then("^I should see the results of the calculation$", () -> {
            throw new PendingException();
        });

        Then("^I should see the new calculation in the list of calculations$", () -> {
            calculationResultPage.navigateBackToClientDetailPage();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
            assertThat(clientDetailPage.getCalculations(simpleDateFormat.format(new Date())))
                    .hasSize(1);
        });

        Then("^I should see the duplicate calculation in the list of calculations$", () -> {
            assertThat(clientDetailPage.getCalculations("25 September 2017"))
                    .hasSize(2);
        });

        Then("^I should no longer see the calculation in the list of calculations$", () -> {
            assertThat(clientDetailPage.getCalculations("25 September 2017"))
                    .isEmpty();
        });
    }
}
