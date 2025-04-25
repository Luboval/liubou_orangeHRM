package eu.senla;

import org.testng.annotations.Test;

public class AppTests {

  @Test
  public void testLogin() {
    LoginPage loginPage = new LoginPage();
    loginPage.visitLoginPage();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    loginPage.enterUserName("Admin");
    loginPage.enterPassword("admin123");
    loginPage.clickLoginButton();

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    AdminUserManagementPage adminUserManagementPage = new AdminUserManagementPage();
    adminUserManagementPage.switchToUserManagementPage();
   // adminUserManagementPage.findElement();


    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    adminUserManagementPage.findElement();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }







    loginPage.tearDown();
  }
}
