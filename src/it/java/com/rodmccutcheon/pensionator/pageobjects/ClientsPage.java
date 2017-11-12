package com.rodmccutcheon.pensionator.pageobjects;

import com.rodmccutcheon.pensionator.DriverFactory;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class ClientsPage extends AbstractPage {

    public void viewClient(final String name) {
        DriverFactory.getInstance().getDriver().findElement(By.xpath("//tr/td[contains(text(), '" + name + "')]/../td[3]/a[1]")).click();
    }

    public void addClient() {

    }
}
