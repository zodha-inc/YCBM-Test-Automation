package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookingCalenderPage;
import pages.BookingsPage;


public class CreateAndDeleteBookingTest extends BaseTest {

    protected BookingsPage bookingsPage;
    protected BookingCalenderPage bookingCalenderPage;

    @BeforeClass
    public void initPage() {
        bookingCalenderPage = PageFactory.initElements(driver, BookingCalenderPage.class);
        bookingsPage = PageFactory.initElements(driver, BookingsPage.class);
    }

    @Test(priority = 1)
    public void testCreateBooking() {
        String firstName = "AA" + Math.random() * 1000;;
        String email = firstName + "@gmial.com";

        dashboardPage.gotoBookingsPage();
        int originBookingsListSize = bookingsPage.getBookingsListSize();
        System.out.println("OriginBookingListSize:" + originBookingsListSize);

        bookingsPage.gotoDashBoard();
        String dashBoardWindowHandle = dashboardPage.gotoSelectedBookingPage();

        bookingCalenderPage.createBooking(firstName, email);
        softAssert.assertEquals(bookingCalenderPage.getBookingConfirmMessage(),
                "You'll receive a notification confirming the meeting details shortly.");

        bookingCalenderPage.close(dashBoardWindowHandle);
        dashboardPage.gotoBookingsPage();
        softAssert.assertEquals(bookingsPage.getBookingsListSize() - originBookingsListSize, 1,
                "Verify if one new booking added into booking list");

        softAssert.assertAll();
    }

    @Test(priority = 22, enabled = false)
    public void testDeleteExistingBooking() {
        dashboardPage.gotoBookingsPage();
    }

}
