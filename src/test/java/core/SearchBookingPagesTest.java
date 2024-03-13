package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchBookingPage;

public class SearchBookingPagesTest extends BaseTest {

    protected  SearchBookingPage searchBookingPage;
    @Test
    public void searchBookingPages() {
        searchBookingPage = PageFactory.initElements(driver, SearchBookingPage.class);
        searchBookingPage.searchPages();
        Assert.assertEquals(searchBookingPage.getBookingPageToSearchValue(),
                searchBookingPage.getSearchedBookingPageText());
    }
}
