package utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Base extends WebDriverHandler{

    private static final Logger LOGGER = Logger.getLogger(Base.class.getName());
    /**
     * This method is used for getting the WebElement in the browser's screen
     *
     * @param locator String xpath of the WebElement
     * @return WebElement by xpath
     */
    public WebElement findElement(String locator) {
        return getDriver().findElement(By.xpath(locator));
    }

    /**
     * This method is checks if the WebElement is visible and if true clicking the WebElement is possible
     *
     * @param locator String xpath of the WebElement
     */
    public void clickOnElementIfVisible(String locator) {
        LOGGER.info("Finding Web element to click");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        findElement(locator).click();
        LOGGER.info("Web element clicked");
    }

    /**
     * This method is used for getting a text of WebElement
     *
     * @param locator String xpath of the WebElement
     * @return text of the WebElement
     */
    public String getText(String locator) {
        return findElement(locator).getText();
    }

    /**
     * This method is used for typing a text in the WebElement
     *
     * @param locator String xpath of the WebElement
     * @param text to type in the WebElement
     */
    public void enterText(String locator, String text) {
        LOGGER.info("Typing text");
        findElement(locator).sendKeys(text);
        LOGGER.info("Text typed");
    }

    /**
     * This method is used for verifying that WebElement is visible
     *
     * @param locator String xpath of the WebElement
     */
    public void verifyIsDisplayed(String locator) {
        LOGGER.info("Checking if Web element is visible");
        Assertions.assertTrue(findElement(locator).isDisplayed());
        LOGGER.info("Web element is visible");
    }
}
