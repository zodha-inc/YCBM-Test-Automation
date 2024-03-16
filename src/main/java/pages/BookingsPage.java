package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

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

    @FindBy(css = "div[class='Container_container__Yd918 NavBar_innerNav__3La7h']>a:nth-child(3)")
    protected WebElement bookingPageButton;

    @FindBy(css = "div[class='Hint_hintContainer__2PIdR']>button[aria-label='Actions']")
    protected List <WebElement> bookingActionIconButton;

    @FindBy(css = "div[class='menus_menuPane__28hfJ']>div:nth-child(4)")
    protected WebElement bookingActionIconCancleOption;

    @FindBy(css = "div[style='margin-top: var(--sizes-x3); display: flex; justify-content: flex-end; gap: var(--sizes-x1); flex-wrap: wrap;']>button:nth-child(2)")
    protected WebElement bookingCancleButton;

    @FindBy(css = "tbody[class='Table_TableBody__1MlII']>tr >td:nth-child(7)")
    protected List <WebElement> bookingEventStatusOption;

    @FindBy(css = "aside>div[class='BookingControls_controls__2As7S']>button[aria-label='Close modal']")
    protected WebElement cancleBookingPopupCloseButton;

    @FindBy(css = "div[class='clickout']>div>div>div:nth-child(2)>div[class='menus_menuIcon__XSyIj']")
    protected WebElement bookingActionIconRebookOption;

    @FindBy(css = "div[class='BookingUpdateDialog_profileCard__QpkzI']>div>div:nth-child(3)>button")
    protected WebElement bookingRebookButton;



    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public int getBookingsListSize() {
        driver.navigate().refresh();
        return allBookingsList.size();
    }

    public void cancleBooking(String firstName) {
        bookingPageButton.click();
        sleepInSeconds(2);

        for (int i = 0; i < bookingEventStatusOption.size(); i++) {
            String status = bookingEventStatusOption.get(i).getText();
            if (status.equals("Confirmed")) {
                WebElement actionIconButton = bookingActionIconButton.get(i);
                clickElementByJS(actionIconButton);
                sleepInSeconds(2);
                bookingActionIconCancleOption.click();
                sleepInSeconds(2);
                bookingCancleButton.click();
                sleepInSeconds(2);
                cancleBookingPopupCloseButton.click();
                sleepInSeconds(3);
            }
        }
    }

    public void rebookCancelledBooking(String firstName) {
        bookingPageButton.click();
        String currentWindowHandle = driver.getWindowHandle();
        sleepInSeconds(2);


        for (int i = 0; i < bookingEventStatusOption.size(); i++) {
            String status = bookingEventStatusOption.get(i).getText();
            if (status.equals("Cancelled")) {
                WebElement actionIconButton = bookingActionIconButton.get(i);
                clickElementByJS(actionIconButton);
                sleepInSeconds(2);
                bookingActionIconRebookOption.click();
                sleepInSeconds(2);
                bookingRebookButton.click();
                sleepInSeconds(2);
                switchToNewWindow(currentWindowHandle);


            }

        }

    }

    public String switchToNewWindow(String currentWindowHandle) {
        Set<String > windowHandles = driver.getWindowHandles();
        String childWindowHandle = "";
        for (String handle:windowHandles) {
            if (!handle.equals(currentWindowHandle)) {
                childWindowHandle = handle;
                driver.switchTo().window(childWindowHandle);
            }
        }
        return childWindowHandle;
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
