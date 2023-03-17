package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import pages.WishListPage;

@Listeners(TestListener.class)
public class WishListTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;
    WishListPage wishListPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        wishListPage = new WishListPage(driver);
    }

    @Description("Wishlist test: Expected result - Product is bought")
    @Epic("Wishlist test epic")
    @Story("Happy path Wishlist test story")
    @Test
    public void wishlist() {
        registerPage.goToRegisterPage().registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        wishListPage.addFavoriteProducts();
        Assert.assertTrue(wishListPage.matchOfProductNameTextInWishList());
    }

}
