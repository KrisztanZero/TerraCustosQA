package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProfileOffCanvas {

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "registration-button")
    private WebElement registrationButton;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    public ProfileOffCanvas(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
