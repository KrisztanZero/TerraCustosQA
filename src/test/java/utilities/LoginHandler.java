package utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import actions.LoginActions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class LoginHandler {

    private LoginActions loginActions;
    private Properties testProperties;

    public LoginHandler(WebDriver driver, WebDriverWait wait, Properties testProperties, Logger logger) {
        this.loginActions = new LoginActions(driver, wait, logger);
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
