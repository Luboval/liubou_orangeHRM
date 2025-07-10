package eu.senla.tests.login;

import eu.senla.management.common.Constants;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.SidePanelPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    public LogoutTest() {
        super(new ApiLoginStrategy());
    }

    @Test (testName = "Logout test")
    public void testLogOut() {

        Assert.assertEquals(new SidePanelPage().executeLogout(), Constants.LOGIN_PAGE_URL, "Wrong page");

    }
}
