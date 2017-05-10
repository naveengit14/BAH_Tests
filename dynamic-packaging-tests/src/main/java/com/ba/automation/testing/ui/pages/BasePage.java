package com.ba.automation.testing.ui.pages;

import com.ba.automation.testing.exceptions.StopTestException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base Page
 * Created by n459280 on 18/04/2017.
 */
public class BasePage {
    @Inject
    Provider<WebDriver> driver;

    /**
     * Wait f0r page load until the given time
     * @param timeUnit
     * @throws StopTestException
     */
    public void waitForPageLoaded(int timeUnit) throws StopTestException {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver.get(), timeUnit);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            throw new StopTestException("Page is not loaded and not ready" + error.getMessage());
        }
    }

}
