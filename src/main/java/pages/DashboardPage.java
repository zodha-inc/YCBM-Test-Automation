package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends WebPage {


    @FindBy(css = "#hs-eu-cookie-confirmation-button-group > a:nth-child(2)")
    protected WebElement cookiesDecline;

    @FindBy(css = "div[aria-label='Upgrade account'] > div:first-child > button")
    protected WebElement closeUpgradeOfferModel;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void declineCookies() {
        if(isElementPresent("#hs-eu-cookie-confirmation-button-group > a:nth-child(2)")) {
            cookiesDecline.click();
        }
    }

    public void closeUpgradeOffer() {
        if(isElementPresent("div[aria-label='Upgrade account'] > div:first-child > button")) {
            closeUpgradeOfferModel.click();
        }
    }
}
