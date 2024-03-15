package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingsPage extends WebPage {

    @FindBy(css = "tbody > tr[data-display-starts-at-medium-short]")
    protected List<WebElement> allBookingsList;

    @FindBy(xpath = "//a[text()='Dashboard']")
    protected WebElement dashBoardNavLink;

    @FindBy(css = "aside[class^='BookingControls_menu'] > div[class^='menus_menuItem']")
    protected List<WebElement> editBookingPopDialogRightSideMenuList;

    @FindBy(css = "div[style^='margin-top: var(--sizes-x3);'] > button:last-child")
    protected WebElement deleteBookingBtn;

    @FindBy(css = "div[class='styledWrapper toast-content']")
    protected WebElement deleteNoticeToaster;

    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public int getBookingsListSize() {
        driver.navigate().refresh();
        return allBookingsList.size();
    }

    public WebElement chooseSpecificBooking(String bookingFirstName) {
        for (WebElement currentBookingRecordRow:allBookingsList) {
            WebElement bookingInfoHeaderEle = currentBookingRecordRow.findElement(By.cssSelector("td:nth-child(4) > span"));
            if(bookingInfoHeaderEle.getText().trim().toLowerCase().contains(bookingFirstName.toLowerCase())) {
                return bookingInfoHeaderEle;
            }
        }
        return null;
    }

    public void gotoDashBoard() {
        dashBoardNavLink.click();
    }

    public void deleteExistingBooking() {
        WebElement selectedBooking = getRandomWebElementFromList(allBookingsList);
        scrollToElement(selectedBooking);
        clickElementByJS(selectedBooking);
        editBookingPopDialogRightSideMenuList.get(3).click();
        sleepInSeconds(1);
        clickElementByJS(deleteBookingBtn);
    }

    public String getDeleteNoticeToasterText() {
        if (isElementPresent("div[class='styledWrapper toast-content']")) {
            return deleteNoticeToaster.getText();
        } else {
            return "Error: Delete notice toaster didn't show.";
        }
    }
}
