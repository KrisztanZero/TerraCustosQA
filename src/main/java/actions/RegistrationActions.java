package actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;

public class RegistrationActions {

    private RegistrationPage registrationPage;
    private WebDriverWait wait;

    public RegistrationActions(WebDriver driver, WebDriverWait wait) {
        this.registrationPage = new RegistrationPage(driver);
        this.wait = wait;
    }

    public void enterUsername(String usernameOrEmail){
        WebElement usernameField = waitForElementToBeClickable(registrationPage.getUsernameFiled());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        usernameField.sendKeys(usernameOrEmail);
    }

    public void enterPassword(String password){
        WebElement passwordField = waitForElementToBeClickable(registrationPage.getPasswordField());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        passwordField.sendKeys(password);
    }

    public void enterEmail(String email){
        WebElement emailField = waitForElementToBeClickable(registrationPage.getEmailField());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.sendKeys(email);
    }

    public void clickCreateAccountButton() {
        WebElement loginButton = waitForElementToBeClickable(registrationPage.getCreateButton());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginButton.click();
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(registrationPage.getErrorMessage()));
        return errorMessageElement.getText();
    }

    private WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
