package eu.senla.tests;

import eu.senla.BaseClass;
import eu.senla.pages.LoginPage;
import eu.senla.pages.SuccessfulLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
  //private WebDriver driver = new ChromeDriver();

  @Test
  public void testLogin() {
    LoginPage loginPage = new LoginPage();
    loginPage.visitLoginPage();

    loginPage.waitForUserNamePresence();
    loginPage.enterUserName("Admin");
    loginPage.enterPassword("admin123");
    loginPage.clickLoginButton();
    SuccessfulLoginPage.waitForDashboardLabelPresence();
    Assert.assertEquals(loginPage.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");



  }
}
