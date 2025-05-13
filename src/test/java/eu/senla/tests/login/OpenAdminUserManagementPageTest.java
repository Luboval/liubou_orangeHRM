package eu.senla.tests.login;

import eu.senla.pages.AdminUserManagementPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;

public class OpenAdminUserManagementPageTest extends BaseTest {


  @Test
  public void openAdminUserManagementPage() {
    AdminUserManagementPage adminUserManagementPage = new AdminUserManagementPage(driver);
    adminUserManagementPage.switchToUserManagementPage();
    adminUserManagementPage.waitForUserManagementLabelPresence();

   // adminUserManagementPage.expandArea();

    adminUserManagementPage.findElement();
    adminUserManagementPage.findSearchButton();
    adminUserManagementPage.findAddButton();
    adminUserManagementPage.findDropDownUserRole();
    adminUserManagementPage.findAdminItemLocator();


  }
}
