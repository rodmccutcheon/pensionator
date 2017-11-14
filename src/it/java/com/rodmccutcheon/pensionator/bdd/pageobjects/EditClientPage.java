package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditClientPage extends AbstractPage {

    @FindBy(id = "client.firstName")
    private WebElement firstNameInput;

    @FindBy(id = "client.lastName")
    private WebElement lastNameInput;

    @FindBy(id = "client.gender")
    private WebElement genderInput;

    @FindBy(id = "client.dateOfBirth")
    private WebElement dateOfBirthInput;

    @FindBy(id = "client.relationshipStatus")
    private WebElement relationshipStatusInput;

    @FindBy(id = "client.homeownerStatus")
    private WebElement homeownerStatusInput;

    @FindBy(id = "save-client")
    private WebElement createButton;

    @Autowired
    public EditClientPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void saveClient() {
        firstNameInput.sendKeys("Gary");
        lastNameInput.sendKeys("Newton");
        new Select(genderInput).selectByValue("Male");
        dateOfBirthInput.sendKeys("20 March 1974");
        dateOfBirthInput.sendKeys(Keys.ENTER);
        new Select(relationshipStatusInput).selectByVisibleText("Single");
        new Select(homeownerStatusInput).selectByVisibleText("Homeowner");
        createButton.click();
    }
}
