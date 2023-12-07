package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegistrationPage {
    @FindBy(id = "registration-username")
    private WebElement usernameFiled;

    @FindBy(id = "registration-password")
    private WebElement passwordField;

    @FindBy(id = "registration-email")
    private WebElement emailField;

    @FindBy(id = "create-account")
    private WebElement createButton;

    @FindBy(id = "registration-error-message")
    private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
