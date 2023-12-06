package utilities;

import org.openqa.selenium.WebDriver;
import actions.LoginActions;

import java.util.Properties;

public class LoginHandler {

    private LoginActions loginActions;
    private Properties testProperties;

    public LoginHandler(WebDriver driver, Properties testProperties) {
        this.loginActions = new LoginActions(driver);
        this.testProperties = testProperties;
    }

    public void performLogin() {
        String username = testProperties.getProperty("username");
        String password = testProperties.getProperty("password");

        loginActions.enterUsername(username);
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();
    }
}
