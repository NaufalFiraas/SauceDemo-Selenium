package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Log;
import utils.Verify;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void loginValidUsernamePassword() {
        LoginPage loginPage = new LoginPage(this.driver);
        ProductsPage productsPage = new ProductsPage(this.driver);

        Log.info("LOGIN - LOGIN WITH VALID USERNAME PASSWORD");
        Log.info("LOGIN - Enter valid username 'standard_user' and valid password 'secret_sauce'");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        Log.info("LOGIN - Clicking login button");
        loginPage.clickLoginBtn();
        String productsPageTitle = productsPage.getPageTitle();
        Log.info("LOGIN - Validate user redirected to product page, expected 'Products' title match with actual " + productsPageTitle);
        Verify.verifyEquals("Products", productsPageTitle, "Login with Valid Username and Password");
        String productsPageUrl = productsPage.getPageUrl();
        Log.info("LOGIN - Validate user redirected to URL: https://www.saucedemo.com/inventory.html");
        Verify.verifyContains("https://www.saucedemo.com/inventory.html", productsPageUrl, "Login with valid username and password");
    }

    @Test(groups = {"regression"})
    public void loginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(this.driver);

        Log.info("LOGIN - LOGIN WITH INVALID USERNAME");
        Log.info("LOGIN - Input credentials with invalid username");
        loginPage.enterUsername("standard_users");
        loginPage.enterPassword("secret_sauce");

        Log.info("LOGIN - Click login button");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getErrorMessage();
        Log.info("LOGIN - Get error message: " + errorMsg);
        String pageUrl = loginPage.getPageUrl();
        Log.info("LOGIN - Get page URL: " + pageUrl);

        Log.info("LOGIN - Validate user failed to login and still on login page with url: https://www.saucedemo.com/");
        Verify.verifyEquals("https://www.saucedemo.com/", pageUrl, "Validate URL still on login page: https://www.saucedemo.com/");

        Log.info("LOGIN - Validate user failed to login and error message is displayed correctly");
        Verify.verifyEquals("\uD83D\uDE2D: Username and password do not match any user in this service", errorMsg, "Validate error message when login with invalid username");
    }

    @Test(groups = {"regression"})
    public void loginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(this.driver);

        Log.info("LOGIN - LOGIN WITH INVALID PASSWORD");
        Log.info("LOGIN - Input credentials with invalid password");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauces");

        Log.info("LOGIN - Click on login button");
        loginPage.clickLoginBtn();

        String pageUrl = loginPage.getPageUrl();
        Log.info("LOGIN - Get page url: " + pageUrl);

        String errorMsg = loginPage.getErrorMessage();
        Log.info("LOGIN - Get error message: " + errorMsg);

        Log.info("LOGIN - Validate user failed to login and still on login page with url: https://www.saucedemo.com/");
        Verify.verifyEquals("https://www.saucedemo.com/", pageUrl, "Validate URL still on login page: https://www.saucedemo.com/");

        Log.info("LOGIN - Validate user failed to login and error message is displayed correctly");
        Verify.verifyEquals("\uD83D\uDE2D: Username and password do not match any user in this service", errorMsg, "Validate error message when login with invalid password");
    }
}
