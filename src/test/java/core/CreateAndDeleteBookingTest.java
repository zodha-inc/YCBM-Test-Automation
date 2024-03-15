package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
        SoftAssert softAssert = new SoftAssert();
        String firstName = "Aa" + Math.round(Math.random()* 1000) ;;
        String email = firstName + "@gmail.com";

        dashboardPage.gotoBookingsPage();
        int originBookingsListSize = bookingsPage.getBookingsListSize();

        bookingsPage.gotoDashBoard();
        String dashBoardWindowHandle = dashboardPage.gotoSelectedBookingPage();

        bookingCalenderPage.createBooking(firstName, email);
        softAssert.assertEquals(bookingCalenderPage.getBookingConfirmMessage(),
                "You'll receive a notification confirming the meeting details shortly.");

        bookingCalenderPage.closeCurrentWindowThenSwitchToWindow(dashBoardWindowHandle);
        dashboardPage.gotoBookingsPage();
        softAssert.assertEquals(bookingsPage.getBookingsListSize() - originBookingsListSize, 1,
                "Verify if one new booking added into booking list");

        softAssert.assertTrue(bookingsPage.chooseSpecificBooking(firstName) != null,"Verify if the new booking inside booking list");

        softAssert.assertAll();
    }

    @Test(priority = 22)
    public void testDeleteExistingBooking() {
        SoftAssert sa = new SoftAssert();
        dashboardPage.gotoBookingsPage();
        int bookingsListSizeBeforeDelete = bookingsPage.getBookingsListSize();
        bookingsPage.deleteExistingBooking();

        sa.assertEquals(bookingsPage.getDeleteNoticeToasterText(),"The booking has been deleted.");
        int bookingsListSizeAfterDelete = bookingsPage.getBookingsListSize();
        sa.assertEquals(bookingsListSizeBeforeDelete-bookingsListSizeAfterDelete,1);
        sa.assertAll();
    }

}
