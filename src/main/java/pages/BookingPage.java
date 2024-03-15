package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BookingPage extends WebPage{
    @FindBy(css = "div[data-testid='dashboardProfiles'] > div > div")
    protected List<WebElement> bookingPagesList;

    protected List<WebElement> searchedBookingPages;

    protected String bookingPageToSearchValue;
    protected String searchedBookingPageText;
    protected String selectedStatus;
    protected String searchInput;
    protected  String noBookingPageText;

    protected int bookingPagesSize;

    protected int bookingPagesSizeAfterDelete;

    protected int deleteFlag;
    JavascriptExecutor jse;

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public void searchParticularBookingPage() {
        wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(bookingPagesList.size() > 0) {
            if(bookingPagesList.size() > 1) {
                WebElement clickFiltersButton = getWebElement("button[data-testid='profilesFilterButton']");
                wdWait.until(ExpectedConditions.elementToBeClickable(clickFiltersButton));
                clickFiltersButton.click();

                WebElement selectStatusDropdown = getWebElement("select[data-testid='profilesFilterStatus']");

                wdWait.until(ExpectedConditions.elementToBeClickable(selectStatusDropdown));

                selectStatusDropdown.click();

                List<WebElement> statusList = getElementsList("div[id='status-visible-menu'] > div > div");

                System.out.println(statusList.size());

                selectRandomStatusAndRetrieve(statusList);
                System.out.println("selected status is: " + selectedStatus);

                setSearchInputBasedOnStatus();
                System.out.println("input provided is: " +searchInput);

                WebElement searchBookingPageInput = getWebElement("input[data-testid='profilesFilterInput']");

                wdWait.until(ExpectedConditions.visibilityOf(searchBookingPageInput));
                searchBookingPageInput.sendKeys(searchInput);
                WebElement providedBookingPageInput = getWebElement("input[data-testid='profilesFilterInput']");

                bookingPageToSearchValue = providedBookingPageInput.getAttribute("value");

                searchAndRetrieveBookingPageText();
                System.out.println(searchedBookingPageText);

            }
        }
    }

    public void selectRandomStatusAndRetrieve(List<WebElement> statusList) {
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
    }

    public void setSearchInputBasedOnStatus() {
        if(selectedStatus.equalsIgnoreCase("Online")) {
            searchInput = "No food test";
        } else if (selectedStatus.equalsIgnoreCase("Offline")) {
            searchInput = "testAbc";
        } else if (selectedStatus.equalsIgnoreCase("Any status")) {
            searchInput = "test-1256";
        } else {
            searchInput = "test1232";
        }
    }

    public void searchAndRetrieveBookingPageText() {
        try {
            sleepInSeconds(2);
            searchedBookingPages = getElementsList
                    ("div[data-testid='dashboardProfiles'] a[data-testtype='profileTitle']");
            if(searchedBookingPages.size() > 0) {
                for (WebElement searchedElement : searchedBookingPages) {
                    String searchedTxt = searchedElement.getText().toLowerCase();
                    if(searchedTxt.contains(bookingPageToSearchValue)) {
                        searchedBookingPageText = searchedElement.getText().toLowerCase();
                    }
                }
                System.out.println("searched page text: " + searchedBookingPageText);
            }else {
                WebElement bookingPageNotFound = getWebElement("div[data-testid='dashboardNoProfiles'] > span");
                searchedBookingPageText = bookingPageNotFound.getText();
                noBookingPageText = "There are no booking pages that match the title " + bookingPageToSearchValue;

            }
        } catch (StaleElementReferenceException ex) {
            System.out.println(ex.getMessage());
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

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void deleteSpecificBookingPage(String bookingPageName) {
        jse = (JavascriptExecutor) driver;
        driver.navigate().refresh();
        System.out.println(bookingPagesList.size());
        bookingPagesSize = bookingPagesList.size();
        if(bookingPagesList.size() > 0) {
            for(WebElement element : bookingPagesList) {
                String text = element.findElement(By.cssSelector("a[data-testtype='profileTitle']")).getText();
                if(text.equals(bookingPageName)) {
                    WebElement clickActions = element.findElement(
                            By.cssSelector("button[aria-label='More Actions']"));
                    scrollIntoViewJS(clickActions);
                    clickActions.click();
                    WebElement deleteButton = getWebElement("div[id='reveal-root'] div[class='clickout'] > div > div > div:nth-child(6)") ;
                    deleteButton.click();
                    WebElement deleteConfirmCheckbox =driver.findElement(By.cssSelector("#typeDelete"));
                    jse.executeScript("arguments[0].click()",deleteConfirmCheckbox);
                    List<WebElement> buttons = getElementsList("div[aria-label='Delete profile modal'] button");
                    buttons.get(2).click();
                    break;
                }
            }
            sleepInSeconds(2);
            bookingPagesList = getElementsList("div[data-testid='dashboardProfiles'] > div > div");
            bookingPagesSizeAfterDelete = bookingPagesList.size();
        }
    }

    public int getBookingPagesSize() {
        return bookingPagesSize;
    }

    public int getBookingPagesSizeAfterDelete() {
        return bookingPagesSizeAfterDelete;
    }
 }
