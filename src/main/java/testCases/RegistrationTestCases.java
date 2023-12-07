package testCases;

import actions.NavbarActions;
import actions.RegistrationActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationTestCases {
    private RegistrationActions registrationActions;

    public RegistrationTestCases(WebDriver driver, WebDriverWait wait) {
        this.registrationActions = new RegistrationActions(driver, wait);
    }

    public void performRegistration(String username, String password, String email){
        registrationActions.enterUsername(username);
        registrationActions.enterPassword(password);
        registrationActions.enterEmail(email);
        registrationActions.clickCreateAccountButton();
    }

    public void performRegistrationWithoutCredentials(){
        registrationActions.clickCreateAccountButton();
    }

    public void performRegistrationWithoutEmail(String username, String password){
        registrationActions.enterUsername(username);
        registrationActions.enterPassword(password);
        registrationActions.clickCreateAccountButton();
    }

    public void performRegistrationWithoutPassword(String username, String email){
        registrationActions.enterUsername(username);
        registrationActions.enterEmail(email);
        registrationActions.clickCreateAccountButton();
    }

    public void performRegistrationWithoutUsername(String password, String email){
        registrationActions.enterPassword(password);
        registrationActions.enterEmail(email);
        registrationActions.clickCreateAccountButton();
    }
}
