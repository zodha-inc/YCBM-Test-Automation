package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class BookingCalenderPage extends WebPage {
    @FindBy(css = "p[class='CookieConsent-module__controls___Be6by'] > button:first-child")
    protected WebElement rejectCookiesBtn;

    @FindBy(css = "div[class^='MonthView-module__day']>button[aria-disabled='false']")
    protected List<WebElement> availableBookingDaysList;

    @FindBy(css = "div[class^='DaySlots-module__slot_column'] button[aria-disabled='false']")
    protected List<WebElement> abailableBookingSlotsList;

    @FindBy(css = "input[data-testid='FNAME']")
    protected WebElement firstNameInput;

    @FindBy(css = "input[data-testid='EMAIL']")
    protected WebElement emailInput;

    @FindBy(css = "div[data-testid='captchaErrorModal'] button")
    protected WebElement securityCheckRefreshBtn;

    @FindBy(css = "div[data-testid='formContent'] button")
    protected WebElement confirmBookingBtn;

    @FindBy(css = "div[class^='ThanksScreen-module__marginTop'] >span >span > p:last-child")
    protected WebElement bookingConfirmingMessage;

    public BookingCalenderPage(WebDriver driver) {
        super(driver);
    }

    public void createBooking(String firstName, String email) {
        if (isElementPresent("p[class='CookieConsent-module__controls___Be6by'] > button:first-child")) {
            rejectCookiesBtn.click();
        }
        if (!availableBookingDaysList.isEmpty()) {
            WebElement selectedBookingDay = getRandomWebElementFromList(availableBookingDaysList);
            clickByJS(selectedBookingDay);
            sleepInSeconds(1);
            if (!abailableBookingSlotsList.isEmpty()) {
                WebElement selectedTimeSlot = getRandomWebElementFromList(abailableBookingSlotsList);
                clickByJS(selectedTimeSlot);
                firstNameInput.sendKeys(firstName);
                emailInput.sendKeys(email);
                sleepInSeconds(2);
                clickByJS(confirmBookingBtn);
                try{
                    securityCheckRefreshBtn.click();
                    sleepInSeconds(2);
                    clickByJS(confirmBookingBtn);
                }
                catch (Exception e) {
                }
            } else {
                System.out.println("Error:time slot list is empty");
            }
        } else {
            System.out.println("Error: the booking available day list is empty");
        }
    }

    public String getBookingConfirmMessage() {
        sleepInSeconds(1);
        return bookingConfirmingMessage.getText();
    }

    public void close(String dashboardWindowHandle) {
        driver.close();
        driver.switchTo().window(dashboardWindowHandle);
    }


}
