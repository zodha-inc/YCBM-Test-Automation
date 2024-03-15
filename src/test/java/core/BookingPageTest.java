package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BookingPage;

import java.time.Duration;

public class BookingPageTest extends BaseTest {

    protected BookingPage bookingPage;

    @Test(priority = 0)
    public void cancleBookingTest() {

        bookingPage = PageFactory.initElements(driver,BookingPage.class);
        bookingPage.cancleBooking();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toaster div.styledWrapper.toast-content")));
        String actualToastText = toastMessage.getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualToastText ,"The booking has been cancelled.");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void rebookCancelledBookingTest() {

        bookingPage = PageFactory.initElements(driver,BookingPage.class);
        bookingPage.rebookCancelledBooking();

        String expectedTitle = driver.getTitle();

        System.out.println(expectedTitle + " :Expected title of page");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedTitle,"Select a time · Black coffee");
        softAssert.assertAll();



    }

}
