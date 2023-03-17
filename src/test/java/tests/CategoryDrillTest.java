package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CategoryDrillPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

@Listeners(TestListener.class)
public class CategoryDrillTest extends BaseTest {
    RegisterPage registerPage;
    LoginPage loginPage;
    CategoryDrillPage categoryDrillPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        Utils.waitForSeconds(2);
        categoryDrillPage = new CategoryDrillPage(driver);
    }

    @Description("Category number Drill products test: Expected result - Number of products in category Power Tools after checking Drill is less than before checking Drill")
    @Epic("Category number Drill products")
    @Story("Happy path Drill products story")
    @Test
    public void compareNumberOfProductsBasePageDrillPage() {
        registerPage.goToRegisterPage().registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        categoryDrillPage.
                boxDrillInPowerTools();
        System.out.println("Number of products in Category Power Tools is " + categoryDrillPage.getNumberOfProductsTotal());
        System.out.println("Number of products in Category Power Tools after checking Drill is " + categoryDrillPage.getNumberOfProductsDrill());
        Assert.assertTrue(categoryDrillPage.isNumberOfProductsLessAfterFilter());

    }
}
