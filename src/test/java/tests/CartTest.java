package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;
    CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    @Description("Cart test: Expected result - Product is bought")
    @Epic("Cart test epic")
    @Story("Happy path cart test story")
    public void addProductsToCart() {
        registerPage.goToRegisterPage().registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        cartPage.addProductsToCart();
        Utils.waitForSeconds(2);
        Assert.assertTrue(cartPage.areTotalNumberOfProductsTheSame());
        Assert.assertTrue(cartPage.areTotalValuesTheSame());

        cartPage.payProducts();
        Assert.assertTrue(cartPage.isPaymentDone());

    }

}
