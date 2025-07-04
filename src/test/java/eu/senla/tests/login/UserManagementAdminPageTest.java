package eu.senla.tests.login;

import eu.senla.management.loginstrategy.UiLoginStrategy;
import eu.senla.pages.admin.UserManagementAdminPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserManagementAdminPageTest extends BaseTest {
    public UserManagementAdminPageTest() {
        super(new UiLoginStrategy());
    }

    @BeforeTest
    void loginBeforeTest() {
        BaseTest login = new UserManagementAdminPageTest();
        login.login();
    }


  @Test (testName = "Admin User Management Page Validation")
  public void adminUserManagementPageElementsValidation() {
      Assert.assertTrue(new UserManagementAdminPage()
                        .switchToUserManagementPage()
                        .findPageElements(),
              "Page does not contain all needed elements");
  }
}
