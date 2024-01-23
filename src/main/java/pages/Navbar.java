package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navbar {

    @FindBy(id = "brand-name")
    private WebElement brandName;

    @FindBy(id = "navbar-welcome-message")
    private WebElement welcomeMessage;

    @FindBy(id = "profile-icon")
    private WebElement profileIcon;

    public Navbar(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebElement getWelcomeMessage(){return welcomeMessage;}
    public WebElement getProfileIcon(){return profileIcon;}
    public WebElement getBrandName(){return brandName;}
}
