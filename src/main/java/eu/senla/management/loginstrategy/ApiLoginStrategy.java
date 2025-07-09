package eu.senla.management.loginstrategy;

import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.management.common.BaseActions;

public class ApiLoginStrategy implements LoginStrategy {

    @Override
    public void login() {
        BaseActions.loginWithCookie(ReadPropertyFile.getProperty("DASHBOARDPAGEURL"));;
    }
}
