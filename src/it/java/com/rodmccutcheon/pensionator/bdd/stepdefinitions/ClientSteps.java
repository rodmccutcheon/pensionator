package com.rodmccutcheon.pensionator.bdd.stepdefinitions;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.ClientsPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.EditClientPage;
import com.rodmccutcheon.pensionator.bdd.pageobjects.SidebarPageFragment;
import com.rodmccutcheon.pensionator.domain.Client;
import com.rodmccutcheon.pensionator.domain.HomeownerStatus;
import com.rodmccutcheon.pensionator.domain.RelationshipStatus;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class ClientSteps implements En {

    @Autowired
    private SidebarPageFragment sidebarPageFragment;

    @Autowired
    private ClientsPage clientsPage;

    @Autowired
    private EditClientPage editClientPage;

    public ClientSteps() {

        Given("^I view a client$", () -> {
            sidebarPageFragment.navigateToClients();
            clientsPage.viewClient("Ned Flanders");
        });

        Given("^I view a client with calculations$", () -> {
            sidebarPageFragment.navigateToClients();
            clientsPage.viewClient("Max Power");
        });

        Given("^I view the list of clients$", () -> {
            sidebarPageFragment.navigateToClients();
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

        When("^I delete a client$", () -> {
            clientsPage.deleteClient("Ned Flanders");
        });

        When("^I delete a client who has a partner$", () -> {
            clientsPage.deleteClient("Homer Simpson");
        });

        Then("^I should no longer see the client in the list of clients$", () -> {
            assertThat(clientsPage.getClients())
                    .isNotEmpty()
                    .doesNotContain("Ned Flanders");
        });

        Then("^I should see the client in the list of clients$", () -> {
            sidebarPageFragment.navigateToClients();
            assertThat(clientsPage.getClients())
                    .contains("Gary Newton");
        });

        Then("^I should see both clients in the list of clients$", () -> {
            sidebarPageFragment.navigateToClients();
            assertThat(clientsPage.getClients())
                    .contains("Andrew Weatherall", "Grace Weatherall");
        });

        Then("^I should no longer see either client in the list of clients", () -> {
            assertThat(clientsPage.getClients())
                    .isNotEmpty()
                    .doesNotContain("Homer Simpson", "Marge Simpson");
        });
    }

}
