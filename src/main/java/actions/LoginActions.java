package actions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

public class LoginActions {
    private LoginPage loginPage;
    private WebDriverWait wait;
    private Logger logger;

    public LoginActions(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.loginPage = new LoginPage(driver);
        this.wait = wait;
        this.logger = logger;
    }

    public void enterUsername(String usernameOrEmail){

        try {
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getUsernameField()));
            sleep(300);
            usernameField.sendKeys(usernameOrEmail);
            logger.info("Entered username or email: " + usernameOrEmail);
        } catch (WebDriverException e){
            logger.error("Exception occurred while entering username: " + e.getMessage());
        }

    }

    public void enterPassword(String password){
        try {
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getPasswordField()));
            sleep(300);
            passwordField.sendKeys(password);
            logger.info("Entered password: " + password);
        } catch (WebDriverException e) {
            logger.error("Exception occurred while entering password: " + e.getMessage());
        }

    }

    public void clickLoginButton() {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton()));
            sleep(300);
            loginButton.click();
            logger.info("Click on 'Login' button.");
        } catch (WebDriverException e){
            logger.error("Exception occurred while click on 'Login' button: " + e.getMessage());
        }

    }

    public String getErrorMessage(){
        try {
            WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMessage()));
            sleep(300);
            String errorMessage = errorMessageElement.getText();
            logger.info("Get error message: " + errorMessageElement.getText());
            return errorMessage;
        } catch (WebDriverException e){
            logger.error("Exception occurred while getting error message: " + e.getMessage());
            return "Failed to retrieve error message";
        }
    }

    private void sleep(Integer milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
