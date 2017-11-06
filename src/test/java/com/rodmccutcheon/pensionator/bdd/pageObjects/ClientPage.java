package com.rodmccutcheon.pensionator.bdd.pageObjects;

import com.rodmccutcheon.pensionator.bdd.DriverFactory;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class ClientPage extends AbstractPage {

    public void duplicateCalculation(final String date) {
        DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr/td[contains(text(), '" + date + "')]/../td[4]/a[3]")).click();
    }
}
