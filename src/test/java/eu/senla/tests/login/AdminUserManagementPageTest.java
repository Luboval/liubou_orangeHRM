package eu.senla.tests.login;

import eu.senla.pages.AdminUserManagementPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminUserManagementPageTest extends BaseTest {


  @Test (testName = "Admin User Management Page Validation")
  public void adminUserManagementPageElementsValidation() {
      Assert.assertTrue(new AdminUserManagementPage()
                        .switchToUserManagementPage()
                        .findPageElements(),
              "Page does not contain all needed elements");
  }
}
