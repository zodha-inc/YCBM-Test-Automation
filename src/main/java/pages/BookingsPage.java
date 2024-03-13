package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingsPage extends WebPage {
    @FindBy(css = "a[href='#/bookings']")
    protected WebElement bookingsLink;

    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoBookingsPage() {
        if(waitUntilClickable(bookingsLink)) {
            bookingsLink.click();
        }
    }
}
