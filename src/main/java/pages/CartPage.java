package pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.Locale;

public class CartPage extends BasePage {
    private By categoryLink = By.cssSelector("a[class='nav-link dropdown-toggle']");
    private By handToolsCategoryLink = By.xpath("//a[text()='Hand Tools']");
    private By powerToolsCategoryLink = By.xpath("//a[text()='Power Tools']");
    private By handToolsCategoryProducts = By.cssSelector(".card-title");
    private By powerToolsCategoryProducts = By.cssSelector(".card-body");
    private By plusButton = By.cssSelector("i[class='fa fa-plus']");
    private By addToCart = By.id("btn-add-to-cart");
    private By numberOfProductsInBasket = By.xpath("//input[@class='form-control quantity']");
    private By cartLink = By.xpath("//span[@id='lblCartCount']");
    private By subTotalPrice = By.xpath("//td[@class='col-md-2 align-middle']");
    private By subTotalValue = By.xpath("//td[@class='col-md-2 align-middle']");
    private By totalValue = By.xpath("//strong[text()='Total']/../following-sibling::td[@class='col-md-2']");
    private By proceedToCheckout1 = By.xpath("//button[text()='Proceed to checkout']");
    private By proceedToCheckout2 = By.xpath("//p[contains(text(),'logged in')]/../div/button");
    private By proceedToCheckout3 = By.cssSelector(".ng-valid + div button");
    private By payingMethod = By.id("payment-method");
    private By accountName = By.id("account-name");
    private By accountNumber = By.id("account-number");
    private By confirmButton = By.xpath("//button[contains(text(),'Confirm')]");
    private By messagePaymentSuccessful = By.xpath("//div[text()='Payment was successful']");

    Faker faker = new Faker(new Locale("en-US"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add products to cart")
    public CartPage addProductsToCart() {
        clickOnElement(categoryLink);
        Utils.waitForSeconds(1);
        hoverAndClick(handToolsCategoryLink);
        clickOnRandomElement(handToolsCategoryProducts);
        Utils.waitForSeconds(1);
        clickOnElement(plusButton);
        clickOnElement(addToCart);
        clickOnElement(categoryLink);
        Utils.waitForSeconds(1);
        hoverAndClick(powerToolsCategoryLink);
        clickOnRandomElement(powerToolsCategoryProducts);
        clickOnElement(addToCart);
        clickOnElement(cartLink);
        return this;
    }

    @Step("Verifying if total number of products in cart are the same as total number of products in cart bubble")
    public boolean areTotalNumberOfProductsTheSame() {
        return matchesExpectedNumberOfProducts(subTotalPrice, subTotalValue, cartLink);
    }

    @Step("Verifying if subtotal value of products is the same as total value of all products in cart")
    public boolean areTotalValuesTheSame() {
        return matchesExpectedValue(subTotalValue, totalValue);
    }

    @Step("Paying products")
    public CartPage payProducts() {
        clickOnElement(proceedToCheckout1);
        clickOnElement(proceedToCheckout2);
        clickOnElement(proceedToCheckout3);
        selectPayingMethod();
        typeIn(accountName, faker.name().fullName());
        typeIn(accountNumber, faker.number().digits(8));
        clickOnElement(confirmButton);
        return this;
    }

    @Step("Selection paying method")
    private void selectPayingMethod() {
        Select select = new Select(getElement(payingMethod));
        select.selectByValue("1: Bank Transfer");
    }

    @Step("Verifying if payment is successfully done")
    public boolean isPaymentDone() {
        return matchesExpectedText(messagePaymentSuccessful, "Payment was successful");
    }
}
