package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SidebarPageFragment extends AbstractPage {

    @FindBy(id = "clients")
    private WebElement clientsMenuItem;

    @Autowired
    public SidebarPageFragment(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void navigateToClients() {
        clientsMenuItem.click();
    }
}
