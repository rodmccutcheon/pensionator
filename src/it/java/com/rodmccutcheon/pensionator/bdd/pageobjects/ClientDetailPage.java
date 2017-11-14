package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDetailPage extends AbstractPage {

    @Autowired
    public ClientDetailPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void duplicateCalculation(final String date) {
        webDriver.findElement(By.xpath("//tr/td[contains(text(), '" + date + "')]/../td[4]/a[3]")).click();
    }

    public List<WebElement> getCalculations(final String date) {
        return webDriver.findElements(By.xpath("//tr/td[contains(text(), '" + date + "')]"));
    }
}
