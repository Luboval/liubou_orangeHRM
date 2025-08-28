package eu.senla.tests.admin;

import eu.senla.pages.adminpages.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class UserManagementAdminPageTest extends BaseTest {

  @Test (testName = "Admin User Management Page Validation", description = "Admin User Management Page Validation", groups = {"ext", "regression"})
  public void adminUserManagementPageElementsValidation() {
      log.info("Start Admin User Management Page Validation");

      Assert.assertTrue(new UserManagementAdminPage()
                        .switchToUserManagementPage()
                        .findPageElements(),
              "Page does not contain all needed elements");
      log.info("Finish Admin User Management Page Validation");

  }
}
