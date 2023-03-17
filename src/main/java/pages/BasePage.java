package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return driver.findElement(locator);
    }

    protected void clickOnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        //getElement(locator).click();
    }

    protected void typeIn(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    protected String getTextFromElement(By locator) {
        return getElement(locator).getText();
    }

    protected void clickOnRandomElement(By locator) {
        Random random = new Random();
        Utils.waitForSeconds(1);
        List<WebElement> list = driver.findElements(locator);
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();
    }

    protected void hover(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element).build().perform();
    }

    protected void hoverAndClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element).click().build().perform();
    }

    protected boolean matchesExpectedText(By locator, String expectedText) {
        WebElement element = getElement(locator);
        if (element.getText().equals(expectedText)) {
            log.info("PASSED - Text found in element: " + element.getText() + " MATCHES expected text: " + expectedText);
            return true;
        } else {
            log.error("FAILED - Text found in element: " + element.getText() + " DOES NOT MATCH expected text: " + expectedText);
        }
        return false;
    }

    protected boolean matchesExpectedNumberOfProducts(By locator1, By locator2, By locator3) {
        List<WebElement> productPricesInBasket = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator1));
        List<WebElement> productValuesInBasket = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator2));
        List<Double> doubleProductPrices = new ArrayList<>();
        List<Double> doubleProductValues = new ArrayList<>();
        double sum = 0;
        for (int i = 1; i < productPricesInBasket.size(); i += 3) {
            doubleProductPrices.add(Double.parseDouble(productPricesInBasket.get(i).getText().replaceAll("\\D", "")));
        }
        for (int j = 2; j < productValuesInBasket.size(); j += 3) {
            doubleProductValues.add(Double.parseDouble(productValuesInBasket.get(j).getText().replaceAll("\\D", "")));
        }
        for (int k = 0; k < doubleProductPrices.size(); k++) {
            sum += doubleProductValues.get(k) / doubleProductPrices.get(k);
        }

        double sum2 = Double.parseDouble(getTextFromElement(locator3));

        if (sum == sum2) {
            log.info("PASSED - number of products in cart: " + sum + " MATCHES number of products in bubble cart: " + sum2);
            return true;
        } else {
            log.error("FAILED - number of products in cart: " + sum + " DOES NOT MATCH number of products in bubble cart: " + sum2);
            return false;
        }
    }

    protected boolean matchesExpectedValue(By locator1, By locator2) {
        List<WebElement> productPricesInBasket = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator1));
        double sum = 0;
        for (int i = 2; i < productPricesInBasket.size(); i += 3) {
            sum += Double.parseDouble(productPricesInBasket.get(i).getText().replaceAll("\\D", ""));
        }
        double sum2 = Double.parseDouble(getTextFromElement(locator2).replaceAll("\\D", ""));
        if (sum == sum2) {
            log.info("PASSED - subtotal value of products in cart: " + sum + " MATCHES total value of all products in cart: " + sum2);
            return true;
        } else {
            log.error("FAILED - subtotal value of products in cart: " + sum + " DOES NOT MATCH total value of all products in cart: " + sum2);
            return false;
        }
    }

    protected boolean errorFieldNotDisplayed(By locator) {
        if (driver.findElements(locator).isEmpty()) {
            log.info("PASSED - Used format in test is valid.");
            return true;
        } else {
            log.error("FAILED - Used format in test is mot valid.");
        }
        return false;
    }

    public int countNumberOfProducts(By locator) {
        List<WebElement> productPricesInBasket = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        int j = 0;
        for (int i = 0; i < productPricesInBasket.size(); i++) {
            j++;
        }
        return j;
    }

    public boolean oneIntIsLessThanAnother(int a, int b) {
        if (a > b) {
            log.info("PASSED - Number of products after checking Drill: " + b + " IS LESS than number of product before checking Drill: " + a);
            return true;
        } else {
            log.error("FAILED -  Number of products after checking Drill: " + b + " IS NOT LESS than number of product before checking Drill: " + a);
            return false;
        }
    }
}
