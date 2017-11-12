package com.rodmccutcheon.pensionator.pageobjects;

import com.rodmccutcheon.pensionator.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientPage extends AbstractPage {

    public void duplicateCalculation(final String date) {
        DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr/td[contains(text(), '" + date + "')]/../td[4]/a[3]")).click();
    }

    public List<WebElement> getCalculations(final String date) {
        return DriverFactory.getInstance().getDriver().findElements(By.xpath("//tr/td[contains(text(), '" + date + "')]"));
    }
}
