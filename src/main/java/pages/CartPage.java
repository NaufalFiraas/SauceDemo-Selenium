package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By checkoutBtn = By.cssSelector("button[data-test='checkout']");
    private final By webTitle = By.cssSelector("span[data-test='title']");
    private final By inventName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By inventItem = By.cssSelector("div[data-set='inventory-item']");
    private final By removeBtn = By.cssSelector("button[data-test*='remove']");

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

    public void clickCheckoutBtn() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.checkoutBtn)).click();
    }

    public String getInventName(int index) {
        List<WebElement> inventNames = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        return inventNames.get(index).getText();
    }

    public void clickRemoveBtn(int index) {
        List<WebElement> removeBtns = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.removeBtn));
        WebElement removeBtn = this.wait.until(ExpectedConditions.elementToBeClickable(removeBtns.get(index)));
        removeBtn.click();
    }

    public boolean isInventListEmpty() {
        List<WebElement> inventList = this.wait.until(ExpectedConditions.numberOfElementsToBe(this.inventItem, 0));
        return inventList.isEmpty();
    }
}
