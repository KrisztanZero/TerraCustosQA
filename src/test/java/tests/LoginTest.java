package tests;

import actions.LoginActions;
import actions.NavbarActions;
import actions.ProfileOffCanvasActions;
import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import testCases.LoginTestCases;

public class LoginTest extends BaseTest {

    private LoginTestCases loginTestCases;
    private NavbarActions navbarActions;
    private LoginActions loginActions;
    private ProfileOffCanvasActions profileOffCanvasActions;

    public LoginTest() {
        this.loginTestCases = new LoginTestCases(driver, wait);
        this.navbarActions = new NavbarActions(driver, wait);
        this.profileOffCanvasActions = new ProfileOffCanvasActions(driver, wait);
        this.loginActions = new LoginActions(driver, wait);
    }

    @BeforeEach
    public void BeforeTestLogin(){
        navbarActions.clickOnProfileIcon();
        profileOffCanvasActions.clickOnLogin();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Username_Password.csv", numLinesToSkip = 1)
    public void login_With_Valid_Username_And_Password(String username, String password){
        logger.info("Perform a valid login with username and password.");
        test.log(Status.INFO,"Perform a valid login with username and password.");
        loginTestCases.performLogin(username, password);

        String welcomeMessage = navbarActions.getWelcomeMessage();
        Assertions.assertEquals("Welcome " + username, welcomeMessage, "Incorrect username or password");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Email_Password.csv", numLinesToSkip = 1)
    public void login_With_Valid_Email_and_Password(String email, String password){
        logger.info("Perform valid login with email and password");
        test.log(Status.INFO, "Perform valid login with email and password");
        loginTestCases.performLogin(email, password);

        String welcomeMessage = navbarActions.getWelcomeMessage();
        Assertions.assertEquals("Welcome " + email, welcomeMessage, "Incorrect success message");
    }

    @Test
    public void login_Without_Credentials(){
        logger.info("Perform invalid login without credentials");
        test.log(Status.INFO, "Perform invalid login without credentials");
        loginTestCases.performLogin("", "");

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Please fill in all fields.", errorMessage, "Incorrect error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Invalid_Password.csv", numLinesToSkip = 1)
    public void login_With_Invalid_Password(String username, String password){
        logger.info("Perform login with invalid password");
        test.log(Status.INFO, "Perform login with invalid password");
        loginTestCases.performLogin(username, password);

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("An error occurred. Please try again.", errorMessage, "Incorrect error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWithout_UsernameOrEmail.csv", numLinesToSkip = 1)
    public void login_Without_Username_Or_Email(String password){
        logger.info("Perform login without username or email");
        test.log(Status.INFO, "Perform login without username or email");
        loginTestCases.performLogin("", password);

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Please enter a username or email.", errorMessage, "Incorrect error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithout_Password.csv", numLinesToSkip = 1)
    public void login_Without_Password(String username){
        logger.info("Perform login without password");
        test.log(Status.INFO, "Perform login without password");
        loginTestCases.performLogin(username, "");

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Please enter a password.", errorMessage, "Incorrect error message");
    }
}
