package eu.senla.tests.login;

import eu.senla.pages.LoginPage;
import eu.senla.pages.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
  //private WebDriver driver = new ChromeDriver();

  @Test (testName = "Test login with valid credentials")
  public void testLoginWithValidCredentials() {
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Title Validation;
    Assert.assertEquals(successfulLogin.getPageUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "Login failed");
  }

  @Test (testName = "Test login with incorrect credentials")
  public void testLoginWithIncorrectCredentials() {

  }


}
