package poc.ui.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import poc.ui.factory.DriverHelper;

public class ShoppingCart extends DriverHelper {

  private String productList = "//*[contains(text(),'#')]";
  private String productListNumber =
      "//*[contains(text(),'#')]/parent::*/parent::*/preceding-sibling::div";
  private By checkoutBtn = By.id("checkout");

  public ShoppingCart(WebDriver driver) {
    super(driver);
  }

  public void verifyProduct(String product) {
    By finalProductList = By.xpath(productList.replaceAll("#", product));
    By finalProductListNumber = By.xpath(productListNumber.replaceAll("#", product));
    verifyElementIsDisplayed(finalProductList);
    verifyElementIsDisplayed(finalProductListNumber);
    int numberBage = Integer.parseInt(getText(finalProductListNumber));
    assertEquals(numberBage > 0, true);
  }

  public void checkoutTheProduct() {
    click(checkoutBtn);
  }
}
