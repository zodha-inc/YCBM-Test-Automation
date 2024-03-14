package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPreferencesPage extends WebPage {

    @FindBy(css = "span[class='avatar_img__2RCXJ']")
    protected WebElement profileButton;

    @FindBy(css = "a[href='/#/account']")
    protected WebElement account;

    @FindBy(css = "a[href='#/account?show=preferences']")
    protected WebElement preferences;

    @FindBy(css = "input[id='radiodark']")
    protected WebElement darkMode;

    @FindBy(css = "input[id='radiolight']")
    protected WebElement lightMode;

    public AccountPreferencesPage(WebDriver driver) {
        super(driver);
    }

    public void profileButtonIcon() {
        profileButton.click();
        sleepInSeconds(2);
    }

    public void accountOption() {
        account.click();
        sleepInSeconds(2);

        preferences.click();
        sleepInSeconds(3);

        darkMode.click();
        sleepInSeconds(10);

        lightMode.click();
        sleepInSeconds(10);


    }
}
