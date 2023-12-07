package actions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Navbar;

public class NavbarActions {
    private Navbar navbar;
    private WebDriverWait wait;
    private Logger logger;

    public NavbarActions(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.navbar = new Navbar(driver);
        this.wait = wait;
        this.logger = logger;
    }

    public String getWelcomeMessage(){
        WebElement welcomeMessage = waitForElementToBeClickable(navbar.getWelcomeMessage());
        return welcomeMessage.getText();
    }

    public void navigateProfileFromWelcomeMessage(){
        WebElement welcomeMessage = waitForElementToBeClickable(navbar.getWelcomeMessage());
        welcomeMessage.click();
    }

    public void clickOnBrandName(){
        WebElement navbarBrandName = waitForElementToBeClickable(navbar.getBrandName());
        navbarBrandName.click();
    }

    public void clickOnProfileIcon(){
        WebElement navbarProfileIcon = waitForElementToBeClickable(navbar.getProfileIcon());
        navbarProfileIcon.click();
    }

    private WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
