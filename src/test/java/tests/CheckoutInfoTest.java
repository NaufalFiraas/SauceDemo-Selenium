package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.Log;
import utils.Verify;

public class CheckoutInfoTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    private void preCondition() {
        LoginPage loginPage = new LoginPage(this.driver);
        ProductsPage productsPage = new ProductsPage(this.driver);
        CartPage cartPage = new CartPage(this.driver);

        Log.info("CHECKOUT INFO - Enter credentials for login");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        Log.info("CHECKOUT INFO - Add item to cart");
        productsPage.addToCart(0);
        Log.info("CHECKOUT INFO - Navigate to cart page");
        productsPage.clickCart();
        Log.info("CHECKOUT INFO - Click checkout on cart page");
        cartPage.clickCheckoutBtn();
    }

    @Test(groups = {"regression"})
    public void continueWithEmptyFirstName() {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(this.driver);
        Log.info("CHECKOUT INFO PAGE - CONTINUE WITH EMPTY FIRST NAME");
        Log.info("CHECKOUT INFO PAGE - Enter last name: Firaas");
        checkoutInfoPage.enterLastName("Firaas");
        Log.info("CHECKOUT INFO PAGE - Enter postal code: 65111");
        checkoutInfoPage.enterPostalCode("65111");
        Log.info("CHECKOUT INFO PAGE - Click continue button");
        checkoutInfoPage.clickContinueBtn();

        String webTitle = checkoutInfoPage.getWebTitle();
        Log.info("CHECKOUT INFO PAGE - Get web title: " + webTitle);
        String currentUrl = checkoutInfoPage.getCurrentUrl();
        Log.info("CHECKOUT INFO PAGE - Get web url: " + currentUrl);

        Verify.verifyEquals("Checkout: Your Information", webTitle, "Verify user still on Checkout Information page by web title");
        Verify.verifyContains("checkout-step-one", currentUrl, "Verify user still on checkout info page by URL");
    }

    @Test(groups = {"regression"})
    public void continueWithEmptyLastName() {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(this.driver);

        Log.info("CHECKOUT INFO PAGE - CONTINUE WITH EMPTY LAST NAME");
        Log.info("CHECKOUT INFO PAGE - Enter first name");
        checkoutInfoPage.enterFirstName("Naufal");
        Log.info("CHECKOUT INFO PAGE - Enter postal code");
        checkoutInfoPage.enterPostalCode("65111");
        Log.info("CHECKOUT INFO PAGE - Click continue button");
        checkoutInfoPage.clickContinueBtn();

        String webTitle = checkoutInfoPage.getWebTitle();
        Log.info("CHECKOUT INFO PAGE - Get web title:" + webTitle);
        String currentUrl = checkoutInfoPage.getCurrentUrl();
        Log.info("CHECKOUT INFO PAGE - Get current url: " + currentUrl);

        Verify.verifyEquals("Checkout: Your Information", webTitle, "Verify user still on checkout information page by web title");
        Verify.verifyContains("checkout-step-one", currentUrl, "Verify user still on checkout info page by url");
    }

    @Test(groups = {"regression"})
    public void continueWithEmptyPostalCode() {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(this.driver);

        Log.info("CHECKOUT INFO PAGE - CONTINUE WITH EMPTY POSTAL CODE");
        Log.info("CHECKOUT INFO PAGE - Enter first name");
        checkoutInfoPage.enterFirstName("Naufal");
        Log.info("CHECKOUT INFO PAGE - Enter last name");
        checkoutInfoPage.enterLastName("Firaas");
        Log.info("CHECKOUT INFO PAGE - Click continue button");
        checkoutInfoPage.clickContinueBtn();

        String webTitle = checkoutInfoPage.getWebTitle();
        Log.info("CHECKOUT INFO PAGE - Get web title: " + webTitle);
        String currentUrl = checkoutInfoPage.getCurrentUrl();
        Log.info("CHECKOUT INFO PAGE - Get current url: " + currentUrl);

        Verify.verifyEquals("Checkout: Your Information", webTitle, "Verify user still on checkout info page by web title");
        Verify.verifyContains("checkout-step-one", currentUrl, "Verify user still on checkout info page by url");
    }

    @Test(groups = {"smoke", "regression"})
    public void continueWithInputAllField() {
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(this.driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(this.driver);

        Log.info("CHECKOUT INFO PAGE - CONTINUE WITH INPUT ALL FIELD");
        Log.info("CHECKOUT INFO PAGE - Enter first name");
        checkoutInfoPage.enterFirstName("Naufal");
        Log.info("CHECKOUT INFO PAGE - Enter last name");
        checkoutInfoPage.enterLastName("Firaas");
        Log.info("CHECKOUT INFO PAGE - Enter postal code");
        checkoutInfoPage.enterPostalCode("65111");
        Log.info("CHECKOUT INFO PAGE - Click continue button");
        checkoutInfoPage.clickContinueBtn();

        String webTitle = checkoutOverviewPage.getWebTitle();
        Log.info("CHECKOUT INFO PAGE - Get webTitle: " + webTitle);
        String currentUrl = checkoutOverviewPage.getCurrentUrl();
        Log.info("CHECKOUT INFO PAGE - Get web url: " + currentUrl);

        Verify.verifyEquals("Checkout: Overview", webTitle, "Verify web title to make sure user navigated to checkout overview page");
        Verify.verifyContains("checkout-step-two", currentUrl, "Verify URL to make sure user navigated to checkout overview page");
    }
}
