package eu.senla.tests.admin;

import eu.senla.pages.adminpages.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserManagementAdminPageTest extends BaseTest {

  @Test (testName = "Admin User Management Page Validation", groups = {"ext", "regression"})
  public void adminUserManagementPageElementsValidation() {
      System.out.println("Start Admin User Management Page Validation");

      Assert.assertTrue(new UserManagementAdminPage()
                        .switchToUserManagementPage()
                        .findPageElements(),
              "Page does not contain all needed elements");
      System.out.println("Finish Admin User Management Page Validation");

  }
}
