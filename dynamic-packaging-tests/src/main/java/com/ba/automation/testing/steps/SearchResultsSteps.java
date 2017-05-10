package com.ba.automation.testing.steps;

import com.ba.automation.testing.enums.ScreenType;
import com.ba.automation.testing.exceptions.StopTestException;
import com.ba.automation.testing.ui.pages.SearchResultsPage;
import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.ba.automation.testing.enums.ScreenType.*;
import static org.junit.Assert.assertTrue;

/**
 * To validate the process bar responsiveness
 * Created by n459280 on 21/02/2017.
 */
public class SearchResultsSteps {
    @Inject
    SearchResultsPage searchResultsPage;

    /**
     * @throws InterruptedException
     * @throws TimeoutException
     */
    @Given("^Navigate to home search results page$")
    public void navigateToDPResponsivePage()
            throws InterruptedException, TimeoutException, StopTestException {
        searchResultsPage.navigateToSearchResultPage();
    }

    /**
     * validate pixel difference should appear at top from number field
     *
     * @param px
     */
    @Then("^verify progress bar margin differs with (.*) top$")
    public void topPXDifferenceFromNumberIcon(String px) {
        assertTrue("Expected pixel difference from number field to top is:" + px +
                        ", Actual pixel difference from number field to top is:"
                        + searchResultsPage.getProgressBar().getCssValue(
                        "padding-top"),
                searchResultsPage.getProgressBar().getCssValue("padding-top").contains(px));
    }

    /**
     * validate pixel difference should appear at bottom from number field
     *
     * @param px
     */
    @Then("^verify progress bar margin differs with (.*) bottom$")
    public void bottomPXDifferenceFromNumberIcon(String px) {
        assertTrue("Expected pixel difference from number field to top is:" + px +
                        ", Actual pixel difference from number field to top is:"
                        + searchResultsPage.getProgressBar().getCssValue(
                        "padding-bottom"),
                searchResultsPage.getProgressBar().getCssValue("padding-bottom").contains(px));
    }

    /**
     * to verify Sort By drop-down options availability
     */
    @Then("^verify Sort By drop-down options displays$")
    public void verifySortByOptionDisplays() {
        assertTrue("Expected: SortBy drop-down Option should display" +
                        ", Actual: SortBy drop-down Option Not displays",
                searchResultsPage.getSortByDropDown().isPresent() && searchResultsPage.getSortByDropDown()
                        .isDisplayed());
    }

    /**
     * to check values under drop-down shown as expected
     *
     * @param options
     */
    @When("^clicking on Sort By drop-down, displays following options$")
    public void validateSortByDropDownOptions(List<String> options) {
        searchResultsPage.getSortByDropDown().click();
        assertTrue("Expected: Values under SortBy drop-down matches the criteria" +
                        ", Actual: Values under SortBy drop-down Not matches the criteria",
                searchResultsPage.verifySortByDropDownValues(options));
    }

    /**
     * to select the required option under SortBy drop down
     *
     * @param option
     */
    @When("^click Sort By drop-down and Select (.*) option$")
    public void clickSortByOptions(String option) throws InterruptedException {
        searchResultsPage.clickSortByDropdownOption(option);
    }

    /**
     * to check the Twirly availability
     *
     * @throws InterruptedException
     */
    @Then("^verify the Twirly displays$")
    public void checkTwirlyDisplays() throws InterruptedException {
        assertTrue("Expected: Twirly loads as per the selection criteria"
                        + "Actual: Twirly is NOt loading",
                searchResultsPage.getTwirlyLoading().isPresent() && searchResultsPage.getTwirlyLoading().isDisplayed());
        searchResultsPage.exitTwirly();

    }

    /**
     * to verify and click hotel image gallery link on breakpoints
     *
     * @param screenType
     */
    @Then("^verify hotel image gallery link on following (.*) and click$")
    public void verifyHotelImageGalleryLinkAndClick(ScreenType screenType) {
        if (screenType.equals(Mobile)) {
            assertTrue("Expected: Hotel Image Gallery Link should NOT display for " + screenType + " breakpoint"
                            + "Actual: Hotel Image Gallery Link is displaying for " + screenType + " breakpoint",
                    !searchResultsPage.getHotelImageGalleryLink().isDisplayed());
        } else {
            assertTrue("Expected: Hotel Image Gallery Link should display"
                            + "Actual: Hotel Image Gallery Link is NOT displaying",
                    searchResultsPage.getHotelImageGalleryLink().isDisplayed() && searchResultsPage
                            .getHotelImageGalleryLink().isPresent());
            searchResultsPage.getHotelImageGalleryLink().click();
        }
    }

