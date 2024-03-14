package core;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.AccountPreferencesPage;

public class AccountPreferencesPageTest extends BaseTest {
    @Test(priority = 1)
    public void profileButtonIconCheck() {
        accountPreferencesPage = PageFactory.initElements(driver, AccountPreferencesPage.class);
        sleepInSeconds(2);
        accountPreferencesPage.profileButtonIcon();
    }

    @Test(priority = 2)
    public void accountOptionCheck() {
        accountPreferencesPage = PageFactory.initElements(driver, AccountPreferencesPage.class);
        sleepInSeconds(2);
        accountPreferencesPage.accountOption();
    }
}