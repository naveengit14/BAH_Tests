package com.ba.automation.testing.ui.pages;

import com.ba.automation.testing.enums.ScreenType;
import com.ba.automation.testing.exceptions.StopTestException;
import com.ba.automation.testing.helper.URLHelper;
import com.ba.automation.testing.ui.pages.section.Footer;
import com.ba.automation.testing.ui.pages.section.ProductContainer;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.Select;

import uk.sponte.automation.seleniumpom.PageElement;
import uk.sponte.automation.seleniumpom.PageFactory;
import uk.sponte.automation.seleniumpom.annotations.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * search result lite page
 * Created by n459280 on 23/02/2017.
 */
public class SearchResultsPage extends BasePage {

    @FindBy(css = ".progress-bar-container")
    private PageElement progressBar;

    @FindBy(id = "sortDropDown")
    private PageElement sortByDropDown;

    @FindBy(id = "sortTwirlyLoading")
    private PageElement twirlyLoading;

    @FindBy(css = ".sort-twirly-overlay-background")
    private PageElement twirlyOverlayBackground;

    @FindBy(xpath = "(//*[@id=\"next-link\"])[2]")
    private PageElement nextLink;

    @FindBy(id = "next-link")
    private PageElement nextLinkMobile;

    @FindBy(xpath = "(//*[@id=\"prev-link\"])[2]")
    private PageElement prevLink;

    @FindBy(id = "prev-link")
    private PageElement prevLinkMobile;

    @FindBy(css = "#hotelImageGalleryOthers .image-gallery-caption")
    private PageElement imageGalleryCaption;

    @FindBy(css = "#hotelImageGalleryOthers .ps-current ul>li")
    private PageElement galleryImage;

    @FindBy(css = "#hotelImageGalleryOthers .ps-list")
    private PageElement imageGalleryList;

    @FindBy(css = "span.image-gallery-caption")
    private PageElement imageGalleryCaptionMobile;

    @FindBy(css = ".hotel-image-gallery-link")
    private PageElement hotelImageGalleryLink;

    @FindBy(css = "#chooseAndContinueButton_1")
    private PageElement chooseAndContinueOption;

    @FindBy(css = ".row.header-container")
    private PageElement pageHeader;

    @FindBy(css = ".header-home-link>a")
    private PageElement homeLink;

    @FindBy(css = ".header-home-icon-container")
    private PageElement homeIcon;

    @FindBy(css = "div.header-telephone-number-container")
    private PageElement telephoneNumberBelow;

    @FindBy(css = ".header-telephone-for-medium>a")
    private PageElement telephoneNumber;

    @FindBy(css = "div p.warning-message-text")
    private PageElement warningMessage;

    @FindBy(css = "div p.information-message-text")
    private List<PageElement> infoMessages;

    @Section
    @FindBy(css = "section.medium-9.columns.wrapper")
    private ProductContainer hotelInfoComponent;

    @Section
    @FindBy(css = "div.hotel-information-container")
    private ProductContainer hotelInfoComponentMobile;

    @Section
    @FindBy(id = "baFooter")
    private Footer footer;

    @Inject
    Provider<WebDriver> driver;

    @Inject
    URLHelper urlHelper;

    @Inject
    private PageFactory pageFactory;

    public PageElement getTwirlyLoading() {
        twirlyLoading.waitUntilVisible();
        return twirlyLoading;
    }

    public PageElement getHotelImageGalleryLink() {
        return hotelImageGalleryLink;
    }

    public PageElement getSortByDropDown() {
        return sortByDropDown;
    }

    public PageElement getProgressBar() {
        progressBar.waitUntilVisible();
        return progressBar;
    }

    public PageElement getPageHeader() {
        return pageHeader;
    }

    public PageElement getImageGalleryCaption() {
        return imageGalleryCaption;
    }

    public PageElement getImageGalleryCaptionMobile() {
        return imageGalleryCaptionMobile;
    }

    public PageElement getImageGalleryList() {
        return imageGalleryList;
    }

    public PageElement getChooseAndContinueOption() {
        return chooseAndContinueOption;
    }

    public PageElement getTelephoneNumberBelow() {
        return telephoneNumberBelow;
    }

    public PageElement getTelephoneNumber() {
        return telephoneNumber;
    }

    public ProductContainer getHotelInfoComponent() {
        return hotelInfoComponent;
    }

