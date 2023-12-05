package utilities;

import org.openqa.selenium.WebDriver;
import steps.LoginSteps;

import java.util.Properties;

public class LoginHandler {

    private LoginSteps loginSteps;
    private Properties testProperties;

    public LoginHandler(WebDriver driver, Properties testProperties) {
        this.loginSteps = new LoginSteps(driver);
        this.testProperties = testProperties;
    }

    public void performLogin() {
        String username = testProperties.getProperty("username");
        String password = testProperties.getProperty("password");

        loginSteps.enterUsername(username);
        loginSteps.enterPassword(password);
        loginSteps.clickLoginButton();
    }
}
