package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class WishListPage extends BasePage {
    private By categoryLink = By.cssSelector("a[class='nav-link dropdown-toggle']");
    private By handToolsCategoryLink = By.xpath("//a[text()='Hand Tools']");
    private By handToolsCategoryProducts = By.cssSelector(".card-title");
    private By randomChosenProduct = By.xpath("//h1[@data-test='product-name']");
    private By addToFavoriteButton = By.id("btn-add-to-favorites");
    private By userMenu = By.id("user-menu");
    private By myFavoritesLink = By.xpath("//a[text()='My favorites']");
    private By myFavoriteBox = By.xpath("//h5[@data-test='product-name']");
    String productNameInCart;

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Adding product in Favorites")
    public WishListPage addFavoriteProducts() {
        clickOnElement(categoryLink);
        Utils.waitForSeconds(1);
        hoverAndClick(handToolsCategoryLink);
        clickOnRandomElement(handToolsCategoryProducts);
        Utils.waitForSeconds(1);
        productNameInCart = getTextFromElement(randomChosenProduct).trim();
        clickOnElement(addToFavoriteButton);
        clickOnElement(userMenu);
        Utils.waitForSeconds(1);
        hoverAndClick(myFavoritesLink);
        return this;
    }

    @Step("Verifying if name of product is the same as its name in Favorites")
    public boolean matchOfProductNameTextInWishList() {
        return matchesExpectedText(myFavoriteBox, productNameInCart);
    }
}