    /**
     * to click on hotel image gallery link
     */
    @Then("^Click on hotel image gallery link$")
    public void clickOnHotelImageGalleryLink() {
        assertTrue("Expected: Hotel Image Gallery Link should display"
                        + "Actual: Hotel Image Gallery Link is NOT displaying",
                searchResultsPage.getHotelImageGalleryLink().isDisplayed());
        searchResultsPage.getHotelImageGalleryLink().click();
    }

    /**
     * to click next and previous arrows per requirement
     *
     * @param arrow
     * @param screen
     * @throws InterruptedException
     */
    @When("^Click (.*) arrow on image gallery for (.*) breakpoint$")
    public void clickArrowOnImgGallery(String arrow, ScreenType screen) throws InterruptedException {
        searchResultsPage.clickArrowLink(arrow, screen);
    }

    /**
     * to verify selected image availability at different breakpoints
     *
     * @param img
     * @param screen
     */
    @Then("^User should able to see the (.*) image at (.*) breakpoint$")
    public void imageValueVerification(String img, ScreenType screen) {
        switch (screen) {
        case Mobile:
            if (img.equals("next")) {
                searchResultsPage.getImageGalleryCaptionMobile().waitUntilStopsMoving();
                assertTrue("Expected: User should able to see the next image"
                                + "Actual: User Unable to see the next image",
                        searchResultsPage.getImageGalleryCaptionMobile().getText().split("/")[0].contains("2"));
                break;
            } else {
                searchResultsPage.getImageGalleryCaptionMobile().waitUntilStopsMoving();
                assertTrue("Expected: User should able to see the previous image"
                                + "Actual: User Unable to see the previous image",
                        searchResultsPage.getImageGalleryCaptionMobile().getText().split("/")[0].contains("1"));
                break;
            }
        default:
            if (img.equals("next")) {
                searchResultsPage.getImageGalleryCaption().waitUntilStopsMoving();
                assertTrue("Expected: User should able to see the next image"
                                + " Actual: User Unable to see the next image",
                        searchResultsPage.getImageGalleryCaption().getText().split("/")[0].contains("2"));
                break;
            } else {
                searchResultsPage.getImageGalleryCaption().waitUntilStopsMoving();
                assertTrue("Expected: User should able to see the previous image"
                                + "Actual: User Unable to see the previous image",
                        searchResultsPage.getImageGalleryCaption().getText().split("/")[0].contains("1"));
                break;
            }
        }
    }

    /**
     * to select random images per count given by customer in image gallery and validate the image appearance.
     *
     * @param count
     * @throws InterruptedException
     */
    @Then("Select (.*) random images and view the selected image reflected in image gallery caption")
    public void verifyTheSelectedImageInImageGallery(int count) throws InterruptedException {
        assertTrue("Expected: The selected image is reflected in image gallery"
                        + "Actual: The selected image not reflected in image gallery",
                searchResultsPage
                        .selectRandomImagesAndGetGalleryCaption(
                                searchResultsPage.getRandomValue(count, 1, maxImages())));
    }

    /**
     * to click choose and continue button
     */
    @When("^click on Choose and continue button$")
    public void clickChooseAndContinueBtn() {
        searchResultsPage.getChooseAndContinueOption().findElement(By.tagName("span")).click();
    }

    /**
     * to verify loading image on button
     */
    @Then("^verify the button shows loading image$")
    public void verifyLoadingImage() {
        assertTrue("Expected: After clicking on 'Choose and continue' button 'Loading' image should display"
                        + " Actual: Loading image is not displaying after clicking 'Choose and continue' button",
                !searchResultsPage
                        .getChooseAndContinueOption().findElement(By.tagName("span")).isDisplayed() && searchResultsPage
                        .getChooseAndContinueOption().findElement(By.tagName("img")).isDisplayed());
    }

    /**
     * to get the maximum number of images from dynamically generated image list.
     *
     * @return
     */
    public int maxImages() {
        if (searchResultsPage.getImageGalleryList().findElements(By.tagName("li")).size() == 0) {
            throw new NullPointerException("Image count is zero");
        }
        return searchResultsPage.getImageGalleryList().findElements(By.tagName("li")).size();
    }

