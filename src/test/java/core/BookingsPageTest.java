package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BookingsPage;

public class BookingsPageTest extends BaseTest{

    protected BookingsPage bookingsPage;

    @BeforeTest
    public void intilization(){
        bookingsPage = PageFactory.initElements(driver,BookingsPage.class);
    }

    @Test(priority = 0)
    public void upcomingBookingsWithFilter(){
        bookingsPage.bookingPageOption();
        bookingsPage.searchBooking();
        Assert.assertEquals(bookingsPage.getTotal(),1);

    }
    @Test(priority = 1)
    public void pastBookingWithFilter(){
        bookingsPage.setClearAll();
        bookingsPage.pastBooking();
        Assert.assertEquals(bookingsPage.getTotal(),3);
    }

    @Test(priority = 2)
    public void getDateRangeWithFilter(){
        bookingsPage.dateRangeOption();
        bookingsPage.dateRangeOption();
        bookingsPage.dateRangeSelection();
        Assert.assertEquals(bookingsPage.dateRangeText(),"Mar 11 — Mar 30, 2024");
    }


}
