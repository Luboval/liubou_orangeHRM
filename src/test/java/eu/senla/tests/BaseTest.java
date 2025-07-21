package eu.senla.tests;

import eu.senla.management.auth.Logout;
import eu.senla.management.common.Driver;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.management.loginstrategy.LoginStrategy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;


public class BaseTest {
    private final LoginStrategy loginStrategy;

    //Добавлен, т.к. не шнициализировался при запуске тестов И теперь работает без установки стратегии в тесте
    public BaseTest() {
        this.loginStrategy = new ApiLoginStrategy();
    }

    public BaseTest(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    @BeforeClass(alwaysRun = true)
    public void login() {
        loginStrategy.login();
        System.out.println("Login");
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
        Logout.logout();
        System.out.println("Logout");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Driver.driverTearDown();
    }
}