    /**
     * to verify margin left and margin right of the page header
     *
     * @param pxLeft
     * @param pxRight
     */
    @Then("^verify page header displays left margin as (.*) pixel and right margin as (.*) pixel$")
    public void marginLeftRightPixelValidation(String pxLeft, String pxRight) {
        assertTrue("Expected Page header left margin is:" + pxLeft +
                        ", Actual Page header left margin is:"
                        + searchResultsPage.getPageHeader().getCssValue(
                        "padding-left"),
                searchResultsPage.getPageHeader().getCssValue("padding-left").contains(pxLeft));
        assertTrue("Expected Page header Right margin is:" + pxLeft +
                        ", Actual Page header Right margin is:"
                        + searchResultsPage.getPageHeader().getCssValue(
                        "padding-right"),
                searchResultsPage.getPageHeader().getCssValue("padding-right").contains(pxRight));
    }

    /**
     * to verify the home object displays as link or icon as per the requirement
     *
     * @param linkORIcon
     */
    @Then("^verify Home(.*) should display$")
    public void validateHomeInPageHeader(String linkORIcon) {
        assertTrue("Expected: Home object should display as:" + linkORIcon +
                        ", Actual: Home object is not displaying as" + linkORIcon,
                searchResultsPage.homeLinkORIconValidation(linkORIcon));
    }

    /**
     * to verify Telephone number appears within the header
     */
    @Then("^verify Telephone number appears within the header$")
    public void verifyTelephoneNumberDisplaysWithInHeader() {
        assertTrue("Expected: Telephone number appears within the header"
                        + ", Actual: Telephone number NOT appears within the header",
                searchResultsPage.getTelephoneNumber().isPresent() && searchResultsPage.getTelephoneNumber()
                        .isDisplayed());
    }

    /**
     * to verify Telephone number NOT appears within the header
     */
    @Then("^verify Telephone number NOT appears within the header$")
    public void verifyTelephoneNumberNotDisplaysWithInPageHeader() {
        assertTrue("Expected: Telephone number appears within the header"
                        + ", Actual: Telephone number NOT appears within the header",
                !searchResultsPage.getTelephoneNumber()
                        .isDisplayed());
    }

    /**
     * to verify telephone number appears bottom of the page
     */
    @Then("^verify Telephone number appears bottom of the page$")
    public void verifyTelephoneNumberDisplaysAtBottom() {
        assertTrue("Expected: Telephone number appears bottom of the page"
                        + ", Actual: Telephone number NOT appears bottom of the page",
                searchResultsPage.getTelephoneNumberBelow().isPresent() && searchResultsPage.getTelephoneNumberBelow()
                        .isDisplayed());
    }

    /**
     * To verify the Flight details tab availability on Hotel information component
     */
    @Then("^Verify flight details tab is display$")
    public void verifyFlightDetailsTabDisplays() {
        assertTrue("Expected: flight Details Tab should display in Hotel Information component"
                        + ", Actual: flight Details Tab is NOT displaying in Hotel Information component",
                searchResultsPage.getHotelInfoComponent().getFlightDetailsTab().getText().contains("Flight details"));
    }

    /**
     * To verify the flight details tab appears open on Hotel Information component by default.
     */
    @Then("^Verify flight details tab appears open on Hotel Information component by default$")
    public void verifyFlightDetailsTabAppearsOpen() {
        assertTrue("Expected: Flight Details Tab should open on Hotel Information component by default"
                        + ", Actual: Flight Details Tab NOT opens on Hotel Information component by default",
                searchResultsPage.getHotelInfoComponent().getActiveTab().getText()
                        .contains("Flight details"));
    }

    /**
     * Validate in-bound and out-bound flight details display under flight details tab
     */
    @Then("^Validate in-bound and out-bound flight details display under flight details tab$")
    public void inBoundOutBoundFlightDetailsValidation() {
        assertTrue("Expected: in-bound and out-bound flight details are displaying under flight details tab"
                        + ", Actual: in-bound and out-bound flight details are NOT displaying under flight details tab",
                searchResultsPage.verifyFlightDetailsDesktop());
    }

    /**
     * to Verify flight details accordion appears collapsed by default
     */
    @Then("^Verify flight details accordion appears collapsed by default$")
    public void checkFlightDetailsCollapsed() {
        assertTrue("Expected: Flight details accordion appears collapsed by default"
                        + ", Actual: Flight details accordion appears expended  by default",
                searchResultsPage.getHotelInfoComponentMobile().getFlightCollapsedContainer().isDisplayed());
    }

    /**
     * to click on flight details accordion
     */
    @When("^Click on flight details accordion$")
    public void clickFlightDetailsAccordion() throws InterruptedException {
        searchResultsPage.getHotelInfoComponentMobile().getFlightCollapsedContainer().click();
        Thread.sleep(1000);
    }

    /**
     * to verify flight details accordion expends
     */
    @Then("^Verify flight details accordion expends$")
    public void checkFlightDetailsExpandsAndShowsFlightInfo() {
        assertTrue("Expected: Flight details accordion appears expends"
                        + ", Actual: Flight details accordion NOT expended",
                searchResultsPage.getHotelInfoComponentMobile().getFlightExpandedContainer().isDisplayed());
    }

