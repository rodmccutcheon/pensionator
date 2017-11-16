package com.rodmccutcheon.pensionator.bdd.pageobjects;

import com.rodmccutcheon.pensionator.domain.Asset;
import com.rodmccutcheon.pensionator.domain.Calculation;
import com.rodmccutcheon.pensionator.domain.IncomeStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditCalculationPage extends AbstractPage {

    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "add-asset")
    private WebElement addAssetButton;

    @FindBy(id = "add-income-stream")
    private WebElement addIncomeStreamButton;

    @FindBy(id = "comment")
    private WebElement commentInput;

    @FindBy(id = "calculate")
    private WebElement calculateButton;

    @Autowired
    public EditCalculationPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void saveCalculation(Calculation calculation) {
        dateInput.sendKeys(calculation.getDate().toString());
        dateInput.sendKeys(Keys.ENTER);

        // Add each asset
        for (int i = 0; i < calculation.getAssets().size(); i++) {
            addAsset(calculation.getAssets().get(i), i);
        }

        // Add each income stream
        for (int j = 0; j < calculation.getIncomeStreams().size(); j++) {
            addIncomeStream(calculation.getIncomeStreams().get(j), j);
        }

        commentInput.sendKeys(calculation.getComment());
        calculateButton.click();
    }

    private void addAsset(Asset asset, int index) {
        addAssetButton.click();
        new Select(getWebDriver().findElement(By.id("assets" + index + ".assetType")))
                .selectByVisibleText(asset.getAssetType().getName());
        getWebDriver().findElement(By.id("assets" + index + ".description")).sendKeys(asset.getDescription());
        getWebDriver().findElement(By.id("assets" + index + ".value")).sendKeys(asset.getValue().toString());
    }

    private void addIncomeStream(IncomeStream incomeStream, int index) {
        addIncomeStreamButton.click();
    }
}
