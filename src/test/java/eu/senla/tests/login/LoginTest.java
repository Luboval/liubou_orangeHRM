package eu.senla.tests.login;

import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.pages.ErrorLoginPage;
import eu.senla.pages.LoginPage;
import eu.senla.pages.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import eu.senla.tests.ProjectDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

  @Test (testName = "Test login with valid credentials", priority = 2)
  public void testLoginWithValidCredentials() {
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Url Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), ReadPropertyFile.getProperty("SUCCESSFULLOGINPAGEURL"), "Login failed");
  }

  @Test (testName = "Test login with incorrect credentials", priority = 1, dataProvider = "getIncorrectCredentials", dataProviderClass = ProjectDataProvider.class)
  public void testLoginWithIncorrectCredentials(String username, String password) {
    ErrorLoginPage errorLoginPage = new LoginPage()
            .visitLoginPage()
            .loginWithIncorrectCredentials(username, password);

    SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(errorLoginPage.getErrorMessage(), "Invalid credentials", "Message is not correct");
            softAssert.assertEquals(errorLoginPage.getErrorIconColor(), ReadPropertyFile.getProperty("ERRORICONCOLOR"));
            softAssert.assertAll();
  }
}
