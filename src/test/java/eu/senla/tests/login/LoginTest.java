package eu.senla.tests.login;

import eu.senla.management.auth.Logout;
import eu.senla.management.utils.ReadPropertyFile;
import eu.senla.pages.login.ErrorLoginPage;
import eu.senla.pages.login.LoginPage;
import eu.senla.pages.login.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import eu.senla.tests.ProjectDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static eu.senla.management.common.constants.AttributesForUITests.ERROR_ICON_COLOR;
import static eu.senla.management.common.constants.PagesPaths.SUCCESSFUL_LOGIN_PAGE;

@Slf4j
public class LoginTest extends BaseTest {

  @BeforeMethod(alwaysRun = true)
  public void logout() {
    Logout.logout();
  }
  @Test (testName = "Test login with valid credentials", groups = {"smoke", "regression", "sm"})
  public void testLoginWithValidCredentials() {
    log.info("Starting Test login with valid credentials");
    SuccessfulLoginPage successfulLogin = new LoginPage()
             .visitLoginPage()
             .loginWithValidCredentials();

    //Url Validation;
    Assert.assertEquals(successfulLogin.getSuccessfulLoginPageUrl(), ReadPropertyFile.getProperty("BASEURL") + SUCCESSFUL_LOGIN_PAGE, "GetCookie failed");

    log.info("Finish Test login with valid credentials");
  }

  @Test (testName = "Test login with incorrect credentials", groups = {"regression"}, dataProvider = "getIncorrectCredentials", dataProviderClass = ProjectDataProvider.class)
  public void testLoginWithIncorrectCredentials(String username, String password) {
    log.info("Start Test login with incorrect credentials");
    ErrorLoginPage errorLoginPage = new LoginPage()
            .visitLoginPage()
            .loginWithIncorrectCredentials(username, password);

    SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(errorLoginPage.getErrorMessage(), "Invalid credentials", "Message is not correct");
            softAssert.assertEquals(errorLoginPage.getErrorIconColor(), ERROR_ICON_COLOR);
            softAssert.assertAll();
    log.info("Finish Test login with incorrect credentials");
  }
}
