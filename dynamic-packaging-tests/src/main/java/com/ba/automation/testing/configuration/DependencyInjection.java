package com.ba.automation.testing.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import uk.sponte.automation.seleniumpom.guice.SeleniumPomGuiceModule;

/**
 * Created by n450777 on 12/04/2016.
 */
public class DependencyInjection extends AbstractModule implements InjectorSource {
    protected void configure() {
        bind(WebDriver.class).toProvider(WebDriverFactory.class).in(
                ScenarioScoped.class);
    }

    public Injector getInjector() {
        return new SeleniumPomGuiceModule(this, CucumberModules.SCENARIO).getInjector();
    }
}
