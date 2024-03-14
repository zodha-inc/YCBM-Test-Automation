package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingsPage extends WebPage {

    @FindBy(css = "tbody > tr[data-display-starts-at-medium-short]")
    protected List<WebElement> allBookingsList;

    @FindBy(xpath = "//a[text()='Dashboard']")
    protected WebElement dashBoardNavLink;

    public BookingsPage(WebDriver driver) {
        super(driver);
    }

    public int getBookingsListSize() {
        driver.navigate().refresh();
        return allBookingsList.size();
    }

    public void gotoDashBoard() {
        dashBoardNavLink.click();
    }
}
