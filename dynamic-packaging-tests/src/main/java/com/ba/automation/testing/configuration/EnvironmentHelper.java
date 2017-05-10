package com.ba.automation.testing.configuration;

/**
 * Created by n450777 on 22/03/2016.
 */
public class EnvironmentHelper {

    public static String getBuildName() {
        String buildName = readFromEnvironment("BUILD");
        if(buildName == null) buildName = readFromEnvironment("SAUCE_BAMBOO_BUILDNUMBER");
        if(buildName == null) buildName = readFromEnvironment("JENKINS_BUILD_NUMBER");
        if(buildName == null) buildName = readFromEnvironment("BUILD_TAG");
        if(buildName == null) buildName = readFromEnvironment("BUILD_NUMBER");
        if(buildName == null) buildName = readFromEnvironment("TRAVIS_BUILD_NUMBER");
        if(buildName == null) buildName = readFromEnvironment("CIRCLE_BUILD_NUM");

        return buildName;
    }

    public static String readFromEnvironment(String key) {
        String v = System.getProperty(key, null);
        if (v == null)
            v = System.getProperty(key.toString().toLowerCase());
        if (v == null)
            v = System.getenv(key);

        return v;
    }
}
