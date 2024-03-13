package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;

import java.time.Duration;

public class BookingPageTest extends BaseTest {

    protected BookingPage bookingPage;

    @Test
    public void goToCancleBookingOption() {

        bookingPage = PageFactory.initElements(driver,BookingPage.class);
        bookingPage.goToCancleBooking();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toaster div.styledWrapper.toast-content")));
        String actualText = toastMessage.getText();
        Assert.assertEquals(actualText ,"The booking has been cancelled.");
    }

}
