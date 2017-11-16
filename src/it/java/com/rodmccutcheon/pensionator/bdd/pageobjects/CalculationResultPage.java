package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculationResultPage extends AbstractPage {

    @FindBy(id = "back")
    private WebElement backButton;

    @Autowired
    public CalculationResultPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void navigateBackToClientDetailPage() {
        backButton.click();
    }
}
