package testCases;

import actions.LoginActions;
import org.openqa.selenium.WebDriver;

public class LoginTestCases {

    private LoginActions loginActions;

    public LoginTestCases(WebDriver driver) {
        this.loginActions = new LoginActions(driver);
    }

    public void performLogin(String usernameOrEmail, String password){
        loginActions.enterUsername(usernameOrEmail);
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();
    }
}
