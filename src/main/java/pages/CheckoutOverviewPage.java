package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By webTitle = By.cssSelector("span[data-test='title']");

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getWebTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

}
