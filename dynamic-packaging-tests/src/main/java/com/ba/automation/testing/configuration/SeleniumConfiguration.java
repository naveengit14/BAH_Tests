package com.ba.automation.testing.configuration;

import com.ba.automation.testing.helper.PropertiesHelper;
import com.google.inject.Inject;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Created by n450777 on 04/04/2016.
 */
@ScenarioScoped
public class SeleniumConfiguration {

    @Inject
    private BuildNameProvider buildNameProvider;


    private final static Logger log = Logger
            .getLogger(SeleniumConfiguration.class.getName());

    private Properties props = System.getProperties();


    public URL getSeleniumUrl() {
        try {
            return new URL(
                    format("http://%s/wd/hub", getSeleniumProperty("host")));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private String getSeleniumProperty(String property,
            String... defaultValue) {
        return getSeleniumProperty("selenium", property, null);
    }

    private String getUserAgent() {
        return getSeleniumProperty("selenium", "user.agent", null);
    }

    private String getSauceProperty(String property, String... defaultValue) {
        return getSeleniumProperty("sauce", property, null);
    }

    private String getSeleniumProperty(String prefix, String property,
            String defaultValue) {
        return props
                .getProperty(format("%s.%s", prefix, property), defaultValue);
    }

    private Properties getSeleniumProperties() {
        Properties selProps = new Properties();
        props.entrySet().stream().filter(prop -> prop.getKey().toString()
                .startsWith("selenium."))
                .forEach(prop -> selProps.put(prop.getKey().toString().replace("selenium.", ""), prop.getValue()));
        return selProps;
    }

    private Map<String, Object> chromeMobileEmulationOptions()
    {
        String userAgent = getSeleniumProperty("userAgentName");
        Map<String ,Object> deviceMetrics   = new HashMap<String, Object>();
        Map<String, Object> mobileEmulation = new HashMap<String,Object>();
        Map<String, Object> chromeOptions   = new HashMap<String, Object>();

        if(userAgent.equalsIgnoreCase("SamsungGalaxyTab2")
                ||userAgent.equalsIgnoreCase("SamsungGalaxyTab3"))
        {
            deviceMetrics.put("width",800);
            deviceMetrics.put("height",1280);
            deviceMetrics.put("pixelRatio",1.0);
            mobileEmulation.put("deviceMetrics",deviceMetrics);
            if(userAgent.equalsIgnoreCase("SamsungGalaxyTab3"))
            {
                mobileEmulation.put("userAgent",
                        "Mozilla/5.0 (Linux; U; Android 4.4.2; en-gb; SM-T310 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko)" +
                                " Version/4.0 Safari/534.30");
            }else {
                mobileEmulation.put("userAgent",
                        "Mozilla/5.0 (Linux; Android 4.4.4; GT-P5110 Build/KTU84Q) AppleWebKit/537.36 (KHTML, like Gecko)" +
                                " Chrome/36.0.1985.135 Safari/537.36");
            }
            chromeOptions.put("mobileEmulation",mobileEmulation);
        }else if(userAgent.equalsIgnoreCase("LgG4"))
        {
            deviceMetrics.put("width",360);
            deviceMetrics.put("height",640);
            deviceMetrics.put("pixelRatio",4.0);
            mobileEmulation.put("deviceMetrics",deviceMetrics);
            mobileEmulation.put("userAgent","Mozilla/5.0 (Linux; Android 5.1; LG-H815 Build/LMY47D) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/45.0.2454.94 Mobile Safari/537.36");
            chromeOptions.put("mobileEmulation",mobileEmulation);
        }
        return chromeOptions;
    }

    public DesiredCapabilities getDesiredCapabilities(){
        DesiredCapabilities caps = new DesiredCapabilities();
        try {

            PropertiesHelper.getSeleniumProperties().entrySet().stream()
                    .filter(prop -> !prop.getKey().equals("host"))
                    .forEach(prop -> {
                        caps.setCapability(
                                prop.getKey().toString(),
                                prop.getValue().toString());
                    });

            if (caps.getBrowserName().equalsIgnoreCase("chrome")) {
                caps.setCapability(ChromeOptions.CAPABILITY, getChromeOptions(getUserAgent()));
            }

            String buildName = buildNameProvider.getBuildName();
            if (buildName != null) {
                caps.setCapability("build", buildName);
            }

            if(getSeleniumProperty("userAgentName")!=null) {
                caps.setCapability(ChromeOptions.CAPABILITY,chromeMobileEmulationOptions());
            }
        }
        catch (Exception ex) {
            log.severe(ex.getMessage());
        }

        return caps;
    }

    private Map<String, Object> getChromeOptions(String userAgent) {
        Map<String, String> mobileEmulation;
        Map<String, Object> chromeOptions = new HashMap<>();
        if(userAgent != null) {
            mobileEmulation = new HashMap<>();
            mobileEmulation.put("userAgent", userAgent);
            chromeOptions.put("mobileEmulation", mobileEmulation);
        }
        return chromeOptions;
    }
}
