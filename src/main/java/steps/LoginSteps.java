package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public void enterUsername(String username){
        WebElement usernameField = loginPage.getUsernameField();
        usernameField.sendKeys(username);
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