    public ProductContainer getHotelInfoComponentMobile() {
        return hotelInfoComponentMobile;
    }

    public PageElement getWarningMessage() {
        return warningMessage;
    }

    public List<PageElement> getInfoMessages() {
        return infoMessages;
    }

    /**
     * to navigate search result page in different custom break points.
     *
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public void navigateToSearchResultPage()
            throws InterruptedException, TimeoutException, StopTestException {
        try {
            driver.get().manage().window().maximize();
        } catch (WebDriverException ex) {
            Thread.sleep(3000);
        }
        driver.get().navigate().to(urlHelper.getBaseURL());
    }

    /**
     * to verify the expected and actual values under SortBy drop-down
     *
     * @param expValues
     * @return
     */
    public boolean verifySortByDropDownValues(List<String> expValues) {
        boolean bValue = false;
        Select dropDownList = new Select(getSortByDropDown());
        List<WebElement> options = dropDownList.getOptions();
        for (int i = 0; i < expValues.size(); i++) {
            if (expValues.get(i).replaceAll("\\s", "").contains(options.get(i).getText().replaceAll("\\s", ""))) {
                bValue = true;
            } else {
                bValue = false;
                break;
            }
        }
        return bValue;
    }

    /**
     * to click option under the SortByDropdown
     *
     * @param option
     * @throws InterruptedException
     */
    public void clickSortByDropdownOption(String option) throws InterruptedException {
        clickDropdownValue(option, this.getSortByDropDown());
    }

