package com.rodmccutcheon.pensionator.bdd.pageobjects;

import com.rodmccutcheon.pensionator.domain.Client;
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

    @FindBy(id = "partner.firstName")
    private WebElement partnerFirstNameInput;

    @FindBy(id = "partner.lastName")
    private WebElement partnerLastNameInput;

    @FindBy(id = "partner.gender")
    private WebElement partnerGenderInput;

    @FindBy(id = "partner.dateOfBirth")
    private WebElement partnerDateOfBirthInput;

    @FindBy(id = "save-client")
    private WebElement saveButton;

    @Autowired
    public EditClientPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    private void enterClientDetails(Client client) {
        firstNameInput.sendKeys(client.getFirstName());
        lastNameInput.sendKeys(client.getLastName());
        new Select(genderInput).selectByValue(client.getGender());
        dateOfBirthInput.sendKeys(client.getDateOfBirth().toString());
        dateOfBirthInput.sendKeys(Keys.ENTER);
        new Select(relationshipStatusInput).selectByVisibleText(client.getRelationshipStatus().getName());
        new Select(homeownerStatusInput).selectByVisibleText(client.getHomeownerStatus().getName());
    }

    public void saveClient(Client client) {
        enterClientDetails(client);
        saveButton.click();
    }

    public void saveCouple(Client client, Client partner) {
        enterClientDetails(client);
        partnerFirstNameInput.sendKeys(partner.getFirstName());
        partnerLastNameInput.sendKeys(partner.getLastName());
        new Select(partnerGenderInput).selectByValue(partner.getGender());
        partnerDateOfBirthInput.sendKeys(partner.getDateOfBirth().toString());
        partnerDateOfBirthInput.sendKeys(Keys.ENTER);
        saveButton.click();
    }
}
