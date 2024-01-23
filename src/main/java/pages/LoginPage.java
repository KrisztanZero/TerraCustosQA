package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "login-username-or-email")
    private WebElement usernameField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit-button")
    private WebElement loginButton;

    @FindBy(id = "login-error-message")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);}

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getLoginErrorMessage(){return loginErrorMessage;}
}
