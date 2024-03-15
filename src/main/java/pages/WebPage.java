package pages;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebPage {
    protected static WebDriver driver;
    protected WebDriverWait wdWait;
    protected JavascriptExecutor jse;
    protected Actions actions;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        jse = (JavascriptExecutor) driver;
        wdWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    public void declineCookies() {
        String cookiesDeclineCssSelector = "#hs-eu-cookie-confirmation-button-group > a:nth-child(2)";
        if (isElementPresent(cookiesDeclineCssSelector)) {
            WebElement cookiesDecline = driver.findElement(By.cssSelector(cookiesDeclineCssSelector));
            cookiesDecline.click();
        }
    }

    public boolean doesElementExist(String cssSelector) {
        WebElement requiredElement = null;

        try {
            requiredElement = driver.findElement(By.cssSelector(cssSelector));
        } catch (NoSuchElementException ex) {
            System.out.println("Element with cssSelector: " + cssSelector + " was NOT found.");
        }

        return requiredElement != null;
    }

    public void waitUntilVisible(String cssSelector) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    public boolean waitUntilExistCss(String cssSelector) {
        try {
            wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    public boolean waitUntilClickable(WebElement element) {
        try {
            wdWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    public boolean waitUntilExistXpath(String xpath) {
        try {
            wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }


    public void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted execption got caught with message, " + ex.getMessage());
        }
    }

    public boolean isElementPresent(String cssSelector) {
        try {
            WebElement requiredElement = driver.findElement(By.cssSelector(cssSelector));
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    public void uploadFileUsingRobot(String fileName) {

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDir);

        String filePathSaparator = null;
        String os = System.getProperty("os.name");
        System.out.println("Operating System : " + os);

        if (os.contains("Windows")) {
            filePathSaparator = "\\";
        } else if (os.contains("MAC")) {
            filePathSaparator = "/";
        }

        String filePath = currentDir + filePathSaparator + "TestData" + filePathSaparator + fileName;
        System.out.println("Complete file path : " + filePath);

        StringSelection stringSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Robot robo = null;
        try {
            robo = new Robot();
        } catch (AWTException e) {
            System.out.println("Exception occured while creating Robot." + e.getMessage());
        }

        robo.setAutoDelay(1000);

        robo.keyPress(KeyEvent.VK_CONTROL);
        robo.keyPress(KeyEvent.VK_V);
        robo.keyRelease(KeyEvent.VK_CONTROL);
        robo.keyRelease(KeyEvent.VK_V);

        robo.keyPress(KeyEvent.VK_ENTER);
        robo.keyRelease(KeyEvent.VK_ENTER);
    }

    public boolean isMaximumLimitReached() {
        try {
            WebElement requiredElement = driver.findElement(By.cssSelector("some-selector"));
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    public static void takeDriverScreenshot(String failureMethodName) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File screenshot = tss.getScreenshotAs(OutputType.FILE);

        double randomNumber = Math.round(1000000);

        File destFile = new File("Screenshots\\" + failureMethodName + "-screenshot-" + String.valueOf(randomNumber) + ".png");

        //Copy file at destination
        try {
            FileUtils.copyFile(screenshot, destFile);
        } catch (IOException e) {
            System.out.println("Writing screenshot to hard disk failed.");
        }
    }

    public void takeElementScreenshot(WebElement elm) {
        TakesScreenshot tss = (TakesScreenshot) elm;
        File screenshot = tss.getScreenshotAs(OutputType.FILE);

        double randomNumber = Math.round(1000000);
        File destFile = new File("Element-screenshot-" + String.valueOf(randomNumber) + ".png");

        //Copy file at destination
        try {
            FileUtils.copyFile(screenshot, destFile);
        } catch (IOException e) {
            System.out.println("Writing screenshot to hard disk failed.");
        }
    }

    public boolean isPageLoaded() {
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15));
        try {
            fluentWait.until((driver) -> jse.executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    public void forceStopPageLoad() {
        jse.executeScript("window.stop()");
    }

    public void clickElementByJS(WebElement element) {
        jse.executeScript("arguments[0].click();", element);
    }

    public WebElement getRandomWebElementFromList(List<WebElement> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public void scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToPageBottom() {
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
