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
import java.util.Random;

public class SearchBookingPage extends WebPage{
    @FindBy(css = "div[data-testid='dashboardProfiles'] > div > div")
    protected List<WebElement> bookingPagesList;

    protected List<WebElement> searchedBookingPages;

    protected String bookingPageToSearchValue;
    protected String searchedBookingPageText;
    protected String selectedStatus;
    protected String searchInput;

    protected  String noBookingPageText;
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
                wdWait.until(ExpectedConditions.elementToBeClickable(selectStatusDropdown));
                selectStatusDropdown.click();
                List<WebElement> statusList = driver.findElements(
                        By.cssSelector("div[id='status-visible-menu'] > div > div"));
                System.out.println(statusList.size());
                Random random = new Random();
                selectedStatus = "Any status";
                searchInput = "test123";
                int randomStatus = random.nextInt(statusList.size());
                //randomStatus = 4;
                if(randomStatus >= 0 && randomStatus <= statusList.size() - 1) {
                    selectedStatus = statusList.get(randomStatus).getText();
                    statusList.get(randomStatus).click();
                }else {
                    statusList.get(0).click();
                    selectedStatus = "Any status";
                }
                if(selectedStatus.equalsIgnoreCase("Online")) {
                    searchInput = "No food test";
                } else if (selectedStatus.equalsIgnoreCase("Offline")) {
                    searchInput = "testAbc";
                } else if (selectedStatus.equalsIgnoreCase("Any status")) {
                    searchInput = "test-1256";
                } else {
                    searchInput = "test1232";
                }
                WebElement searchBookingPageInput = driver.findElement(
                        By.cssSelector("input[data-testid='profilesFilterInput']"));
                wdWait.until(ExpectedConditions.visibilityOf(searchBookingPageInput));
                searchBookingPageInput.sendKeys(searchInput);
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
                                searchedBookingPageText = searchedElement.getText().toLowerCase();
                            }
                        }
                        System.out.println("searched page text: " + searchedBookingPageText);
                    }else {
                        WebElement bookingPageNotFound = driver.findElement(
                                By.cssSelector("div[data-testid='dashboardNoProfiles'] > span"));
                        searchedBookingPageText = bookingPageNotFound.getText();
                        noBookingPageText = "There are no booking pages that match the title " + bookingPageToSearchValue;

                    }
                } catch (StaleElementReferenceException ex) {
                    System.out.println(ex.getMessage());
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

    public String getNoBookingPageText() {
        return noBookingPageText;
    }
}
