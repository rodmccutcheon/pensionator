package com.rodmccutcheon.pensionator.bdd;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.*;
import com.rodmccutcheon.pensionator.domain.Client;
import com.rodmccutcheon.pensionator.domain.HomeownerStatus;
import com.rodmccutcheon.pensionator.domain.RelationshipStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class CucumberStepDefinitions implements En {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private SidebarPageFragment sidebarPageFragment;

    @Autowired
    private ClientsPage clientsPage;

    @Autowired
    private ClientDetailPage clientDetailPage;

    @Autowired
    private EditClientPage editClientPage;

    @Autowired
    private DataSource datasource;

    @Before
    public void setup() throws SQLException {
        System.out.println("Cleaning up database");
        ScriptUtils.executeSqlScript(datasource.getConnection(), new ClassPathResource("test-data.sql"));
    }

    @After
    public void logout() {
        sidebarPageFragment.logout();
    }

    public CucumberStepDefinitions() {
        Given("^I login as a valid user$", () -> {
            loginPage.toPage();
            loginPage.doLogin("rod", "password123");
        });

        Given("^I view a user with calculations$", () -> {
            sidebarPageFragment.navigateToClients();
            clientsPage.viewClient("Max Power");
        });

        When("^I duplicate a calculation$", () -> {
            clientDetailPage.duplicateCalculation("25 September 2017");
        });

        When("^I add a new single client$", () -> {
            clientsPage.navigateToEditClientPage();
            editClientPage.saveClient(new Client("Gary", "Newton", new Date(), "Male", new HomeownerStatus("Homeowner"), new RelationshipStatus("Single")));
        });

        When("^I add a new couple$", () -> {
            clientsPage.navigateToEditClientPage();
            Client client = new Client("Andrew", "Weatherall", new Date(), "Male", new HomeownerStatus("Homeowner"), new RelationshipStatus("Couple"));
            Client partner = new Client("Grace", "Weatherall", new Date(), "Female", new HomeownerStatus("Homeowner"), new RelationshipStatus("Couple"));
            editClientPage.saveCouple(client, partner);
        });

        Then("^I should see the duplicate calculation$", () -> {
            assertThat(clientDetailPage.getCalculations("25 September 2017"))
                    .hasSize(2);
        });

        Then("^I should see the client listed$", () -> {    // Write code here that turns the phrase above into concrete actions
            sidebarPageFragment.navigateToClients();
            assertThat(clientsPage.getClients())
                    .contains("Gary Newton");
        });

        Given("^I view the list of clients$", () -> {
            sidebarPageFragment.navigateToClients();
        });

        When("^I delete a client$", () -> {
            clientsPage.deleteClient("Ned Flanders");
        });

        Then("^I should no longer see the client listed$", () -> {
            assertThat(clientsPage.getClients())
                    .isNotEmpty()
                    .doesNotContain("Ned Flanders");
        });

        Then("^I should see both clients listed$", () -> {
            sidebarPageFragment.navigateToClients();
            assertThat(clientsPage.getClients())
                    .contains("Andrew Weatherall", "Grace Weatherall");
        });

        When("^I delete a client who has a partner$", () -> {
            clientsPage.deleteClient("Homer Simpson");
        });

        Then("^I should no longer see either client listed$", () -> {
            assertThat(clientsPage.getClients())
                    .isNotEmpty()
                    .doesNotContain("Homer Simpson", "Marge Simpson");
        });
    }

}
