package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ProductsPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By webTitle = By.cssSelector(".title");
    private final By inventItems = By.cssSelector(".inventory_item");
    private final By inventName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By inventDesc = By.cssSelector("div[data-test='inventory-item-desc']");
    private final By inventPrice = By.cssSelector("div[data-test='inventory-item-price']");
    private final By inventImg = By.cssSelector("img[class='inventory_item_img']");
    private final By cartBadge = By.cssSelector("span[data-test='shopping-cart-badge']");
    private final By addToCartBtn = By.cssSelector("button[data-test*='add-to-cart']");
    private final By removeButton = By.cssSelector("button[data-test*='remove-']");
    private final By cartIcon = By.cssSelector("a[data-test='shopping-cart-link']");

    public String getPageTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.webTitle)).getText();
    }

    public String getPageUrl() {
        return this.driver.getCurrentUrl();
    }

    public boolean isInventoryListDisplayed() {
        List<WebElement> inventItems = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventItems));
        return !inventItems.isEmpty();
    }

    public boolean isNameDescPriceDisplayed() {
        List<WebElement> inventItems = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventItems));
        List<WebElement> inventNames = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        List<WebElement> inventDescs = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventDesc));
        List<WebElement> inventPrices = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventPrice));
        List<WebElement> inventImg = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventImg));

        Log.info("inventItems length: " + inventItems.size());
        Log.info("inventName length: " + inventNames.size());
        Log.info("inventDescs length: " + inventDescs.size());
        Log.info("inventPrices length: " + inventPrices.size());
        Log.info("inventImg length: " + inventImg.size());
        return inventImg.size() == inventItems.size() && inventNames.size() == inventItems.size() && inventDescs.size() == inventItems.size() && inventPrices.size() == inventItems.size();
    }

    public String clickProductName(int index) {
        List<WebElement> productsName = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        String productName = productsName.get(index).getText();
        productsName.get(index).click();
        return productName;
    }

    public String getProductNameDetailPage() {
        this.wait.until(ExpectedConditions.urlContains("inventory-item.html?id="));
        WebElement productsName = this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.inventName));
        return productsName.getText();
    }

    public String getDetailPageUrl() {
        this.wait.until(ExpectedConditions.urlContains("inventory-item.html?id="));
        return this.driver.getCurrentUrl();
    }

    public HashMap<String, String> addToCart(int index) {
        List<WebElement> productNames = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventName));
        String productName = productNames.get(index).getText();
        List<WebElement> productPrices = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.inventPrice));
        String productPrice = productPrices.get(index).getText().replace("$", "");
        HashMap<String, String> namePrice = new HashMap<>();
        namePrice.put("name", productName);
        namePrice.put("price", productPrice);
        List<WebElement> addToCartBtns = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.addToCartBtn));
        addToCartBtns.get(0).click();
        return namePrice;
    }

    public void removeFromCart(int index) {
        List<WebElement> removeFromCartBtns = this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.removeButton));
        removeFromCartBtns.get(index).click();
    }

    public String getCartBadge() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.cartBadge)).getText();
    }

    public void clickCart() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.cartIcon)).click();
    }
}