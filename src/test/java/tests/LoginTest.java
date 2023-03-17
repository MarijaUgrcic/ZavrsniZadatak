package tests;

import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        loginPage = new LoginPage(driver);
    }

    @Description("Negative cases Login test: Expected result - Error message is shown")
    @Epic("Negative cases Login test epic")
    @Story("Negative cases Login test story")
    @Test
    public void loginTestNegativeCaseUsernameBlankPasswordBlank() {
        loginPage.goToLoginForm()
                .loginUser("", "");
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameBlankPasswordBlank());
    }

    @Description("Negative cases Login test: Expected result - Error message is shown")
    @Epic("Negative cases Login test epic")
    @Story("Negative cases Login test story")
    @Test(dataProvider = "dpLoginTestUsernameInvalid", dataProviderClass = Utils.class)
    public void loginTestNegativeCaseUsernameInvalidPasswordValid(String username, String password) {
        loginPage.goToLoginForm()
                .loginUser(username, password);
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameInvalidPasswordValid());
    }

    @Description("Negative cases Login test: Expected result - Error message is shown")
    @Epic("Negative cases Login test epic")
    @Story("Negative cases Login test story")
    @Test(dataProvider = "dpLoginTestPasswordInvalid", dataProviderClass = Utils.class)
    public void loginTestNegativeCaseUsernameValidPasswordInvalid(String username, String password) {
        loginPage.goToLoginForm()
                .loginUser(username, password);
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameValidPasswordInvalid());
    }
}
