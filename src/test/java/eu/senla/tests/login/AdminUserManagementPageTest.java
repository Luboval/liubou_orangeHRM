package eu.senla.tests.login;

import eu.senla.pages.AdminUserManagementPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminUserManagementPageTest extends BaseTest {


  @Test (testName = "Admin User Management Page Validation")
  public void adminUserManagementPage() {
    boolean adminUserManagementPage = new AdminUserManagementPage()
              .switchToUserManagementPage()
              .findPageElements();
      Assert.assertTrue(adminUserManagementPage, "Page does not contain all needed elements");
  }
}
