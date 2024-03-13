package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookingPage extends WebPage {

    @FindBy(css = "div[class='Container_container__Yd918 NavBar_innerNav__3La7h']>a:nth-child(3)")
    protected WebElement bookingPageButton;

    @FindBy(css = "div[class='Hint_hintContainer__2PIdR']>button[aria-label='Actions']")
    protected List <WebElement> bookingActionIconButton;

    @FindBy(css = "div[class='menus_menuPane__28hfJ']>div:nth-child(4)")
    protected WebElement bookingActionIconOptions;

    @FindBy(css = "div[style='margin-top: var(--sizes-x3); display: flex; justify-content: flex-end; gap: var(--sizes-x1); flex-wrap: wrap;']>button:nth-child(2)")
    protected WebElement bookingCancleButton;

    @FindBy(css = "tbody[class='Table_TableBody__1MlII']>tr >td:nth-child(7)")
    protected List <WebElement> bookingTableStatusOption;

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public void goToCancleBooking() {
        bookingPageButton.click();
        sleepInSeconds(2);

        for (int i = 0; i < bookingTableStatusOption.size(); i++) {

            String status = bookingTableStatusOption.get(i).getText();

            if (status.equals("Confirmed")) {
                bookingActionIconButton.get(i).click();
                sleepInSeconds(2);
                bookingActionIconOptions.click();
                sleepInSeconds(2);
                bookingCancleButton.click();
                sleepInSeconds(2);
            }
        }
    }



}

