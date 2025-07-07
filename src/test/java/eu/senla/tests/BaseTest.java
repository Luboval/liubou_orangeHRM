package eu.senla.tests;

import eu.senla.management.general.Driver;
import eu.senla.management.loginstrategy.LoginStrategy;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private LoginStrategy loginStrategy;

    public BaseTest(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

 //   @BeforeSuite(alwaysRun = true)
// не нужен т.к. логин идет
//    public void setup() {
//       Driver.driverRun();
//    }

    @BeforeSuite(alwaysRun = true)
    public void login() {
        loginStrategy.login();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Driver.driverTearDown();
    }
}
