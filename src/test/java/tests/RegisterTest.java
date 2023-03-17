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

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Register user: Expected result - User is registered, text is present on the welcome page")
    @Epic("Register epic")
    @Story("Happy path register story")
    public void registerUserTest() {
        registerPage.goToRegisterPage()
                .registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        Assert.assertTrue(registerPage.isUserRegistered());
    }
}
