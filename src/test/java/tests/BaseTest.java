package tests;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverManager.getDriver();
        softAssert = new SoftAssert();
        driver.get("https://practicesoftwaretesting.com/#/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
