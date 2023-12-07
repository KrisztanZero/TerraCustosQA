package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

public class LoginActions {
    private LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public void enterUsername(String usernameOrEmail){
        WebElement usernameField = loginPage.getUsernameField();
        usernameField.sendKeys(usernameOrEmail);
    }

    public void enterPassword(String password){
        WebElement passwordField = loginPage.getPasswordField();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = loginPage.getLoginButton();
        loginButton.click();
    }
}
