package eu.senla.management.auth;

import eu.senla.management.common.Constants;
import eu.senla.management.common.Driver;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;

public class Logout {

    public static void logout() {
         RequestManager.getRequest(
                SpecConfig.requestSpecification(),
                Constants.LOGOUT_URL
         );
         Driver.driverRun().manage().deleteAllCookies();
         Driver.driverRun().navigate().refresh();
    }
}