    /**
     * to validate in-bound and out-bound flight details display under flight details accordion
     */
    @Then("^Validate in-bound and out-bound flight details display under flight details accordion$")
    public void inBoundOutBoundFlightDetailsValidationMobile() {
        assertTrue("Expected: in-bound and out-bound flight details are displaying under flight details accordion"
                        + ", Actual: in-bound and out-bound flight details are NOT displaying under flight details accordion",
                searchResultsPage.verifyFlightDetailsMobile());
    }

    /**
     * to validate Details link displays under flight details
     * @param breakPoint
     * @throws InterruptedException
     */
    @Then("^Validate Details link displays under flight details for following (.*)$")
    public void checkDetailsLinkUnderFlightDetails(String breakPoint) throws InterruptedException {
        if (breakPoint.contains("Mobile")) {
            clickFlightDetailsAccordion();
            assertTrue("Expected: flight details link displays under flight details for "+breakPoint+" breakpoint"
                            + ", Actual: in-bound and out-bound flight details are NOT displaying under flight details for "+breakPoint+" breakpoint",
                      searchResultsPage.checkFlightDetailsLink(breakPoint));
        } else {
            assertTrue("Expected: in-bound and out-bound flight details are displaying under flight details for "+breakPoint+" breakpoint"
                            + ", Actual: in-bound and out-bound flight details are NOT displaying under flight details for "+breakPoint+" breakpoint",
                      searchResultsPage.checkFlightDetailsLink(breakPoint));
        }
    }

    /**
     * to click flight details link to expand.
     * @param link
     * @param breakpoint
     * @throws InterruptedException
     */
    @When("^Click (.*) details link at (.*) to expand$")
    public void clickDetailsLink(String link, String breakpoint) throws InterruptedException {
        searchResultsPage.clickFlightDetailsLink(link, breakpoint);
    }

    /**
     * to verify the flight details information under flight detils link
     * @param link
     * @param breakpoint
     * @throws InterruptedException
     */
    @Then("^Verify (.*) flight details summery displays at (.*)$")
    public void clickDetailsLinkAndCheckSummery(String link, String breakpoint) throws InterruptedException {
        searchResultsPage.verifyFlightDetailsLink(link, breakpoint);
    }

    /**
     * to click flight details link again to collapse
     * @param link
     * @param breakpoint
     * @throws InterruptedException
     */
    @And("^Click (.*) details link again at (.*) to collapse$")
    public void clickDetailsLinkToCollapse(String link, String breakpoint) throws InterruptedException {
        clickDetailsLink(link,breakpoint);
    }


    /**
     * to verify the footer displays following links
     *
     * @param links
     */
    @Then("^Verify the footer displays following site links in footer$")
    public void validateFooterLinks(List<String> links) throws InterruptedException {
        assertTrue("Expected: Links in footer should matches the criteria"+
                        ", Actual: Links in footer Not matches the criteria",
                searchResultsPage.validateFooterSiteLinks(links));
    }

    /**
     * to verify the following social icon links in footer
     * @param img
     */
    @Then("^Verify the following social icon links in footer$")
    public void validateFooterIcons(List<String> img){
        assertTrue("Expected: Social Link images in footer should matches the criteria"+
                        ", Actual: Social Link images in footer Not matches the criteria",
                searchResultsPage.validateFooterSocialLinksImg(img));
    }





    /**
     * To validate the warning message should show following text
     * @param warningMessage
     */
    @Then("^validate the warning message should show following text$")
    public void assertWarningMessage(List<String> warningMessage) {
        assertTrue("Expected: Warning message should display as :" + warningMessage + ":, Actual: "
                        + "Warning message is not displaying as expected. Actual warning message is" + searchResultsPage
                        .getWarningMessage().getText(),
                warningMessage.contains(searchResultsPage.getWarningMessage().getText()));
    }

    /**
     * to validate information message should show following texts
     * @param informationMsgs
     */
    @Then("^validate information message should show following texts$")
    public void assertInformationMessages(List<String> informationMsgs) {
        for (int i = 0; i < informationMsgs.size(); i++) {
            assertTrue("Expected: Information message should display as :" + informationMsgs.get(i) + ":, Actual: "
                            + "Information message is not displaying as expected. Actual warning message is"
                            + searchResultsPage
                            .getInfoMessages().get(i).getText(),
                    informationMsgs.contains(searchResultsPage.getInfoMessages().get(i).getText()));
        }
    }
}
