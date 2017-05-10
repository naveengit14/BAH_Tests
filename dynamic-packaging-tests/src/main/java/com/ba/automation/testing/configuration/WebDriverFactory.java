package com.ba.automation.testing.configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by n450777 on 12/04/2016.
 */
public class WebDriverFactory implements Provider<WebDriver> {
    private static final String driverPath = "C:/Users/n459280/git/dynamic-packaging-tests/etc/browserdrivers/";

    @Inject
    private SeleniumConfiguration seleniumConfiguration;

    public WebDriver get() {
        if (System.getProperty("Webdriver.local") != null) {
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            return new ChromeDriver();
        }
        return new RemoteWebDriver(
                seleniumConfiguration.getSeleniumUrl(),
                seleniumConfiguration.getDesiredCapabilities());
    }
}
