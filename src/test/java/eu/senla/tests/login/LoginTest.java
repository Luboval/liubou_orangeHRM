package eu.senla.tests.login;

import eu.senla.pages.LoginPage;
import eu.senla.pages.SuccessfulLoginPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
  //private WebDriver driver = new ChromeDriver();

  @Test
  public void testLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.visitLoginPage();
    loginPage.waitForUserNamePresence();
    loginPage.enterUserName("Admin");
    loginPage.enterPassword("admin123");
    loginPage.clickLoginButton();
    new SuccessfulLoginPage(driver).waitForDashboardLabelPresence();
    Assert.assertEquals(loginPage.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
  }
}
