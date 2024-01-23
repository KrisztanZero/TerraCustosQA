package actions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;

public class RegistrationActions {

    private RegistrationPage registrationPage;
    private WebDriverWait wait;
    private Logger logger;

    public RegistrationActions(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.registrationPage = new RegistrationPage(driver);
        this.wait = wait;
        this.logger = logger;
    }

    public void enterUsername(String usernameOrEmail){
        WebElement usernameField = waitForElementToBeClickable(registrationPage.getUsernameFiled());
        sleep(300);
        usernameField.sendKeys(usernameOrEmail);
    }

    public void enterPassword(String password){
        WebElement passwordField = waitForElementToBeClickable(registrationPage.getPasswordField());
        sleep(300);
        passwordField.sendKeys(password);
    }

    public void enterEmail(String email){
        WebElement emailField = waitForElementToBeClickable(registrationPage.getEmailField());
        sleep(300);
        emailField.sendKeys(email);
    }

    public void clickCreateAccountButton() {
        WebElement loginButton = waitForElementToBeClickable(registrationPage.getCreateButton());
        sleep(300);
        loginButton.click();
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(registrationPage.getErrorMessage()));
        sleep(300);
        return errorMessageElement.getText();
    }

    private WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void sleep(Integer milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
