package eu.senla.tests.login;

import eu.senla.pages.SidePanelPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static eu.senla.management.common.constants.PagesPaths.LOGIN_PAGE;
import static eu.senla.management.utils.ReadPropertyFile.getProperty;

public class LogoutTest extends BaseTest {

    @Test (testName = "Logout test", groups = {"smoke", "regression"})
    public void testLogOut() {
        System.out.println("Start Logout test");

        Assert.assertEquals(new SidePanelPage().executeLogout(), getProperty("BASEURL") + LOGIN_PAGE, "Wrong page");

        System.out.println("Finish Logout test");

    }
}
