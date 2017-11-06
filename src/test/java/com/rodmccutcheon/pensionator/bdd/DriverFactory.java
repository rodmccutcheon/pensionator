package com.rodmccutcheon.pensionator.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private DriverFactory() {
        // Do-nothing..Do not allow to initialize this class from outside
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    // thread local driver object for webdriver
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            //System.setProperty("webdriver.firefox.marionette", "/usr/local/bin/geckodriver");
            //System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            //WebDriver driver = new ChromeDriver();
            //driver.get("http://localhost:8080");
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            return new ChromeDriver(); // or other browser drivers
        }
    };

    // call this method to get the driver object and launch the browser
    public WebDriver getDriver() {
        return driver.get();
    }

    // Quits the driver and closes the browser
    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
