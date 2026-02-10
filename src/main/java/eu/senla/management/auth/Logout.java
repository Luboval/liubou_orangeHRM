package eu.senla.management.auth;

import eu.senla.management.common.Driver;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;

import static eu.senla.management.common.constants.PagesPaths.LOGOUT_URL;

public class Logout {

    public static void logout() {
         RequestManager.getRequest(
                SpecConfig.requestSpecification(),
                LOGOUT_URL
         );
         Driver.driverRun().manage().deleteAllCookies();
         Driver.driverRun().navigate().refresh();
    }
}
