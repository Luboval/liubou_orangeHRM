package eu.senla.tests.admin;

import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.adminpages.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserManagementAdminPageTest extends BaseTest {
    public UserManagementAdminPageTest() {
        super(new ApiLoginStrategy());
    }

  @Test (testName = "Admin User Management Page Validation")
  public void adminUserManagementPageElementsValidation() {
      Assert.assertTrue(new UserManagementAdminPage()
                        .switchToUserManagementPage()
                        .findPageElements(),
              "Page does not contain all needed elements");
  }
}
