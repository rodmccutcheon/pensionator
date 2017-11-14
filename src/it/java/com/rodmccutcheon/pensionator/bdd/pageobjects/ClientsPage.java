package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientsPage extends AbstractPage {

    @Autowired
    public ClientsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void viewClient(final String name) {
        webDriver.findElement(By.xpath("//tr/td[contains(text(), '" + name + "')]/../td[3]/a[1]")).click();
    }

    public void addClient() {

    }

    public void deleteClient(final String name) {
        webDriver
                .findElement(By.xpath("//tr/td[contains(text(), '" + name + "')]/../td[3]/a[3]"))
                .click();
        webDriver
                .switchTo()
                .alert()
                .accept();
    }

    public List<String> getClients() {
        return webDriver
                .findElements(By.xpath("//tr/td[2]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
