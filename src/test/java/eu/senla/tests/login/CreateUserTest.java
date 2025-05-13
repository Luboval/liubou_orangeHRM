package eu.senla.tests.login;

import eu.senla.pages.PimPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {


  @Test
  public void testCreateUser() {
    PimPage pimPage = new PimPage(driver);
    pimPage.switchToPimPage();
    pimPage.waitForPimLabel();
    pimPage.clickAddButton();
    pimPage.waitForAddEmployeeLabel();
    pimPage.addEmployeeWithPassword();




  }
}
