package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class CategoryDrillPage extends BasePage {
    private By categoryLink = By.cssSelector("a[class='nav-link dropdown-toggle']");
    private By powerToolsCategoryLink = By.xpath("//a[text()='Power Tools']");
    private By drillBox = By.cssSelector("input[data-test='category-11']");
    private By powerToolsCategoryProducts = By.cssSelector(".card-title");
    int numberOfProductsDrill;
    int numberOfProductsTotal;

    public CategoryDrillPage(WebDriver driver) {
        super(driver);
    }

    @Step("Selecting all products before and after selecting Drill category")
    public CategoryDrillPage boxDrillInPowerTools() {
        clickOnElement(categoryLink);
        Utils.waitForSeconds(1);
        hoverAndClick(powerToolsCategoryLink);
        Utils.waitForSeconds(1);
        numberOfProductsTotal = countNumberOfProducts(powerToolsCategoryProducts);
        clickOnElement(drillBox);
        Utils.waitForSeconds(2);
        numberOfProductsDrill = countNumberOfProducts(powerToolsCategoryProducts);
        return this;
    }

    @Step("Verifying if number od products is less after filtering")
    public boolean isNumberOfProductsLessAfterFilter() {
        return oneIntIsLessThanAnother(numberOfProductsTotal, numberOfProductsDrill);
    }

    public int getNumberOfProductsDrill() {
        return numberOfProductsDrill;
    }

    public int getNumberOfProductsTotal() {
        return numberOfProductsTotal;
    }
}
