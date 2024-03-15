package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = "a[href='#/account']")
    protected List<WebElement> backToAccountOverview;

    @FindBy(css = "input[name='familyName']")
    protected WebElement lastNameField;

//    @FindBy(name = "familyName")
//    protected WebElement lastName;

    @FindBy(css = "div>button[data-testid='update-preferences-button']")
    protected WebElement updatePreferencesButton;


    @FindBy(css = "input[name='organisation']")
    protected WebElement organizationField;

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
        sleepInSeconds(5);

        lightMode.click();
        sleepInSeconds(5);

        backToAccountOverview.get(6).click();
        sleepInSeconds(3);
    }

    public void updateAccount() {

        lastNameField.sendKeys("JMird");
        sleepInSeconds(2);

        organizationField.sendKeys("Vantage");
        sleepInSeconds(3);

        updatePreferencesButton.click();
        sleepInSeconds(2);
    }
}
