package com.rodmccutcheon.pensionator.bdd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AbstractPage {

    private WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    /*public void navigate(final String value) {
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
    }*/

    protected boolean hasErrors() {
        final List<WebElement> errors = webDriver.findElements(By.className("error"));
        return (errors.size() > 0) && errors.get(0).isDisplayed();
    }
}
