package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingsPage extends WebPage{
    @FindBy(css = "a[href='#/bookings']")
    protected WebElement bookingsPage;

    @FindBy(css = "button[aria-controls='bookings-filters']")
    protected WebElement filterOption;


    @FindBy(css = "[placeholder='Search bookings...']")
    protected WebElement searchBookings;

    @FindBy(css = "tbody > tr[data-display-starts-at-medium-short]")
    protected List<WebElement> totalList;

    @FindBy(css = "button[class^='ActionBar_actionBarItemInner']")
    protected List<WebElement> actionButtons;

    @FindBy(css = "div > h2[class^='typography_base']")
    protected WebElement getPastText;

    @FindBy(css = "span[class^='tag_base']")
    protected List<WebElement> baseSelectionOption;


    @FindBy(css = "div[aria-label*='March 11']")
    protected WebElement dateMar11;

    @FindBy(css = "div[aria-label*='March 30']")
    protected WebElement dateMar30;


    @FindBy(css = "span[class^='Ellipsis_noWrap'][style^='max-width']")
    protected List<WebElement> bookingDateRangeText;

    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public void bookingPageOption(){
        bookingsPage.click();
    }

    public void searchBooking(){
        filterOption.click();
        searchBookings.sendKeys("john");
        searchBookings.sendKeys(Keys.ENTER);
    }

    public int getTotal(){
        return totalList.size();
    }

    public void setClearAll(){
        actionButtons.get(0).click();
    }

    public void pastBooking(){
        actionButtons.get(3).click();
    }

    public String getPastBookingsText(){
        return getPastText.getText();
    }

    public void dateRangeOption(){
        actionButtons.get(4).click();
    }

    public void dateRangeSelection(){
        dateMar11.click();
        dateMar30.click();
    }

    public String dateRangeText(){
        return bookingDateRangeText.get(0).getText();
    }


}
