package com.ba.automation.testing.hooks;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by n450777 on 22/03/2016.
 */
public class SauceLabsHooks {

    public static final String SAUCE_URL = "ondemand.saucelabs.com";

    @Inject
    Provider<WebDriver> driverProvider;

    public static final String SAUCELABS_SESSION_REPORT_FORMAT = "SauceOnDemandSessionID=%s job-name=%s%n";

    @Before
    public void reportTestAndBuildName(Scenario scenario) {
        if(!isRunningOnSauceLabs()) {
            return;
        }

        if(driverProvider.get() instanceof RemoteWebDriver) {
            System.out.printf(SAUCELABS_SESSION_REPORT_FORMAT,
                    ((RemoteWebDriver) driverProvider.get()).getSessionId(), scenario.getName());
        }
        getJSExecutor().executeScript(String.format("sauce:job-name=%s", scenario.getName()));
    }

    @After
    public void reportTestBuildAndStatus(Scenario scenario) {
        if(!isRunningOnSauceLabs()) {
            return;
        }
        getJSExecutor().executeScript(String.format("sauce:job-result=%s", !scenario.isFailed()));
    }

    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driverProvider.get();
    }

    public boolean isRunningOnSauceLabs() {

        if (!(driverProvider.get() instanceof RemoteWebDriver)) {
            return false;
        }

        RemoteWebDriver remoteDriver = (RemoteWebDriver) driverProvider.get();
        CommandExecutor commandExecutor = remoteDriver.getCommandExecutor();

        if(commandExecutor instanceof HttpCommandExecutor) {
            return ((HttpCommandExecutor) commandExecutor).getAddressOfRemoteServer().getHost().equalsIgnoreCase(
                    SAUCE_URL);
        }

        return false;
    }
}
