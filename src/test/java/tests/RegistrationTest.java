package tests;

import actions.NavbarActions;
import actions.ProfileOffCanvasActions;
import actions.RegistrationActions;
import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import testCases.RegistrationTestCases;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest extends BaseTest {

    private NavbarActions navbarActions;
    private ProfileOffCanvasActions profileOffCanvasActions;
    private RegistrationTestCases registrationTestCases;
    private RegistrationActions registrationActions;

    @BeforeAll
    public void InitComponents() {
        navbarActions = new NavbarActions(driver, wait, logger);
        profileOffCanvasActions = new ProfileOffCanvasActions(driver, wait, logger);
        registrationTestCases = new RegistrationTestCases(driver, wait, logger);
        registrationActions = new RegistrationActions(driver, wait, logger);
    }

    @BeforeEach
    public void BeforeTestLogin(){
        navbarActions.clickOnBrandName();
        navbarActions.clickOnProfileIcon();
        profileOffCanvasActions.clickOnRegistration();
    }

    @Order(1)
    @Disabled("This test is currently disabled, accounts already registered and tested.")
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_Valid_Credentials.csv", numLinesToSkip = 1)
    public void registrationWithValidCredentials(String username, String password, String email) {
        try {
            logger.info("Perform registration with valid credentials.");
            test.log(Status.INFO, "Perform registration with valid credentials.");
            registrationTestCases.performRegistration(username, password, email);

            test.log(Status.INFO, "Verify successful registration.");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean presenceOfLoginButton = elementChecker.isElementPresent(By.id("login-submit-button"));
            Assertions.assertTrue(presenceOfLoginButton);
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithValidCredentials: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_ExistedUser.csv", numLinesToSkip = 1)
    public void registrationWithExistedUser(String username, String password, String email) {
        try {
            logger.info("Perform registration with existed user.");
            test.log(Status.INFO, "Perform registration with existed user.");
            registrationTestCases.performRegistration(username, password, email);

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Username or email already registered", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithExistedUser: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(3)
    @Test
    public void registrationWithoutCredentials() {
        try {
            logger.info("Perform registration without credentials.");
            test.log(Status.INFO, "Perform registration without  credentials.");
            registrationTestCases.performRegistrationWithoutCredentials();

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Please fill in all fields.", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithoutCredentials: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_Invalid_Email.csv", numLinesToSkip = 1)
    public void registrationWithInvalidEmail(String username, String password, String email) {
        try {
            logger.info("Perform registration with invalid email.");
            test.log(Status.INFO, "Perform registration with invalid email.");

            registrationTestCases.performRegistration(username, password, email);

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Please enter a valid email address", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithInvalidEmail: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(5)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Email.csv", numLinesToSkip = 1)
    public void registrationWithoutEmail(String username, String password) {
        try {
            logger.info("Perform registration without email.");
            test.log(Status.INFO, "Perform registration without email.");
            registrationTestCases.performRegistrationWithoutEmail(username, password);

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Please enter email", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithoutEmail: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(6)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Username.csv", numLinesToSkip = 1)
    public void registrationWithoutUsername(String password, String email) {
        try {
            logger.info("Perform registration without username.");
            test.log(Status.INFO, "Perform registration without username.");
            registrationTestCases.performRegistrationWithoutUsername(password, email);

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Please enter username", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithoutUsername: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }

    @Order(7)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Email.csv", numLinesToSkip = 1)
    public void registrationWithoutPassword(String username, String email) {
        try {
            logger.info("Perform registration without password.");
            test.log(Status.INFO, "Perform registration without password.");
            registrationTestCases.performRegistrationWithoutPassword(username, email);

            test.log(Status.INFO, "Verify error message.");

            String errorMessage = registrationActions.getErrorMessage();
            Assertions.assertEquals("Please enter password", errorMessage, "Incorrect error message.");
            test.log(Status.PASS, "Test passed");

        } catch (WebDriverException e) {
            logger.error("Exception occurred in registrationWithoutPassword: " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");

            throw e;
        }
    }
}
