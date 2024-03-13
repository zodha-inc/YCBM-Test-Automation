package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DuplicateExistingBooking extends WebPage {

    @FindBy(css = "div[class='Hint_hintContainer__2PIdR']>button[aria-label='More Actions']")
    protected WebElement actions;

    @FindBy(css = "div.menus_menuLabel__3acrt")
    protected List<WebElement> actionOptions;

    @FindBy(css = "input#ob_basics")
    protected WebElement bookingPageLinkField;

    @FindBy(css = "a#menu-timesGroup")
    protected WebElement durationAndDurability;

    @FindBy(css = "span.button_container__3jHr3.button_transparentContainer__3OzET.button_noPadding__BpdyD")
    protected WebElement editDurationAndDurability;

    @FindBy(css = "input[data-testid='MONDAY_end_time_0']")
    protected WebElement editHours;

    @FindBy(css = "button[data-testid='TUESDAY_delete_interval']")
    protected WebElement deleteBooking;

    @FindBy(css = "div.SettingsControl_settingsControl__1_cRQ")
    protected WebElement saveAllChanges;

    @FindBy(css = "a.NavBar_navItem__35LWf")
    protected WebElement gotoDasboard;

    @FindBy(css = "div.styledWrapper.toast-content")
    protected WebElement duplicateBookingSaved;

    public DuplicateExistingBooking(WebDriver driver) {
        super(driver);
    }

    public void successfulDuplicateBooking() {

        sleepInSeconds(2);
        actions.click();
        sleepInSeconds(2);
        actionOptions.get(3).click();
        sleepInSeconds(2);
        bookingPageLinkField.click();
        sleepInSeconds(2);
        bookingPageLinkField.sendKeys("aa");
        sleepInSeconds(2);
        durationAndDurability.click();
        sleepInSeconds(1);
        editDurationAndDurability.click();
        sleepInSeconds(1);
        editHours.click();
        sleepInSeconds(1);
        editHours.sendKeys("4");
        sleepInSeconds(1);
        editHours.click();
        sleepInSeconds(1);
        saveAllChanges.click();
        sleepInSeconds(1);
        gotoDasboard.click();
        sleepInSeconds(1);


    }
}
