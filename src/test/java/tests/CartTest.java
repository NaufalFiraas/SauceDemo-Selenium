package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Log;
import utils.Verify;

public class CartTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    private void preCondition() {
        LoginPage loginPage = new LoginPage(this.driver);

        Log.info("CART PAGE - Input credentials on login page");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @Test(groups = {"regression"})
    public void verifyUserCantContinueCheckout() {
        ProductsPage productsPage = new ProductsPage(this.driver);
        CartPage cartPage = new CartPage(this.driver);

        Log.info("CART PAGE - VERIFY USER CAN'T CONTINUE TO CHECKOUT PAGE WITHOUT SELECT ANY PRODUCTS");
        Log.info("CART PAGE - Click cart icon on products page");
        productsPage.clickCart();
        Log.info("CART PAGE - Click Checkout button on cart page");
        cartPage.clickCheckoutBtn();
        String pageTitle = cartPage.getPageTitle();
        Log.info("CART PAGE - Get page title: " + pageTitle);
        String currentUrl = cartPage.getCurrentUrl();
        Log.info("CART PAGE - Get current URL: " + currentUrl);

        Verify.verifyEquals("Your Cart", pageTitle, "Verify current page title is still on 'Your Cart'");
        Verify.verifyContains("/cart.html", currentUrl, "Verify URL is still on cart page");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyDisplayedItemsMatchwithSelectedItems() {
        ProductsPage productsPage = new ProductsPage(this.driver);
        CartPage cartPage = new CartPage(this.driver);

        Log.info("CART PAGE - VERIFY DISPLAYED ITEMS MATCH WITH SELECTED ITEMS BY USER");
        String firstSelectedItem = productsPage.addToCart(0).get("name");
        Log.info("CART PAGE - Add first item to cart: " + firstSelectedItem);
        String secondSelectedItem = productsPage.addToCart(1).get("name");
        Log.info("CART PAGE - Add second item to cart: " + secondSelectedItem);

        String firstDisplayedItem = cartPage.getInventName(0);
        Log.info("CART PAGE - Get first displayed item: " + firstDisplayedItem);
        String secondDisplayedItem = cartPage.getInventName(1);
        Log.info("CART PAGE - Get second displayed item: " + secondDisplayedItem);

        Verify.verifyEquals(firstSelectedItem, firstDisplayedItem, "Verify first displayed item match with first user selected item");
        Verify.verifyEquals(secondSelectedItem, secondDisplayedItem, "Verify second displayed item match with second user selected item");
    }

    @Test(groups = {"regression"})
    public void verifyUserCanRemoveProductFromCartPage() {
        ProductsPage productsPage = new ProductsPage(this.driver);
        CartPage cartPage = new CartPage(this.driver);

        Log.info("CART PAGE - VERIFY USER CAN REMOVE ITEM FROM CART PAGE");
        Log.info("CART PAGE - Add first item to cart");
        productsPage.addToCart(0);
        Log.info("CART PAGE - Add second item to cart");
        productsPage.addToCart(1);
        Log.info("CART PAGE - Click cart icon");
        productsPage.clickCart();

        Log.info(("CART PAGE - Remove second item from cart"));
        cartPage.clickRemoveBtn(1);
        Log.info("CART PAGE - Remove first item from cart");
        cartPage.clickRemoveBtn(0);
        boolean isInventListEmpty = cartPage.isInventListEmpty();

        Verify.verifyTrue(isInventListEmpty, "Verify item list is empty after user removing all items");
    }
}
