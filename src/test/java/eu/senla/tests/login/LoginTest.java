package eu.senla.tests.login;

import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.management.loginstrategy.UiLoginStrategy;
import eu.senla.management.auth.GetToken;
import eu.senla.pages.login.ErrorLoginPage;
import eu.senla.pages.login.LoginPage;
import eu.senla.pages.login.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import eu.senla.tests.ProjectDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
  public LoginTest() {
    super(new UiLoginStrategy());
  }

  @Test (testName = "Test login with valid credentials", priority = 2)
  public void testLoginWithValidCredentials() {
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Url Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), ReadPropertyFile.getProperty("SUCCESSFULLOGINPAGEURL"), "GetCookie failed");
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

  @Test
    public void loginApi() {

    System.out.println("Token" + GetToken.getToken());



      //String response = GetCookie.getCookie();
      //System.out.println("Cookie is "+response);

  }
}
