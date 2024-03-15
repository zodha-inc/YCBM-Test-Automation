package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BookingPage;

public class BookingPageTest extends BaseTest {

    protected BookingPage bookingPage;

    @BeforeClass
    public void initialize() {
        bookingPage = PageFactory.initElements(driver, pages.BookingPage.class);
    }
    @Test(priority = 1)
    public void searchSpecificBookingPageTest() {
        bookingPage.searchParticularBookingPage();
        if(bookingPage.getSearchedBookingPageText().contains("no booking")) {
            Assert.assertEquals(bookingPage.getNoBookingPageText(), bookingPage.getSearchedBookingPageText());
        }else {
            Assert.assertEquals(bookingPage.getBookingPageToSearchValue(),
                    bookingPage.getSearchedBookingPageText());
        }

    }

    @Test(priority = 2)
    public void deleteSpecificBookingPageTest() {
        bookingPage.deleteSpecificBookingPage("test124");
        int sizeBeforeDelete = bookingPage.getBookingPagesSize();
        int sizeAfterDelete = bookingPage.getBookingPagesSizeAfterDelete();
        if(sizeBeforeDelete == sizeAfterDelete) {
            Assert.assertFalse(sizeBeforeDelete > sizeAfterDelete, "specific booking page to delete not found");
        } else {
            Assert.assertTrue(sizeBeforeDelete > sizeAfterDelete, "Specific booking page was successfully deleted.");
        }
    }
}
