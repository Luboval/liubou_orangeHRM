package eu.senla.tests.login;

import eu.senla.management.auth.Logout;
import eu.senla.management.common.Constants;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
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
  public LoginTest() {
    super(new ApiLoginStrategy());
  }

  @BeforeMethod
  void logout() {
    Logout.logout();
  }

  @Test (testName = "Test login with valid credentials")
  public void testLoginWithValidCredentials() {
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Url Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), Constants.SUCCESSFUL_LOGIN_PAGE_URL, "GetCookie failed");
  }

  @Test (testName = "Test login with incorrect credentials",  dataProvider = "getIncorrectCredentials", dataProviderClass = ProjectDataProvider.class)
  public void testLoginWithIncorrectCredentials(String username, String password) {
    ErrorLoginPage errorLoginPage = new LoginPage()
            .visitLoginPage()
            .loginWithIncorrectCredentials(username, password);

    SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(errorLoginPage.getErrorMessage(), "Invalid credentials", "Message is not correct");
            softAssert.assertEquals(errorLoginPage.getErrorIconColor(), Constants.ERROR_ICON_COLOR);
            softAssert.assertAll();
  }
}
