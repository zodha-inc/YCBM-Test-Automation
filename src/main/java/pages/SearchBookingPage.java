package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchBookingPage extends WebPage{
    @FindBy(css = "div[data-testid='dashboardProfiles'] > div > div")
    protected List<WebElement> bookingPagesList;

    protected List<WebElement> searchedBookingPages;

    protected String bookingPageToSearchValue;
    protected String searchedBookingPageText;
    public SearchBookingPage(WebDriver driver) {
        super(driver);
    }

    public void searchPages() {
        wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(bookingPagesList.size() > 0) {
            if(bookingPagesList.size() > 1) {
                WebElement clickFiltersButton = driver.findElement(
                        By.cssSelector("button[data-testid='profilesFilterButton']"));
                wdWait.until(ExpectedConditions.elementToBeClickable(clickFiltersButton));
                clickFiltersButton.click();
                WebElement selectStatusDropdown = driver.findElement(
                        By.cssSelector("select[data-testid='profilesFilterStatus']"));
                Select statusDropdown = new Select(selectStatusDropdown);

                // Select by visible text
                statusDropdown.selectByVisibleText("Online");
                WebElement searchBookingPageInput = driver.findElement(
                        By.cssSelector("input[data-testid='profilesFilterInput']"));
                wdWait.until(ExpectedConditions.visibilityOf(searchBookingPageInput));
                searchBookingPageInput.sendKeys("test123");
                //searchBookingPageInput.sendKeys("test1815");
                WebElement providedBookingPageInput = driver.findElement(
                        By.cssSelector("input[data-testid='profilesFilterInput']"));
                bookingPageToSearchValue = providedBookingPageInput.getAttribute("value");
                try {
                     sleepInSeconds(2);
                     searchedBookingPages = driver.findElements(
                            By.cssSelector
                                    ("div[data-testid='dashboardProfiles'] a[data-testtype='profileTitle']"));
                    if(searchedBookingPages.size() > 0) {
                        for (WebElement searchedElement : searchedBookingPages) {
                            String searchedTxt = searchedElement.getText().toLowerCase();
                            if(searchedTxt.contains(bookingPageToSearchValue)) {
                                searchedBookingPageText = searchedElement.getText();
                            }
                        }
                        System.out.println("searched page text" + searchedBookingPageText);
                    }
                } catch (StaleElementReferenceException ex) {
                    System.out.println(ex.getMessage());
//
                }

            }

        }
    }
    public String getBookingPageToSearchValue() {
        return bookingPageToSearchValue;
    }
    public String getSearchedBookingPageText() {
        return searchedBookingPageText;
    }
}
