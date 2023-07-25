package poc.ui.factory;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class DriverHelper {
    public WebDriver driver;

    public DriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By elem, String key) {
        fluentWait(elem);
        driver.findElement(elem).sendKeys(key);
    }

    public String getText(By elem) {
        fluentWait(elem);
        return driver.findElement(elem).getText();
    }

    public void click(By elem) {
        fluentWait(elem);
        driver.findElement(elem).click();
    }

    public void verifyElementIsDisplayed(By elem) {
        fluentWait(elem);
        assertEquals(driver.findElement(elem).isDisplayed(), true);
    }

    public void fluentWait(By element) {
        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

    WebElement foo =
        wait.until(
            new Function<>() {
              public WebElement apply(WebDriver driver) {
                return driver.findElement(element);
              }
            });
    }
}
