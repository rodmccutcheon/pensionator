package com.rodmccutcheon.pensionator.bdd;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientDetailPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientsPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.DashboardPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class CucumberStepDefinitions implements En {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ClientsPage clientsPage;

    @Autowired
    private ClientDetailPage clientDetailPage;

    public CucumberStepDefinitions() {
        Given("^I login in as a valid user$", () -> {
            loginPage.toPage();
            loginPage.doLogin("rod", "password123");
        });

        Given("^I view a user with calculations$", () -> {
            dashboardPage.navigateToClients();
            clientsPage.viewClient("Max Power");
        });

        When("^I duplicate a calculation$", () -> {
            clientDetailPage.duplicateCalculation("25 September 2017");
        });

        When("^I add a new single client$", () -> {
            // Write code here that turns the phrase above into concrete actions
            dashboardPage.navigateToClients();
            clientsPage.addClient();
        });

        When("^I add a new couple$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^I should see the duplicate calculation$", () -> {
            Assertions.assertThat(clientDetailPage.getCalculations("25 September 2017")).hasSize(2);
        });

        Then("^I should see the client listed$", () -> {    // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

    }

}
