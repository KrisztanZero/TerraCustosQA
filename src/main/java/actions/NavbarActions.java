package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Navbar;

public class NavbarActions {
    private Navbar navbar;

    public NavbarActions(WebDriver driver) {
        this.navbar = new Navbar(driver);
    }

    public void getProfileFromWelcomeMessage(){
        WebElement welcomeMessage = navbar.getWelcomeMessage();
        welcomeMessage.click();
    }

}
