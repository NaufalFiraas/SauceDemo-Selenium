package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By webTitle = By.cssSelector(".title");

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}