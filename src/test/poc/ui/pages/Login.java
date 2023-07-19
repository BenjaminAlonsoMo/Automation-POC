package challenge.ui.pages;

import challenge.ui.factory.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
