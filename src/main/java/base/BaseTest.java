package base;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    @Getter
    protected WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://saucedemo.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
