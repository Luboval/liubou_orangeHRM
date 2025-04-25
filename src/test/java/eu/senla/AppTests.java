package eu.senla;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AppTests {
  private WebDriver driver = new ChromeDriver();

  @Test
  public void testLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.visitLoginPage();
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    loginPage.enterUserName("Admin");
    loginPage.enterPassword("admin123");
    loginPage.clickLoginButton();

    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    AdminUserManagementPage adminUserManagementPage = new AdminUserManagementPage(driver);
    adminUserManagementPage.switchToUserManagementPage();


    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    adminUserManagementPage.expandArea();

    adminUserManagementPage.findElement();
    adminUserManagementPage.findSearchButton();
    adminUserManagementPage.findAddButton();
    adminUserManagementPage.findDropDownUserRole();
    adminUserManagementPage.findAdminItemLocator();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }







    loginPage.tearDown();
  }
}
