package com.ba.automation.testing.ui.pages.section;

import org.openqa.selenium.support.FindBy;
import uk.sponte.automation.seleniumpom.PageElement;
import uk.sponte.automation.seleniumpom.PageSection;

import java.util.List;

/**
 * Footer in search results page
 * Created by n459280 on 17/04/2017.
 */
public class Footer extends PageSection {

    @FindBy(css = "ul.siteLinks li")
    private List<PageElement> siteLinks;

    @FindBy(css = "ul.socialLinks a")
    private List<PageElement> socialLinkImg;

    public List<PageElement> getSiteLinks() {
        return siteLinks;
    }
    public List<PageElement> getSocialLinkImg() {
        return socialLinkImg;
    }
}
