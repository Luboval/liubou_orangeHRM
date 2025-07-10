package eu.senla.tests;

import eu.senla.management.common.Driver;
import eu.senla.management.loginstrategy.LoginStrategy;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    private LoginStrategy loginStrategy;

    public BaseTest(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    @BeforeTest(alwaysRun = true)
    public void login() {
        loginStrategy.login();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Driver.driverTearDown();
    }
}
