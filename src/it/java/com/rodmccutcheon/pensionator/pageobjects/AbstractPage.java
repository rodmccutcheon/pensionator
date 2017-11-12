package com.rodmccutcheon.pensionator.pageobjects;

import com.rodmccutcheon.pensionator.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    public void navigate(final String value) {
        DriverFactory.getInstance().getDriver().navigate().to(value);
    }

    protected void editText(final String id, final String value) {
        final WebElement element = (new FluentWait<>(DriverFactory.getInstance().getDriver()))
                .withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.sendKeys(value);
    }

    protected void clickId(final String id) {
        final WebElement button = DriverFactory.getInstance().getDriver().findElement(By.id(id));
        final JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
        executor.executeScript("arguments[0].click();", button);
    }

    protected boolean hasErrors() {
        final List<WebElement> errors = DriverFactory.getInstance().getDriver()
                .findElements(By.className("error"));
        return (errors.size() > 0) && errors.get(0).isDisplayed();
    }
}
