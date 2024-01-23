package actions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProfileOffCanvas;

public class ProfileOffCanvasActions {
    private ProfileOffCanvas profileOffCanvas;
    private WebDriverWait wait;
    private Logger logger;

    public ProfileOffCanvasActions(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.profileOffCanvas = new ProfileOffCanvas(driver);
        this.wait = wait;
        this.logger = logger;
    }

    public void clickOnLogin(){
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(profileOffCanvas.getLoginButton()));
            loginButton.click();
            logger.info("Click on 'Login' button.");
        } catch (WebDriverException e){
            logger.error("Exception occurred while click on 'Login' button: " + e.getMessage());
        }
    }

    public void clickOnRegistration(){
        try {
            WebElement registrationButton = wait.until(ExpectedConditions.elementToBeClickable(profileOffCanvas.getRegistrationButton()));
            registrationButton.click();
            logger.info("Click on 'Registration' button.");
        } catch (WebDriverException e){
            logger.error("Exception occurred while click on 'Registration' button: " + e.getMessage());
        }

    }

    public void clickOnLogout(){
        try {
            WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(profileOffCanvas.getLogoutButton()));
            logoutButton.click();
            logger.info("Click on 'Logout' button.");
        } catch (WebDriverException e) {
            logger.error("Exception occurred while click on 'Logout' button: " + e.getMessage());
        }

    }
}