    /**
     * click drop down vales with parameters
     *
     * @param option
     * @param element
     */
    public void clickDropdownValue(String option, PageElement element) {
        // added the sleep for a second to see the selected option displays in sauce labs results.
        try {
            Thread.sleep(500);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(option);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * to click on the arrow by link name
     *
     * @param linkName
     * @param screen
     */
    public void clickArrowLink(String linkName, ScreenType screen) throws InterruptedException {
        if (ScreenType.Mobile.equals(screen)) {
            switch (linkName) {
            case "next":
                nextLinkMobile.waitUntilStopsMoving();
                nextLinkMobile.click();
                Thread.sleep(2000);
                break;
            case "previous":
                prevLinkMobile.waitUntilStopsMoving();
                prevLinkMobile.click();
                Thread.sleep(2000);
                break;
            }
        } else {
            switch (linkName) {
            case "next":
                nextLink.waitUntilStopsMoving();
                nextLink.click();
                Thread.sleep(1000);
                break;
            case "previous":
                prevLink.waitUntilStopsMoving();
                prevLink.click();
                Thread.sleep(1000);
                break;
            }
        }
    }

    /**
     * to generate random values between min and mix values
     *
     * @param count
     * @param min
     * @param max
     * @return
     */
    public List<Integer> getRandomValue(int count, int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            int li = (int) (Math.random() * (max - min)) + min;
            list.add(li);
        }
        return list;
    }

    /**
     * To Select images from image gallery randomly
     *
     * @param integerList
     * @return
     * @throws InterruptedException
     */
    public boolean selectRandomImagesAndGetGalleryCaption(List<Integer> integerList) throws InterruptedException {
        List<Integer> galleryCaptionList = new ArrayList<>();
        for (Integer integer : integerList) {
            try {
                imageGalleryList.findElement(By.xpath("ul/li[" + integer + "]")).click();
            } catch (NoSuchElementException ex) {
                JavascriptExecutor js = (JavascriptExecutor) driver.get();
                js.executeScript("arguments[0].click();",
                        imageGalleryList.findElement(By.xpath("ul/li[" + integer + "]")));
            }
            Thread.sleep(1000);
            galleryCaptionList.add(Integer.parseInt(galleryImage.getAttribute("class").split("_")[1]));
        }
        return galleryCaptionList.containsAll(integerList);
    }

    /**
     * To exit Twirly loading.
     *
     * @throws InterruptedException
     */
    public void exitTwirly() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].click();", twirlyOverlayBackground);
    }

    /**
     * to the home element is icon or link in given breakpoint
     *
     * @param linkORIcon
     * @return
     */
    public boolean homeLinkORIconValidation(String linkORIcon) {
        if (linkORIcon.contains("link")) {
            return homeLink.isPresent() && homeLink.isDisplayed();
        } else {
            return homeIcon.isPresent() && homeIcon.isDisplayed();
        }
    }

    /**
     * to verify flight details for Desktop,Tablet and Custom breakpoint
     *
     * @return
     */
    public boolean verifyFlightDetailsDesktop() {
        return verifyFlightDetails(hotelInfoComponent);
    }

    /**
     * to verify flight details for mobile breakpoint
     *
     * @return
     */
    public boolean verifyFlightDetailsMobile() {
        return verifyFlightDetails(hotelInfoComponentMobile);
    }

    /**
     * to verify the flight details in flight details container
     *
     * @return
     */
    public boolean verifyFlightDetails(ProductContainer element) {
        List<PageElement> flightDepartureDetails = element.getFlightDepartureDetails();
        boolean flag = false;
        for (PageElement flightDepartureDetail : flightDepartureDetails) {
            if (flightDepartureDetail.isDisplayed()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * to check the flight details link availability
     *
     * @param breakPoint
     * @return
     */
    public boolean checkFlightDetailsLink(String breakPoint) {
        if (breakPoint.contains("Mobile")) {
            hotelInfoComponentMobile.getFlightDetailsOutboundLink().waitUntilVisible(2000);
            return hotelInfoComponentMobile.getFlightDetailsOutboundLink().isDisplayed() && hotelInfoComponentMobile
                    .getFlightDetailsInboundLink().isDisplayed();
        } else {
            return hotelInfoComponent.getFlightDetailsOutboundLink().isDisplayed() && hotelInfoComponent
                    .getFlightDetailsInboundLink().isDisplayed();
        }
    }

    /**
     * to click scroll and click on the element.
     *
     * @param element
     * @throws InterruptedException
     */
    public void clickOnElement(PageElement element) throws InterruptedException {
        element.waitUntilVisible();
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                element);
        Thread.sleep(500);
        element.click();
        Thread.sleep(500);
    }

    /**
     * to  click the flight details link as per breakpoint
     *
     * @param link
     * @param breakpoint
     * @throws InterruptedException
     */
    public void clickFlightDetailsLink(String link, String breakpoint) throws InterruptedException {
        if (breakpoint.contains("Mobile")) {
            if (link.contains("out-bound")) {
                clickOnElement(hotelInfoComponentMobile.getFlightDetailsOutboundLink());
            } else {
                clickOnElement(hotelInfoComponentMobile.getFlightDetailsInboundLink());
            }
        } else {
            if (link.contains("out-bound")) {
                clickOnElement(hotelInfoComponent.getFlightDetailsOutboundLink());
            } else {
                clickOnElement(hotelInfoComponent.getFlightDetailsInboundLink());
            }
        }
    }

    /**
     * to verify the flight details summery text is present under flight details link
     *
     * @param link
     * @param breakpoint
     * @return
     * @throws InterruptedException
     */
    public boolean verifyFlightDetailsLink(String link, String breakpoint) throws InterruptedException {
        boolean results = false;
        if (breakpoint.contains("Mobile")) {
            if (link.contains("out-bound")) {
                results = !hotelInfoComponentMobile.getDetailsDropDownOutboundContainer().getText().isEmpty();
                return results;
            } else {
                results = !hotelInfoComponentMobile.getDetailsDropDownInboundContainer().getText().isEmpty();
                return results;
            }
        } else {
            if (link.contains("out-bound")) {
                results = !hotelInfoComponent.getDetailsDropDownOutboundContainer().getText().isEmpty();
                return results;
            } else {
                results = !hotelInfoComponent.getDetailsDropDownInboundContainer().getText().isEmpty();
                return results;
            }
        }
    }

    /**
     * to verify the footer site links
     *
     * @param expValues
     * @return
     */
    public boolean validateFooterSiteLinks(List<String> expValues) throws InterruptedException {
        boolean flag = false;
        ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                footer.getSiteLinks().get(0));
        Thread.sleep(1000);
        for (int i = 0; i < expValues.size(); i++) {
            if (expValues.get(i).replaceAll("\\s", "").contains(
                    footer.getSiteLinks().get(i).getText().replaceAll("[^A-Za-z]+", ""))) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * to verify the footer site links
     *
     * @param expValues
     * @return
     */
    public boolean validateFooterSocialLinksImg(List<String> expValues) {
        boolean flag = false;
        for (int i = 0; i < expValues.size(); i++) {
            if (footer.getSocialLinkImg().get(i).getAttribute("href").contains(expValues.get(i))) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
