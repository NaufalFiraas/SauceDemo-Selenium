package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.Log;
import utils.Verify;

import java.util.HashMap;

public class CheckoutOverviewTest extends BaseTest {
    private String firstProductName;
    private String secondProductName;
    private Float firstProductPrice;
    private Float secondProductPrice;

    @BeforeMethod(alwaysRun = true)
    private void preConditions() {
        LoginPage loginPage = new LoginPage(this.driver);
        ProductsPage productsPage = new ProductsPage(this.driver);
        CartPage cartPage = new CartPage(this.driver);
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - PRE CONDITIONS");
        Log.info("CHECKOUT OVERVIEW PAGE - Input login credentials");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        Log.info("CHECKOUT OVERVIEW PAGE - Click login btn");
        loginPage.clickLoginBtn();

        HashMap<String, String> firstProduct = productsPage.addToCart(0);
        HashMap<String, String> secondProduct = productsPage.addToCart(1);
        this.firstProductName = firstProduct.get("name");
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product name: " + this.firstProductName);
        this.secondProductName = secondProduct.get("name");
        Log.info("CHECKOUT OVERVIEW PAGE - Get second product name: " + this.secondProductName);
        this.firstProductPrice = Float.valueOf(firstProduct.get("price"));
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product price: " + this.firstProductPrice);
        this.secondProductPrice = Float.valueOf(secondProduct.get("price"));
        Log.info("CHECKOUT OVERVIEW PAGE - Get second product price: " + this.secondProductPrice);
        Log.info("CHECKOUT OVERVIEW PAGE - Click cart icon");
        productsPage.clickCart();

        Log.info("CHECKOUT OVERVIEW PAGE - Click checkout button");
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product name on cart page: " + cartPage.getInventName(0));
        Log.info("CHECKOUT OVERVIEW PAGE - Get second product name on cart page: " + cartPage.getInventName(1));
        cartPage.clickCheckoutBtn();

        Log.info("CHECKOUT OVERVIEW PAGE - Complete form on checkout info page");
        checkoutInfoPage.enterFirstName("Naufal");
        checkoutInfoPage.enterLastName("Firaas");
        checkoutInfoPage.enterPostalCode("65111");
        Log.info("CHECKOUT OVERVIEW PAGE - Click continue button");
        checkoutInfoPage.clickContinueBtn();
    }

    @Test(groups = {"smoke", "regression", "debug"})
    public void verifyItemNames() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - VERIFY ITEM NAMES IS CORRECT");
        String firstProductName = cop.getItemName(0);
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product name: " + firstProductName);
        String secondProductName = cop.getItemName(1);
        Log.info("CHECKOUT OVERVIEW PAGE - Get second product name: " + secondProductName);

        Verify.verifyEquals(this.firstProductName, firstProductName, "Verify first product name is correct");
        Verify.verifyEquals(this.secondProductName, secondProductName, "Verify second product name is correct");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyItemPrices() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - VERIFY ITEM PRICES IS CORRECT");
        String firstProductPrice = cop.getItemPrice(0).toString();
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product price: " + firstProductPrice);
        String secondProductPrice = cop.getItemPrice(1).toString();
        Log.info("CHECKOUT OVERVIEW PAGE - Get second product price: " + secondProductPrice);

        Verify.verifyEquals(this.firstProductPrice.toString(), firstProductPrice, "Verify first product price");
        Verify.verifyEquals(this.secondProductPrice.toString(), secondProductPrice, "Verify second product price");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyTotalPrice() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - VERIFY TOTAL PRICE IS CORRECT");
        String expectedTotal = String.valueOf(this.firstProductPrice + this.secondProductPrice);
        String actualTotal = String.valueOf(cop.getItemPrice(0) + cop.getItemPrice(1));
        Log.info("CHECKOUT OVERVIEW PAGE - Get total price: " + actualTotal);

        Verify.verifyEquals(expectedTotal, actualTotal, "Verify total price is correct");
    }

    @Test(groups = {"regression"})
    public void removeItemAndVerifyTotalPrice() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - REMOVE ITEM AND VERIFY TOTAL PRICE");
        Log.info("CHECKOUT OVERVIEW PAGE - Click second product name");
        cop.clickProductName(1);
        Log.info("CHECKOUT OVERVIEW PAGE - Click remove button");
        cop.clickRemove();
        Log.info("CHECKOUT OVERVIEW PAGE - Back from detail page");
        this.driver.navigate().back();
        String firstProductPrice = cop.getItemPrice(0).toString();
        Log.info("CHECKOUT OVERVIEW PAGE - Get first product price: " + firstProductPrice);
        String totalPrice = cop.getTotalPrice();
        Log.info("CHECKOUT OVERVIEW PAGE - Get total price after delete second product: " + totalPrice);

        Verify.verifyEquals(firstProductPrice, totalPrice, "Verify total price is correct");
    }

    @Test(groups = {"regression"})
    public void removeAllItemAndVerifyUserCannotCheckout() {
        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT OVERVIEW PAGE - REMOVE ALL ITEM AND VERIFY USER CANNOT CHECKOUT");
        Log.info("CHECKOUT OVERVIEW PAGE - Click first product name");
        cop.clickProductName(1);
        Log.info("CHECKOUT OVERVIEW PAGE - Click remove button");
        cop.clickRemove();
        Log.info("CHECKOUT OVERVIEW PAGE - Back to checkout overview page");
        this.driver.navigate().back();
        Log.info("CHECKOUT OVERVIEW PAGE - Click second product name");
        cop.clickProductName(0);
        Log.info("CHECKOUT OVERVIEW PAGE - Click remove button");
        cop.clickRemove();
        Log.info("CHECKOUT OVERVIEW PAGE - Back to checkout overview page");
        this.driver.navigate().back();
        Log.info("CHECKOUT OVERVIEW PAGE - Click finish button");
        cop.clickFinishBtn();
        String webTitle = cop.getWebTitle();
        Log.info("CHECKOUT OVERVIEW PAGE - Get current web title: " + webTitle);
        String currentUrl = cop.getCurrentUrl();
        Log.info("CHECKOUT OVERVIEW PAGE - Get current web URL: " + currentUrl);

        Verify.verifyEquals("Checkout: Overview", webTitle, "Verify web title is still on checkout overview page");
        Verify.verifyContains("checkout-step-two", currentUrl, "Verify URL is still on checkout overview page");
    }

}
