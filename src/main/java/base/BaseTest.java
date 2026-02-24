package base;

import com.aventstack.extentreports.ExtentTest;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.Log;

@Listeners(utils.ExtentTestListener.class)
public class BaseTest {
    @Getter
    protected WebDriver driver;
    protected ExtentTest test;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Log.info("Open webdriver");
        this.driver = new ChromeDriver();
        Log.info("Open browser");
        this.driver.manage().window().maximize();
        Log.info("Navigate to 'https://saucedemo.com'");
        this.driver.get("https://saucedemo.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (this.driver != null) {
            Log.info("Quitting session and close browser");
            this.driver.quit();
        }
    }
}
