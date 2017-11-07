package com.rodmccutcheon.pensionator.bdd;

import com.rodmccutcheon.pensionator.PensionatorApplication;
import com.rodmccutcheon.pensionator.bdd.pageObjects.ClientPage;
import com.rodmccutcheon.pensionator.bdd.pageObjects.ClientsPage;
import com.rodmccutcheon.pensionator.bdd.pageObjects.DashboardPage;
import com.rodmccutcheon.pensionator.bdd.pageObjects.LoginPage;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PensionatorApplication.class)
@SpringBootTest
public class CucumberStepDefinitions implements En {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ClientsPage clientsPage;

    @Autowired
    private ClientPage clientPage;

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
            clientPage.duplicateCalculation("25 September 2017");
        });

        Then("^I should see the duplicate calculation$", () -> {
            assertThat(clientPage.getCalculations("25 September 2017")).hasSize(2);
        });
    }

}
