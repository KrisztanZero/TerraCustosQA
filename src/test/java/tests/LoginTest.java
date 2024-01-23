package tests;

import actions.LoginActions;
import actions.NavbarActions;
import actions.ProfileOffCanvasActions;
import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriverException;
import testCases.LoginTestCases;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    private LoginTestCases loginTestCases;
    private NavbarActions navbarActions;
    private LoginActions loginActions;
    private ProfileOffCanvasActions profileOffCanvasActions;

    @BeforeAll
    public void InitComponents() {
        this.loginTestCases = new LoginTestCases(driver, wait, logger);
        this.navbarActions = new NavbarActions(driver, wait, logger);
        this.profileOffCanvasActions = new ProfileOffCanvasActions(driver, wait, logger);
        this.loginActions = new LoginActions(driver, wait, logger);
    }

    @BeforeEach
    public void BeforeTestLogin(){
        navbarActions.clickOnBrandName();
        navbarActions.clickOnProfileIcon();
        profileOffCanvasActions.clickOnLogin();
    }

    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Username_Password.csv", numLinesToSkip = 1)
    public void loginWithValidUsernameAndPassword(String username, String password) {
        try {
            logger.info("Perform a valid login with username and password.");
            test.log(Status.INFO, "Perform a valid login with username and password.");
            loginTestCases.performLogin(username, password);

            test.log(Status.INFO, "Get welcome message");
            String welcomeMessage = navbarActions.getWelcomeMessage();
            test.log(Status.INFO, "Welcome message: " + welcomeMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("Welcome " + username, welcomeMessage, "Incorrect username or password");
            test.log(Status.PASS, "Test passed");

            navbarActions.clickOnProfileIcon();
            profileOffCanvasActions.clickOnLogout();
        } catch (Exception e) {
            logger.error("Exception occurred in loginWithValidUsernameAndPassword: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Email_Password.csv", numLinesToSkip = 1)
    public void loginWithValidEmailAndPassword(String username, String email, String password) {
        try {
            logger.info("Perform valid login with email and password");
            test.log(Status.INFO, "Perform valid login with email and password");
            loginTestCases.performLogin(email, password);

            test.log(Status.INFO, "Get welcome message");
            String welcomeMessage = navbarActions.getWelcomeMessage();
            test.log(Status.INFO, "Welcome message: " + welcomeMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("Welcome " + username, welcomeMessage, "Incorrect success message");
            test.log(Status.PASS, "Test passed");

            navbarActions.clickOnProfileIcon();
            profileOffCanvasActions.clickOnLogout();
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithValidEmailAndPassword: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(3)
    @Test
    public void loginWithoutCredentials() {
        try {
            logger.info("Perform invalid login without credentials");
            test.log(Status.INFO, "Perform invalid login without credentials");
            loginTestCases.performLoginWithoutCredentials();

            test.log(Status.INFO, "Get error message");
            String errorMessage = loginActions.getErrorMessage();
            test.log(Status.INFO, "Error message: " + errorMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("Please fill in all fields.", errorMessage, "Incorrect error message");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithoutCredentials: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Invalid_Username.csv", numLinesToSkip = 1)
    public void loginWithInvalidUsername(String username, String password) {
        try {
            logger.info("Perform login with invalid username");
            test.log(Status.INFO, "Perform login with invalid username");
            loginTestCases.performLogin(username, password);

            test.log(Status.INFO, "Get error message");
            String errorMessage = loginActions.getErrorMessage();
            test.log(Status.INFO, "Error message: " + errorMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("An error occurred. Please try again.", errorMessage, "Incorrect error message");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithInvalidUsername: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(5)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWith_Invalid_Password.csv", numLinesToSkip = 1)
    public void loginWithInvalidPassword(String username, String password) {
        try {
            logger.info("Perform login with invalid password");
            test.log(Status.INFO, "Perform login with invalid password");
            loginTestCases.performLogin(username, password);

            test.log(Status.INFO, "Get error message");
            String errorMessage = loginActions.getErrorMessage();
            test.log(Status.INFO, "Error message: " + errorMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("An error occurred. Please try again.", errorMessage, "Incorrect error message");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithInvalidPassword: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(6)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginTestDataWithout_UsernameOrEmail.csv", numLinesToSkip = 1)
    public void loginWithoutUsernameOrEmail(String password) {
        try {
            logger.info("Perform login without username or email");
            test.log(Status.INFO, "Perform login without username or email");
            loginTestCases.performLoginWithoutUsernameOrEmail(password);

            test.log(Status.INFO, "Get error message");
            String errorMessage = loginActions.getErrorMessage();
            test.log(Status.INFO, "Error message: " + errorMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("Please enter a username or email.", errorMessage, "Incorrect error message");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithoutUsernameOrEmail: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }

    @Order(7)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithout_Password.csv", numLinesToSkip = 1)
    public void loginWithoutPassword(String username) {
        try {
            logger.info("Perform login without password");
            test.log(Status.INFO, "Perform login without password");
            loginTestCases.performLoginWithoutPassword(username);

            test.log(Status.INFO, "Get error message");
            String errorMessage = loginActions.getErrorMessage();
            test.log(Status.INFO, "Error message: " + errorMessage);

            test.log(Status.INFO, "Verifying assertion");
            Assertions.assertEquals("Please enter a password.", errorMessage, "Incorrect error message");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e) {
            logger.error("Exception occurred in loginWithoutPassword: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
            throw e;
        }
    }
}
