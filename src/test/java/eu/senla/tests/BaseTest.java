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

    @BeforeSuite(alwaysRun = true)
    public void setup() {
       Driver.driverRun();
    }

    public void login() {
        loginStrategy.login();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Driver.driverTearDown();
    }
}
