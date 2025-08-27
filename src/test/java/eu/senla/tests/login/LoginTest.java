package eu.senla.tests.login;

import eu.senla.management.auth.Logout;
import eu.senla.management.common.Constants;
import eu.senla.pages.login.ErrorLoginPage;
import eu.senla.pages.login.LoginPage;
import eu.senla.pages.login.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import eu.senla.tests.ProjectDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

  @BeforeMethod (alwaysRun = true)
  public void logout() {
    Logout.logout();
  }

  @Test (testName = "Test login with valid credentials", groups = {"smoke", "regression", "sm"})
  public void testLoginWithValidCredentials() {
    System.out.println("starting Test login with valid credentials");
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Url Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), Constants.SUCCESSFUL_LOGIN_PAGE_URL, "GetCookie failed");

    System.out.println("Finish Test login with valid credentials");
  }

  @Test (testName = "Test login with incorrect credentials", groups = {"regression"}, dataProvider = "getIncorrectCredentials", dataProviderClass = ProjectDataProvider.class)
  public void testLoginWithIncorrectCredentials(String username, String password) {
    System.out.println("Start Test login with incorrect credentials");
    ErrorLoginPage errorLoginPage = new LoginPage()
            .visitLoginPage()
            .loginWithIncorrectCredentials(username, password);

    SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(errorLoginPage.getErrorMessage(), "Invalid credentials", "Message is not correct");
            softAssert.assertEquals(errorLoginPage.getErrorIconColor(), Constants.ERROR_ICON_COLOR);
            softAssert.assertAll();
    System.out.println("Finish Test login with incorrect credentials");
  }
}
