import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.LoginTest;
import tests.RegistrationTest;

@Suite
@SelectClasses({ RegistrationTest.class, LoginTest.class })
public class TestSuite {
}
