package challenge.ui.pages;

import challenge.ui.factory.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformation extends DriverHelper {

    private By elemFirstName = By.id("first-name");
    private By elemLastName = By.id("last-name");
    private By elemPostalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By thanksMessage = By.xpath("//*[contains(text(),'THANK YOU FOR YOUR ORDER')]");

    public CheckoutInformation(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        sendKeys(elemFirstName, firstName);
        sendKeys(elemLastName, lastName);
        sendKeys(elemPostalCode, postalCode);
        click(continueBtn);
    }

    public void finishProcess() {
        click(finishBtn);
        verifyElementIsDisplayed(thanksMessage);
    }
}
