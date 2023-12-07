package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

public class LoginActions {
    private LoginPage loginPage;
    private WebDriverWait wait;

    public LoginActions(WebDriver driver, WebDriverWait wait) {
        this.loginPage = new LoginPage(driver);
        this.wait = wait;
    }

    public void enterUsername(String usernameOrEmail){
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getUsernameField()));
        usernameField.sendKeys(usernameOrEmail);
    }

    public void enterPassword(String password){
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getPasswordField()));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));
        loginButton.click();
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMessage()));
        return errorMessageElement.getText();
    }
}
