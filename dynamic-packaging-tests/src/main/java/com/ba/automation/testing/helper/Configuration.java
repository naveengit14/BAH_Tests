package com.ba.automation.testing.helper;

import com.ba.automation.testing.exceptions.StopTestException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Configuration page
 * Created by n448846 on 25/08/2016.
 */
public final class Configuration {
    public static final String KEYBASE = "autotest";
    // This is the full key allowing for multiple settings
    public static final String KEY_SETTINGS = KEYBASE + ".settings";
    // This is used to ensure the settings are loaded
    public static final String KEY_LOADED = KEYBASE + ".loaded";
    // This environment used for testing
    public static final String KEY_ENVIRONMENT = "environment";
    // remaining parts
    public static final String KEYP_ENV_URL = "url";

    private static final Logger LOG = Logger.getLogger(Configuration.class.getName());

	/*
     * 3 levels of hierarchy - user - project - module
	 *
	 * 3 levels of property files - bootstrap -
	 * {user.home}/capuser-bootstrap.properties -
	 * ../project-bootstrap.properties - ./bootstrap.properties - defaults -
	 * normal properties - {user.home}/capuser.properties -
	 * ../project.properties - ./module.properties
	 */

    private static final String[] FILES = {
            // The reverse order here is due to he fact they are all loaded in
            // this order and so the
            // later ones loaded overwrite the newer ones to try mimic the ant
            // process.
            "./module.properties",
            "autotest/module.properties",
            "../project.properties",
            "{user.home}/capuser.properties",
            "./bootstrap.properties",
            "./test.properties",
            "../test.properties",
            "../project-bootstrap.properties",
            "project-bootstrap.properties",
            "{user.home}/capuser-bootstrap.properties" };

    // so that multiple
    // thread can
    // reconcile the
    // instance

    private static final Configuration CONFIG = new Configuration();

    private final Properties myProps;

    private String settings;



    /**
     * This is private constructor for this class which defines default values.
     */
    private Configuration() {
        settings = System.getProperty(KEY_SETTINGS, null);
        myProps = new Properties(System.getProperties());
        String userHome = System.getProperty("user.home", "./");
        String loaded = myProps.getProperty(KEY_LOADED);
        if (loaded == null) {
            for (String filename : FILES) {
                try {
                    myProps.load(getFileInputStream(filename.replace("{user.home}", userHome)));
                } catch (FileNotFoundException fnfe) {
                    // Ignore any files that aren't present - same as ant build
                    // infrastructure
                } catch (IOException ioe) {
                    // TODO decide what we require here?
                    LOG.severe("ERROR: " + ioe.getMessage()); // NOPMD
                }
            }
            myProps.putAll(System.getProperties());
        }
        if (null == settings) {
            settings = myProps.getProperty(KEY_SETTINGS, null);
        }
    }

    /**
     * Provides configuration volatile instance.
     *
     * @return Configuration - configuration volatile instance
     */
    public static Configuration getConfiguration() {
        return CONFIG;
    }


    public FileInputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

    /**
     * To get property for the keypart.
     *
     * @param keypart String - key
     * @return String - Property value for the keypart
     */
    protected String getProperty(String keypart) {
        return getProperty(keypart, null);
    }

    /**
     * To get property for the keypart. If its not found, returns defaultValue.
     *
     * @param keypart      String - key
     * @param defaultValue String - default value
     * @return String - Property value for the keypart
     */
    protected String getProperty(String keypart, String defaultValue) {
        String value = null;
        if (myProps != null) {

            if (settings != null && settings.length() > 0) {
                value = myProps.getProperty(KEYBASE + "." + settings + "."
                        + keypart);
            } else {
                value = myProps.getProperty(KEYBASE + "." + keypart);
            }
            if (value == null || value.length() == 0) {
                value = myProps.getProperty(KEYBASE + ".default." + keypart,
                        defaultValue);
            }
        }
        return interpolate(value);
    }

    /**
     * Interpolate in prop names
     *
     * @param value String
     * @return String
     */
    public String interpolate(String value) {
        String modifiedValue = value;

        if (null != modifiedValue) {
            Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z.]+}");
            Matcher matcher = pattern.matcher(value);
            while (matcher.find()) {
                modifiedValue = modifiedValue.replaceAll("\\$\\{", "").replaceAll("}", "");
                modifiedValue = getProperty(modifiedValue);
            }
        }
        return modifiedValue;
    }

    public String getEnvironment() throws StopTestException {
        // get env from system properties first
        String env = getProperty(KEY_ENVIRONMENT);
        if (env == null) {
            throw new StopTestException("environment not set - please set "
                    + KEYBASE + "." + KEY_ENVIRONMENT);
        }
        return env;
    }

//    protected String getEnvironmentsProperty(String keypart)
//            throws StopTestException {
//
//        return getProperty(keypart);
//    }

    public String getUrl() throws StopTestException {
        return getProperty(KEYP_ENV_URL);
    }

}
