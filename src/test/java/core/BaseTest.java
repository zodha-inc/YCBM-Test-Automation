package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.DashboardPage;
import pages.LandingPage;
import utils.PropertiesUtils;

public class BaseTest {

    protected static WebDriver driver;
    protected LandingPage landingPage;
    protected DashboardPage dashboardPage;

    @BeforeTest
    public void goToLandingPage() {

        String testPlatform = System.getProperty("testPlatform");
        System.out.println(testPlatform + " from mvn command");

        PropertiesUtils.initializeLocalProperties();

        String targetBrowserFromConfigProperty = PropertiesUtils.getLocalConfigProperty("browser");
        String targetBrowserFromMvnCommand = System.getProperty("targetBrowser");

        String targetBrowser = "chrome"; // Default browser is Chrome

        if (targetBrowserFromMvnCommand != null) {
            targetBrowser = targetBrowserFromMvnCommand;
        } else if (targetBrowserFromConfigProperty != null) {
            targetBrowser = targetBrowserFromConfigProperty;
        }

        if (targetBrowser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (targetBrowser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (targetBrowser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://app.youcanbook.me/#/login");
        landingPage = PageFactory.initElements(driver, LandingPage.class);

        if (landingPage.waitUntilExistXpath("//h1[contains(text(), 'Log in to your account')]")) {
            landingPage.declineCookies();
            driver.findElement(By.name("email")).sendKeys(PropertiesUtils.getLocalConfigProperty("userId"));
            driver.findElement(By.name("password")).sendKeys(PropertiesUtils.getLocalConfigProperty("password"));
            driver.findElement(By.cssSelector("form > button:nth-child(3) > span")).click();
            dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
            dashboardPage.closeUpgradeOffer();
        }
    }

    @AfterTest
    public void tearDown() {
        sleepInSeconds(3);
        //driver.quit();
    }


    public void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ex) {
            System.out.println("Interrupted execption got caught with message, " + ex.getMessage());
        }
    }
}
