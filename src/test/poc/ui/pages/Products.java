package poc.ui.pages;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import poc.ui.factory.DriverHelper;

public class Products extends DriverHelper {
  private String productImg = "img[alt='#']";
  private String productTitle = "//*[contains(text(),'#')]";
  private String productAddBtn =
      "//*[contains(text(),'#')]/parent::*/parent::div/following-sibling::div/button";
  private By shoppingCartBage = By.cssSelector("a[class='shopping_cart_link'] > span");

  public Products(WebDriver driver) {
    super(driver);
  }

  @Step("Verify Product is displayed")
  public void verifyProductIsDisplayed(String product) {
    By finalProductImg = By.cssSelector(productImg.replaceAll("#", product));
    By finalProductTitle = By.xpath(productTitle.replaceAll("#", product));
    verifyElementIsDisplayed(finalProductImg);
    verifyElementIsDisplayed(finalProductTitle);
  }

  public void addProduct(String product) {
    By finalProductAddBtn = By.xpath(productAddBtn.replaceAll("#", product));
    click(finalProductAddBtn);
  }

  public void verifyShoppingCartBage() {
    int numberBage = Integer.parseInt(getText(shoppingCartBage));
    assertEquals(numberBage > 0, true);
    click(shoppingCartBage);
  }
}
