package core;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DuplicateExistingBooking;

public class DuplicateExistingBookingTest extends BaseTest {

    protected DuplicateExistingBooking duplicateExistingBooking;


    @Test(priority = 1)
    public void createDuplicateBookingTest() {

        duplicateExistingBooking = PageFactory.initElements(driver, DuplicateExistingBooking.class);
        duplicateExistingBooking.createDuplicateBooking();
        duplicateExistingBooking.sleepInSeconds(2);

        SoftAssert softAssert = new SoftAssert();
        String actualSuccessMessage = driver.findElement(By.cssSelector("div.styledWrapper.toast-content")).getText();
        String expectedSuccessMessage = "Your booking page has been saved";
        softAssert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Success message mismatch");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void switchToGridListViewTest() {

        duplicateExistingBooking = PageFactory.initElements(driver, DuplicateExistingBooking.class);
        duplicateExistingBooking.switchToGridListView();
        duplicateExistingBooking.sleepInSeconds(2);

    }

    @Test(priority = 3)
    public void settingBookingOnlineOfflineTest() {

        duplicateExistingBooking = PageFactory.initElements(driver, DuplicateExistingBooking.class);
        duplicateExistingBooking.settingBookingOnlineOffline();
        duplicateExistingBooking.sleepInSeconds(2);
    }

}
