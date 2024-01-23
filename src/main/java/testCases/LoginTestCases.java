package testCases;

import actions.LoginActions;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTestCases {

    private LoginActions loginActions;

    public LoginTestCases(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.loginActions = new LoginActions(driver, wait, logger);
    }

    public void performLogin(String usernameOrEmail, String password){
        loginActions.enterUsername(usernameOrEmail);
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();
    }

    public void performLoginWithoutCredentials(){loginActions.clickLoginButton();}

    public void performLoginWithoutUsernameOrEmail(String password){
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();}

    public void performLoginWithoutPassword(String username){
        loginActions.enterUsername(username);
        loginActions.clickLoginButton();}
}
