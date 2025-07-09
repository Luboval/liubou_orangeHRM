package eu.senla.tests.login;

import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.SidePanelPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    public LogoutTest() {
        super(new ApiLoginStrategy());
    }

    @Test
    public void testLogOut() {
        new SidePanelPage()
                .executeLogout();
    }
}
