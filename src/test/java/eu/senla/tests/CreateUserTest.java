package eu.senla.tests;

import eu.senla.BaseClass;
import eu.senla.pages.AdminUserManagementPage;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseClass {


  @Test
  public void testCreateUser() {
    AdminUserManagementPage adminUserManagementPage = new AdminUserManagementPage();
    adminUserManagementPage.switchToUserManagementPage();
    adminUserManagementPage.waitForUserManagementLabelPresence();

    adminUserManagementPage.expandArea();

    adminUserManagementPage.findElement();
    adminUserManagementPage.findSearchButton();
    adminUserManagementPage.findAddButton();
    adminUserManagementPage.findDropDownUserRole();
    adminUserManagementPage.findAdminItemLocator();


  }
}
