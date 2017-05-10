package com.ba.automation.testing.hooks;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Created by n450777 on 12/04/2016.
 */
public class ScreenshotHooks {
    @Inject
    Provider<WebDriver> driver;

    @After
    public void takeScreenshot(Scenario scenario) {
        scenario.embed(((TakesScreenshot) driver.get())
                .getScreenshotAs(OutputType.BYTES), "image/png");
    }
}
