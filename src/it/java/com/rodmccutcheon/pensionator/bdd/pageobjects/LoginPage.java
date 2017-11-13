package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPage extends AbstractPage {

    public LoginPage toPage() {
        navigate("http://localhost:8080");
        return this;
    }

    public void doLogin(final String testUser, final String testPass) {
        editText("username", testUser);
        editText("password", testPass);
        clickId("login");
        assertThat(hasErrors()).isFalse();
    }
}
