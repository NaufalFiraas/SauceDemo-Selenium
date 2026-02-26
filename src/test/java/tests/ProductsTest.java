package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Log;
import utils.Verify;

public class ProductsTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    private void preCondition() {
        LoginPage loginPage = new LoginPage(this.driver);
        Log.info("PRODUCTS PAGE - Input credentials on login page");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyProductListDisplayed() {
        ProductsPage productsPage = new ProductsPage(this.driver);

        Log.info("PRODUCTS PAGE - Verify product list is displayed on Products Page");
        Verify.verifyTrue(productsPage.isInventoryListDisplayed(), "Verify product list is displayed");
    }


    @Test(groups = {"regression"})
    public void verifyAllProductsComponentDisplayed() {
        ProductsPage productsPage = new ProductsPage(this.driver);

        Log.info("PRODUCTS PAGE - Verify all product name, image, description, price is displayed");
        Verify.verifyTrue(productsPage.isNameDescPriceDisplayed(), "Verify all product name, image, description, price is displayed");
    }

    @Test(groups = {"regression"})
    public void verifyUsercanOpenDetailProductPage() {
        ProductsPage productsPage = new ProductsPage(this.driver);

        Log.info("PRODUCTS PAGE - VERIFY USER CAN OPEN DETAIL PRODUCT PAGE");
        Log.info("PRODUCTS PAGE - Click on product name");
        String productName = productsPage.clickProductName(1);
        String detailPageUrl = productsPage.getDetailPageUrl();
        String productNameDetailPage = productsPage.getProductNameDetailPage();

        Log.info("PRODUCTS PAGE - Verify URL already redirected to detail page");
        Verify.verifyContains("inventory-item.html?id=", detailPageUrl, "Verify URL already redirected to detail page");
        Log.info("PRODUCTS PAGE - Verify product name displayed on Detail Page match with selected product");
        Verify.verifyEquals(productName, productNameDetailPage, "Verify product name displayed on Detail Page match with selected product");
    }

    @Test(groups = {"regression"})
    public void verifyUserCanAddToCart() {
        ProductsPage productsPage = new ProductsPage(this.driver);

        Log.info("PRODUCTS PAGE - Adding two items into cart");
        productsPage.addToCart(0);
        productsPage.addToCart(1);
        String firstCartBadge = productsPage.getCartBadge();
        Log.info("PRODUCTS PAGE - Getting cart badge: " + firstCartBadge);

        Log.info("PRODUCTS PAGE - Remove one item from cart");
        productsPage.removeFromCart(1);
        String finalCartBadge = productsPage.getCartBadge();
        Log.info("PRODUCTS PAGE - Getting cart badge: " + finalCartBadge);

        Log.info("PRODUCTS PAGE - Verify cart badge is correct after adding two items into cart");
        Verify.verifyEquals("2", firstCartBadge, "Verify cart badge is correct after adding two items");
        Log.info("PRODUCTS PAGE - Verify cart badge is correct after removing one item from cart");
        Verify.verifyEquals("1", finalCartBadge, "Verify cart badge is correct after removing one item");
    }
}
