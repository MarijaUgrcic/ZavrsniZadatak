package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {
    private By email = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("input[value='Login']");
    private By signInButton = By.cssSelector("a[data-test='nav-sign-in']");
    private By errorMessageEmail = By.xpath("//div[@data-test='email-error']/div");
    private By errorMessagePassword = By.xpath("//div[@data-test='password-error']/div");

    private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to login form")
    public LoginPage goToLoginForm() {
        clickOnElement(signInButton);
        return this;
    }

    @Step("Filling in login form with username and password")
    public LoginPage loginUser(String username, String password) {
        Utils.waitForSeconds(1);
        typeIn(email, username);
        typeIn(passwordField, password);
        clickOnElement(loginButton);
        return this;
    }
    @Step("Verifying if error messages is shown")
    public boolean loginNegativeCases() {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add("E-mail is required.");
        errorMessages.add("Password is required.");
        errorMessages.add("E-mail format is invalid.");
        errorMessages.add("Password length is invalid");

        if (((!driver.findElements(errorMessageEmail).isEmpty()) &&
                errorMessages.contains(getTextFromElement(errorMessageEmail))) ||
                ((!driver.findElements(errorMessagePassword).isEmpty()) &&
                        errorMessages.contains(getTextFromElement(errorMessagePassword)))) {
            log.info("PASSED - Error text found in element MATCHES expected text");
            return true;
        } else {
            log.info("FAILED - Form of text in Email filed and Password filed are valid");
            return false;
        }
    }
}

