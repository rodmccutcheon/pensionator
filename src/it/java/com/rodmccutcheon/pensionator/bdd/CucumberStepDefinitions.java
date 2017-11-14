package com.rodmccutcheon.pensionator.bdd;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientDetailPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientsPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.DashboardPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
@Sql(scripts = "test-data.sql")
public class CucumberStepDefinitions implements En {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ClientsPage clientsPage;

    @Autowired
    private ClientDetailPage clientDetailPage;

    @Autowired
    private DataSource datasource;

    @Before
    public void setup() throws SQLException {
        System.out.println("Cleaning up database");
        ScriptUtils.executeSqlScript(datasource.getConnection(), new ClassPathResource("test-data.sql"));
    }

    @After
    public void logout() {
        dashboardPage.logout();
    }

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
            assertThat(clientDetailPage.getCalculations("25 September 2017")).hasSize(2);
        });

        Then("^I should see the client listed$", () -> {    // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Given("^I view the list of clients$", () -> {
            dashboardPage.navigateToClients();
        });

        When("^I delete a client$", () -> {
            clientsPage.deleteClient("Ned Flanders");
        });

        Then("^I should no longer see the client listed$", () -> {
            assertThat(clientsPage.getClients()).doesNotContain("Ned Flanders");
        });

    }

}
