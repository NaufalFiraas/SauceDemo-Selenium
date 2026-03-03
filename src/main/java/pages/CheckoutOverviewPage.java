package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;
import java.util.List;

public class CheckoutOverviewPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By webTitle = By.cssSelector("span[data-test='title']");
    private final By inventName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By inventPrice = By.cssSelector("div[data-test='inventory-item-price']");
    private final By removeBtn = By.id("remove");
    private final By totalPrice = By.cssSelector("div[data-test='subtotal-label']");
    private final By finishBtn = By.id("finish");

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getWebTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

    public String getItemName(int index) {
        List<WebElement> productNames = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        Log.info("ITEM INDEX: " + index + " | ITEM NAME: " + productNames.get(index).getText());
        return productNames.get(index).getText();
    }

    public Float getItemPrice(int index) {
        List<WebElement> productPrices = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventPrice));
        return Float.valueOf(productPrices.get(index).getText().replace("$", ""));
    }

    public void clickProductName(int index) {
        List<WebElement> productNames = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        productNames.get(index).click();
    }

    public void clickRemove() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.removeBtn)).click();
    }

    public String getTotalPrice() {
        String totalPrice = this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.totalPrice)).getText();
        return totalPrice.replace("Item total: $", "");
    }

    public void clickFinishBtn() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.finishBtn)).click();
    }
}
