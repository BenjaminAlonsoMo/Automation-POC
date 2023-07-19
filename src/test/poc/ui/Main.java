package challenge.ui;

import challenge.ui.pages.CheckoutInformation;
import challenge.ui.pages.Login;
import challenge.ui.pages.Products;
import challenge.ui.pages.ShoppingCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Main {
    WebDriver driver;
    String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        String driverPath = System.getProperty("user.dir") + File.separator + "src/test/resources" + File.separator + "driver" + File.separator + "chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://www.saucedemo.com/";
        setImplicitWait(30);
        driver.get(baseUrl);
    }

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
