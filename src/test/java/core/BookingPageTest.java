package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;

public class BookingPageTest extends BaseTest {

    protected BookingPage bookingPage;
    @Test
    public void searchSpecificBookingPageTest() {
        bookingPage = PageFactory.initElements(driver, pages.BookingPage.class);
        bookingPage.searchParticularBookingPage();
        if(bookingPage.getSearchedBookingPageText().contains("no booking")) {
            Assert.assertEquals(bookingPage.getNoBookingPageText(), bookingPage.getSearchedBookingPageText());
        }else {
            Assert.assertEquals(bookingPage.getBookingPageToSearchValue(),
                    bookingPage.getSearchedBookingPageText());
        }

    }
}
