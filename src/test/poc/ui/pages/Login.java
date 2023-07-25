package poc.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import poc.ui.factory.DriverHelper;

public class Login extends DriverHelper {
  private By userName = By.id("user-name");
  private By userPass = By.id("password");
  private By loginBtn = By.id("login-button");

  public Login(WebDriver driver) {
    super(driver);
  }

  public void login_User(String usrName, String usrPass) {
    sendKeys(userName, usrName);
    sendKeys(userPass, usrPass);
    click(loginBtn);
  }
}
