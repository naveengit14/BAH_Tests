package com.ba.automation.testing.helper;

import com.ba.automation.testing.exceptions.StopTestException;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.regex.Pattern;

/**
 * Helps in URL design
 * Created by n448846 on 26/08/2016.
 */
public class URLHelper {

    private static final Pattern DEV_ENVIRONMENT = Pattern.compile("^capdev([0-9][0-9][0-9])$");

    private static final Pattern DEV_ENVIRONMENT_FQDN = Pattern.compile("^capdev\\d{3}\\.uk\\.ba\\.com$");

    private static final Pattern REG_PRELIVE_ENVIRONMENT = Pattern.compile("^regression([0-9])|prelive$");

    private static final String PRELIVE_ENVIRONMENT = "prelive";

    private static final String LIVE_ENVIRONMENT = "live";

    private static final Pattern CAP_EXTERNAL_ENVIRONMENT = Pattern.compile("^capext[0-9]{3}");

    private static final String HTTPS = "https";

    private static final String HTTP = "http";

    @Inject
    @Named("autotest.browser.useSecureSiteInIberia")
    private static boolean useSecureSite;

    /**
     * Creates BasePage object and loads it's definition from specified
     * properties file
     *
     * @throws StopTestException
     */
    public URLHelper() throws StopTestException {
        //Do nothing
    }

    /**
     * This method returns the environment URL based on framework settings
     *
     * @return String a URL for the given server environment
     * @throws StopTestException
     */
    public String getBaseURL() throws StopTestException {
        String url = Configuration.getConfiguration().getUrl();
        if (url != null) {
            return url;
        } else {
            throw new StopTestException("URL is not defined : " + url);
        }
    }

}
