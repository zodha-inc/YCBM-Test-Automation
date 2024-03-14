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



    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public void bookingPageOption(){
        bookingsPage.click();
    }

    public void searchBooking(){
        filterOption.click();
        searchBookings.sendKeys("test");
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
        baseSelectionOption.get(5).click();
    }

}
