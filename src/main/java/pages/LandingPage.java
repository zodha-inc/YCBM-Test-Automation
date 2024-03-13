package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends WebPage {

    @FindBy(xpath = "//h1[contains(text(), 'Log in to your account')]")
    protected WebElement pageHeader;


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return pageHeader.getText();
    }


}
