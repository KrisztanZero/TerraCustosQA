package tests;

import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Navbar;
import pages.ProfileOffCanvas;
import testCases.LoginTestCases;

public class LoginTest extends BaseTest {

    private LoginTestCases loginTestCases;
    private Navbar navbar;
    private ProfileOffCanvas profileOffCanvas;

    public LoginTest() {
        this.loginTestCases = new LoginTestCases(driver);
        this.navbar = new Navbar(driver);
        this.profileOffCanvas = new ProfileOffCanvas(driver);
    }

    @BeforeEach
    public void BeforeTestLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(navbar.getProfileIcon())).click();
        wait.until(ExpectedConditions.elementToBeClickable(profileOffCanvas.getLoginButton())).click();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Username_Password.csv", numLinesToSkip = 1)
    public void login_With_Valid_Username_And_Password(String username, String password){
        logger.info("Perform a valid login with username and password.");
        test.log(Status.INFO,"Perform a valid login with username and password.");
        loginTestCases.performLogin(username, password);

        String welcomeMessage = navbar.getWelcomeMessage().getText();
        Assertions.assertEquals("Welcome " + username, welcomeMessage, "Incorrect username or password");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Email_Password.csv", numLinesToSkip = 1)
    public void login_With_Valid_Email_and_Password(String email, String password){
        logger.info("Perform valid login with email and password");
        test.log(Status.INFO, "Perform valid login with email and password");
        loginTestCases.performLogin(email, password);

        String welcomeMessage = navbar.getWelcomeMessage().getText();
        Assertions.assertEquals("Welcome " + email, welcomeMessage, "Incorrect email or password");
    }

    @Test
    public void loginWithoutCredentials(){
        logger.info("Perform invalid login without credentials");
        test.log(Status.INFO, "Perform invalid login without credentials");
        loginTestCases.performLogin("", "");


    }
}
