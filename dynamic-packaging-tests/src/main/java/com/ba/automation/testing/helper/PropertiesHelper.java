package com.ba.automation.testing.helper;

//import com.ba.cap.autotest.framework.config.Configuration;
//import com.ba.cap.autotest.framework.exceptions.StopTestException;
import com.google.common.base.CaseFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Yuvaraj on 23/03/2017.
 */
public class PropertiesHelper {

    private static Properties seleniumWhitelist = new Properties();

    private static Properties translateKeys = new Properties();

    private static String SELENIUM_VARIABLES_PREFIX = "selenium.";
    private static String SELENIUM_ENVIRONMENT_VARIABLES_PREFIX = "SELENIUM_";
    private static String SAUCE_ENVIRONMENT_VARIABLES_PREFIX = "SAUCE_";

    static {
        translateKeys.put("browser", "browserName");
        translateKeys.put("apiKey", "access-key");
        translateKeys.put("accessKey", "access-key");

        seleniumWhitelist.put("access-key", "access-key");
        seleniumWhitelist.put("app", "app");
        seleniumWhitelist.put("appactivity", "appActivity");
        seleniumWhitelist.put("appiumversion", "appiumVersion");
        seleniumWhitelist.put("apppackage", "appPackage");
        seleniumWhitelist.put("args", "args");
        seleniumWhitelist.put("autoacceptalerts", "autoAcceptAlerts");
        seleniumWhitelist.put("automationname", "automationName");
        seleniumWhitelist.put("avoidproxy", "avoidProxy");
        seleniumWhitelist.put("background", "background");
        seleniumWhitelist.put("browsername", "browserName");
        seleniumWhitelist.put("build", "build");
        seleniumWhitelist.put("capturehtml", "captureHtml");
        seleniumWhitelist.put("chromedriverversion", "chromedriverVersion");
        seleniumWhitelist.put("commandtimeout", "commandTimeout");
        seleniumWhitelist.put("customdata", "customData");
        seleniumWhitelist.put("devicename", "deviceName");
        seleniumWhitelist.put("deviceorientation", "deviceOrientation");
        seleniumWhitelist.put("executable", "executable");
        seleniumWhitelist.put("idletimeout", "idleTimeout");
        seleniumWhitelist.put("iedriverversion", "iedriverVersion");
        seleniumWhitelist.put("maxduration", "maxDuration");
        seleniumWhitelist.put("name", "name");
        seleniumWhitelist.put("parenttunnel", "parentTunnel");
        seleniumWhitelist.put("passed", "passed");
        seleniumWhitelist.put("platform", "platform");
        seleniumWhitelist.put("platformname", "platformName");
        seleniumWhitelist.put("platformversion", "platformVersion");
        seleniumWhitelist.put("prerun", "prerun");
        seleniumWhitelist.put("public", "public");
        seleniumWhitelist.put("recordlogs", "recordLogs");
        seleniumWhitelist.put("recordscreenshots", "recordScreenshots");
        seleniumWhitelist.put("recordvideo", "recordVideo");
        seleniumWhitelist.put("screenresolution", "screenResolution");
        seleniumWhitelist.put("seleniumversion", "seleniumVersion");
        seleniumWhitelist.put("tags", "tags");
        seleniumWhitelist.put("timeout", "timeout");
        seleniumWhitelist.put("timezone", "timeZone");
        seleniumWhitelist.put("tunnelidentifier", "tunnelIdentifier");
        seleniumWhitelist.put("username", "username");
        seleniumWhitelist.put("version", "version");
        seleniumWhitelist.put("videouploadonpass", "videoUploadOnPass");
        seleniumWhitelist.put("webdriverremotequietexceptions", "webdriverRemoteQuietExceptions");
    }

    public static HashMap<String, Object> getSeleniumProperties()
            throws Exception {
        HashMap<String, Object> props = new HashMap<>();

        props.putAll(PropertiesHelper.extractPropertiesFromEnvironment(
                SELENIUM_ENVIRONMENT_VARIABLES_PREFIX));

        props.putAll(PropertiesHelper.extractPropertiesFromProperties(
                SELENIUM_VARIABLES_PREFIX,
                System.getProperties()));

        return props;
    }

    static HashMap<String, Object> extractPropertiesFromEnvironment(
            String prefix) throws Exception {

        HashMap<String, Object> props = new HashMap<>();

        for (Map.Entry<String, String> object : System.getenv().entrySet()) {
            String keyName = object.getKey();

            props.putAll(collect(prefix, keyName, object.getValue()));
        }

        return props;
    }

    static HashMap<String, Object> extractPropertiesFromProperties(
            String prefix, Properties properties) throws Exception {
        HashMap<String, Object> props = new HashMap<>();

        for (Map.Entry<Object, Object> object : properties
                .entrySet()) {
            String keyName = object.getKey().toString();

            props.putAll(
                    collect(prefix, keyName, object.getValue().toString()));
        }

        return props;
    }


    private static HashMap<String, Object> collect(String prefix,
                                                   String keyName, String value) throws Exception {
        HashMap<String, Object> stringStringHashMap = new HashMap<>();

        if (!keyName.startsWith(prefix))
            return stringStringHashMap;

        Object sanitisedValue = sanitiseValue(value);
        String sanitisedKeyName = sanitiseKeyName(keyName, prefix);

        if (seleniumWhitelist.containsKey(sanitisedKeyName.toLowerCase())) {
            stringStringHashMap.put(sanitisedKeyName, sanitisedValue);
        }

        return stringStringHashMap;
    }

    static Object sanitiseValue(String value) {
        try {
            JSONObject jsonObject = new JSONObject(value);
            return getMap(jsonObject);
        } catch (JSONException exception) {
            return value;
        }
    }

    private static Map<String, String> getMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();

        Set keys = jsonObject.keySet();

        for (Object key : keys) {
            String value = jsonObject.getString(key.toString());
            map.put(key.toString(), value);
        }

        return map;
    }

    static String sanitiseKeyName(String keyName, String... prefixes) {
        String desiredKey = keyName;
        for (String prefix : prefixes) {
            desiredKey = desiredKey.replace(prefix, "");
        }
        boolean convertCase = !keyName.contains(".");

        if (convertCase) {
            desiredKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, desiredKey);
        }

        desiredKey = translateKeys.getProperty(desiredKey, desiredKey);
        desiredKey = seleniumWhitelist.getProperty(desiredKey.toLowerCase(), desiredKey);

        return desiredKey;
    }
}
