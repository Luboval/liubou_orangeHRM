package eu.senla.tests.login;

import eu.senla.pages.ErrorLoginPage;
import eu.senla.pages.LoginPage;
import eu.senla.pages.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import eu.senla.tests.ProjectDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
  //private WebDriver driver = new ChromeDriver();


  @Test (testName = "Test login with valid credentials", priority = 2)
  public void testLoginWithValidCredentials() {
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Title Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", "Login failed");
  }

  @Test (testName = "Test login with incorrect credentials", priority = 1, dataProvider = "getIncorrectCredentials", dataProviderClass = ProjectDataProvider.class)
  public void testLoginWithIncorrectCredentials(String username, String password) {
    ErrorLoginPage errorLoginPage = new LoginPage()
            .visitLoginPage()
            .loginWithIncorrectCredentials(username, password);

    SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(errorLoginPage.getErrorMessage(), "Invalid credentials", "Message is not correct");
            softAssert.assertTrue(errorLoginPage.getErrorIconLocator());
            softAssert.assertAll();
  }
}
