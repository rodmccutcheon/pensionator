package com.rodmccutcheon.pensionator.bdd.stepdefinitions;

import com.rodmccutcheon.pensionator.bdd.config.CucumberConfig;
import com.rodmccutcheon.pensionator.bdd.pageobjects.LoginPage;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CucumberConfig.class)
@SpringBootTest
public class LoginSteps implements En {

    @Autowired
    private LoginPage loginPage;

    public LoginSteps() {

        Given("^I login as a valid user$", () -> {
            loginPage.toPage();
            loginPage.doLogin("rod", "password123");
        });

    }
}
