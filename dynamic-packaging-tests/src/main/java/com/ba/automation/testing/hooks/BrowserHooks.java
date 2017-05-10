package com.ba.automation.testing.hooks;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

/**
 * Created by n450777 on 12/04/2016.
 */
public class BrowserHooks {
    @Inject
    Provider<WebDriver> driver;

    @After(order = 1)
    public void closeBrowser() {
        driver.get().quit();
    }
}
