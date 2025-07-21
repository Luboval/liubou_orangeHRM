package eu.senla.tests.login;

import eu.senla.management.common.Constants;
import eu.senla.pages.SidePanelPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test (testName = "Logout test", groups = {"smoke", "regression"})
    public void testLogOut() {
        System.out.println("Start Logout test");

        Assert.assertEquals(new SidePanelPage().executeLogout(), Constants.LOGIN_PAGE_URL, "Wrong page");

        System.out.println("Finish Logout test");

    }
}
