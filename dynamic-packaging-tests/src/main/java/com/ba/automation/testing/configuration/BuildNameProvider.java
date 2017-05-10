package com.ba.automation.testing.configuration;

import com.google.inject.Singleton;

/**
 * Created by n450777 on 22/03/2016.
 */
@Singleton
public class BuildNameProvider {

    private String buildName;

    public String getBuildName() {
        if(buildName != null) return buildName;

        buildName = EnvironmentHelper.getBuildName();
        return buildName;
    }
}
