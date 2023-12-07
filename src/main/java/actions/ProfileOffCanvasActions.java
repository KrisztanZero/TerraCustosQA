package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProfileOffCanvas;

public class ProfileOffCanvasActions {
    private ProfileOffCanvas profileOffCanvas;
    private WebDriverWait wait;

    public ProfileOffCanvasActions(WebDriver driver, WebDriverWait wait) {
        this.profileOffCanvas = new ProfileOffCanvas(driver);
        this.wait = wait;
    }

    public void clickOnLogin(){
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(profileOffCanvas.getLoginButton()));
        loginButton.click();
    }
}
