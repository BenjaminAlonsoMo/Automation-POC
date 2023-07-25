package poc.ui;

import io.qameta.allure.*;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import poc.ui.pages.CheckoutInformation;
import poc.ui.pages.Login;
import poc.ui.pages.Products;
import poc.ui.pages.ShoppingCart;

public class Main {
  WebDriver driver;
  String baseUrl;

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "https://www.saucedemo.com/";
    setImplicitWait(30);
    driver.get(baseUrl);
  }

  @Description("General requirements for test case")
  @Severity(SeverityLevel.NORMAL)
  @TmsLink("123456")
  @Feature("Example")
  @Test
  public void verifyInputAndSubmitButtonAreDisplayed() {
    Login login = new Login(driver);
    Products products = new Products(driver);
    ShoppingCart shoppingCart = new ShoppingCart(driver);
    CheckoutInformation checkoutInformation = new CheckoutInformation(driver);
    String product = "Sauce Labs Backpack";
    String fisrtName = "Benja";
    String lastName = "Alonso";
    String postalCode = "06450";
    login.login_User("standard_user", "secret_sauce");
    products.verifyProductIsDisplayed(product);
    products.addProduct(product);
    products.verifyShoppingCartBage();
    shoppingCart.verifyProduct(product);
    shoppingCart.checkoutTheProduct();
    checkoutInformation.fillInformation(fisrtName, lastName, postalCode);
    shoppingCart.verifyProduct(product);
    checkoutInformation.finishProcess();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    Thread.sleep(3000);
    driver.quit();
  }

  public void setImplicitWait(int time) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
  }
}
