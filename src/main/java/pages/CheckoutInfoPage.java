package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInfoPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By webTitle = By.cssSelector("span[data-test='title']");

    public void enterFirstName(String firstName) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstName)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.lastName)).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.postalCode)).sendKeys(postalCode);
    }

    public void clickContinueBtn() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.continueBtn)).click();
    }

    public String getWebTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
}
