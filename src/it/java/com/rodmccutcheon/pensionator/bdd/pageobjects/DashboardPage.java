package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.springframework.stereotype.Component;

@Component
public class DashboardPage extends AbstractPage {

    public void navigateToClients() {
        clickId("clients");
    }

    public void logout() {
        clickId("logout");
    }
}
