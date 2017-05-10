package com.ba.automation.testing.ui.pages.section;

import org.openqa.selenium.support.FindBy;
import uk.sponte.automation.seleniumpom.PageElement;
import uk.sponte.automation.seleniumpom.PageSection;

import java.util.List;

/**
 * ProductContainer section in search results page
 * Created by n459280 on 06/04/2017.
 */
public class ProductContainer extends PageSection {

    @FindBy(css = "ul.tabs li>a[href*='tab1']")
    private PageElement flightDetailsTab;

    @FindBy(css = "ul>li.active")
    private PageElement activeTab;

    @FindBy(css = "article.flight-details-tab-container")
    private PageElement flightDetailsContainer;

    @FindBy(css = ".flight-details-tab-inner-container")
    private List<PageElement> flightDetailsTabInnerContainer;

    @FindBy(css = ".flight-details-tab-departure-details")
    private List<PageElement> flightDepartureDetails;

    @FindBy(css = "a[id*='flightDetailsOutboundLink']")
    private PageElement flightDetailsOutboundLink;

    @FindBy(css = "a[id*='flightDetailsInboundLink']")
    private PageElement flightDetailsInboundLink;

    @FindBy(css = ".flight-details-tab-warning-container")
    private PageElement flightDetailsWarning;

    @FindBy(css = "div.special-offers-container")
    private PageElement specialOffersContainerMobile;

    @FindBy(css = "div.flight-details-your-flights-container")
    private PageElement flightDetailsContainerMobile;

    @FindBy(css = "#flightDetailsExpandedContainer")
    private PageElement flightExpandedContainer;

    @FindBy(css = "#flightDetailsCollapsedContainer")
    private PageElement flightCollapsedContainer;

    @FindBy(css = "div[id*='flightDetailsDropDownOutboundContainer']")
    private PageElement detailsDropDownOutboundContainer;

    @FindBy(css = "div[id*='flightDetailsDropDownInboundContainer']")
    private PageElement detailsDropDownInboundContainer;

    public PageElement getFlightDetailsTab() {
        return flightDetailsTab;
    }

    public PageElement getActiveTab() {
        return activeTab;
    }

    public PageElement getFlightDetailsContainer() {
        return flightDetailsContainer;
    }

    public List<PageElement> getFlightDetailsTabInnerContainer() {
        return flightDetailsTabInnerContainer;
    }

    public List<PageElement> getFlightDepartureDetails() {
        return flightDepartureDetails;
    }

    public PageElement getFlightDetailsOutboundLink() {
        return flightDetailsOutboundLink;
    }

    public PageElement getFlightDetailsInboundLink() {
        return flightDetailsInboundLink;
    }

    public PageElement getFlightDetailsWarning() {
        return flightDetailsWarning;
    }

    public PageElement getSpecialOffersContainerMobile() {
        return specialOffersContainerMobile;
    }

    public PageElement getFlightDetailsContainerMobile() {
        return flightDetailsContainerMobile;
    }

    public PageElement getFlightExpandedContainer() {
        return flightExpandedContainer;
    }

    public PageElement getFlightCollapsedContainer() {
        return flightCollapsedContainer;
    }

    public PageElement getDetailsDropDownOutboundContainer(){
        return detailsDropDownOutboundContainer;
    }

    public PageElement getDetailsDropDownInboundContainer(){
        return detailsDropDownInboundContainer;
    }

}
