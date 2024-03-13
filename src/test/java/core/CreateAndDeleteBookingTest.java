package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookingCalenderPage;
import pages.BookingsPage;


public class CreateAndDeleteBookingTest extends BaseTest{

    protected BookingsPage bookingsPage;
    protected BookingCalenderPage bookingCalenderPage;

    @BeforeClass
    public void initPage() {
        bookingCalenderPage = PageFactory.initElements(driver,BookingCalenderPage.class);
        bookingsPage = PageFactory.initElements(driver,BookingsPage.class);
    }

    @Test(priority = 1)
    public void testCreateBooking() {
        System.out.println("inside");
        dashboardPage.gotoSelectedBookingPage();
        sleepInSeconds(2);
        bookingCalenderPage.createBooking("AA","aa@gmail.com");
        Assert.assertEquals( bookingCalenderPage.getBookingConfirmMessage(),
                "You'll receive a notification confirming the meeting details shortly.");
    }

    @Test(priority = 22,enabled = false)
    public void testDeleteExistingBooking() {
        bookingsPage.gotoBookingsPage();
    }

}
