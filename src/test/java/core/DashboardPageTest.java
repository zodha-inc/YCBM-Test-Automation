package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;

@Test(priority = 0)
public class DashboardPageTest extends BaseTest {

    protected DashboardPage dashboardPage;
    @BeforeClass
    public void classSetup() {
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }

    @Test(priority = 1)
    public void successfulLogin() {

    }

    @Test(priority = 2)
    public void searchSpecificBookingPageTest() {
        dashboardPage.searchParticularBookingPage();
        if(dashboardPage.getSearchedBookingPageText().contains("no booking")) {
            Assert.assertEquals(dashboardPage.getNoBookingPageText(), dashboardPage.getSearchedBookingPageText());
        }else {
            Assert.assertEquals(dashboardPage.getBookingPageToSearchValue(),
                    dashboardPage.getSearchedBookingPageText());
        }

    }

    @Test(priority = 3)
    public void deleteSpecificBookingPageTest() {
        dashboardPage.deleteSpecificBookingPage("test124");
        int sizeBeforeDelete = dashboardPage.getBookingPagesSize();
        int sizeAfterDelete = dashboardPage.getBookingPagesSizeAfterDelete();
        if(sizeBeforeDelete == sizeAfterDelete) {
            Assert.assertFalse(sizeBeforeDelete > sizeAfterDelete, "specific booking page to delete not found");
        } else {
            Assert.assertTrue(sizeBeforeDelete > sizeAfterDelete, "Specific booking page was successfully deleted.");
        }
    }
}
