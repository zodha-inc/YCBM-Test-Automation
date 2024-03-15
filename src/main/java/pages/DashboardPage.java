package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

public class DashboardPage extends WebPage {

    @FindBy(css = "div[aria-label='Upgrade account'] > div:first-child > button")
    protected WebElement closeUpgradeOfferModel;

    @FindBy(css = "div[data-testid='dashboardProfiles'] > div > div[class^='BookingPages_item']")
    protected List<WebElement> bookingPagesList;

    @FindBy(css = "a[href='#/bookings']")
    protected WebElement bookingsLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }



    public void closeUpgradeOffer() {
        if (isElementPresent("div[aria-label='Upgrade account'] > div:first-child > button")) {
            closeUpgradeOfferModel.click();
        }
    }

    public void gotoBookingsPage() {
        if(waitUntilClickable(bookingsLink)) {
            bookingsLink.click();
        }
    }

    public String gotoSelectedBookingPage() {
        System.out.println("Booking list size"+bookingPagesList.size());
        String dashBoardWindowHandle = driver.getWindowHandle();
        if (!bookingPagesList.isEmpty()) {
            WebElement selectedBookingPageItem = getRandomWebElementFromList(bookingPagesList);
            WebElement bookingPageLink = selectedBookingPageItem.findElement(By.cssSelector(
                    "div[data-testtype='profile'] a[data-testtype='profileLink']"));
            if(waitUntilClickable(bookingPageLink)) {
                bookingPageLink.click();
                Set<String> windowHandlesList = driver.getWindowHandles();
                for(String wh : windowHandlesList){
                    if(!wh.equals(dashBoardWindowHandle)) {
                        driver.switchTo().window(wh);
                    }
                }
            }
        } else {
            System.out.println("Error: didn't get the Booking page list, size is 0." );
        }
        return dashBoardWindowHandle;
    }


}
