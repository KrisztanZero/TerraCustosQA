package tests;

import actions.NavbarActions;
import actions.ProfileOffCanvasActions;
import actions.RegistrationActions;
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
import org.openqa.selenium.By;
import testCases.RegistrationTestCases;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest extends BaseTest {

    private NavbarActions navbarActions;
    private ProfileOffCanvasActions profileOffCanvasActions;
    private RegistrationTestCases registrationTestCases;
    private RegistrationActions registrationActions;

    @BeforeAll
    public void InitComponents() {
        navbarActions = new NavbarActions(driver, wait);
        profileOffCanvasActions = new ProfileOffCanvasActions(driver, wait);
        registrationTestCases = new RegistrationTestCases(driver, wait);
        registrationActions = new RegistrationActions(driver, wait);
    }

    @BeforeEach
    public void BeforeTestLogin(){
        navbarActions.clickOnBrandName();
        navbarActions.clickOnProfileIcon();
        profileOffCanvasActions.clickOnRegistration();
    }

    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_Valid_Credentials.csv", numLinesToSkip = 1)
    public void registrationWith_Valid_Credentials(String username, String password, String email){
        logger.info("Perform registration with valid credentials.");
        test.log(Status.INFO,"Perform registration with valid credentials.");
        registrationTestCases.performRegistration(username,password,email);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        boolean presenceOfLoginButton = elementChecker.isElementPresent(By.id("login-submit-button"));
        Assertions.assertTrue(presenceOfLoginButton);
    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_ExistedUser.csv", numLinesToSkip = 1)
    public void registrationWith_Existed_User(String username, String password, String email){
        logger.info("Perform registration with existed user.");
        test.log(Status.INFO,"Perform registration with existed user.");
        registrationTestCases.performRegistration(username,password,email);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Username or email already registered",errorMessage, "Incorrect error message.");
    }

    @Order(3)
    @Test
    public void registrationWithout_Credentials(){
        logger.info("Perform registration without credentials.");
        test.log(Status.INFO,"Perform registration without  credentials.");
        registrationTestCases.performRegistrationWithoutCredentials();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Please fill in all fields.",errorMessage, "Incorrect error message.");
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWith_Invalid_Email.csv", numLinesToSkip = 1)
    public void registrationWith_Invalid_Email(String username, String password, String email){
        logger.info("Perform registration with invalid email.");
        test.log(Status.INFO,"Perform registration with invalid email.");

        registrationTestCases.performRegistration(username,password,email);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Please enter a valid email address",errorMessage, "Incorrect error message.");
    }

    @Order(5)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Email.csv", numLinesToSkip = 1)
    public void registrationWithout_Email(String username, String password){
        logger.info("Perform registration without email.");
        test.log(Status.INFO,"Perform registration without email.");
        registrationTestCases.performRegistrationWithoutEmail(username,password);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Please enter email",errorMessage, "Incorrect error message.");
    }
    @Order(6)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Username.csv", numLinesToSkip = 1)
    public void registrationWithout_Username(String password, String email){
        logger.info("Perform registration without username.");
        test.log(Status.INFO,"Perform registration without username.");
        registrationTestCases.performRegistrationWithoutUsername(password,email);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Please enter username",errorMessage, "Incorrect error message.");
    }

    @Order(7)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/registrationTestDataWithout_Email.csv", numLinesToSkip = 1)
    public void registrationWithout_Password(String username, String email){
        logger.info("Perform registration without password.");
        test.log(Status.INFO,"Perform registration without password.");
        registrationTestCases.performRegistrationWithoutPassword(username,email);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String errorMessage = registrationActions.getErrorMessage();
        Assertions.assertEquals("Please enter password",errorMessage, "Incorrect error message.");
    }
}
