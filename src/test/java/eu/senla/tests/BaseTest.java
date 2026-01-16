package eu.senla.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import eu.senla.elements.ErrorMessages;
import eu.senla.management.auth.Logout;
import eu.senla.management.common.Driver;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.management.loginstrategy.LoginStrategy;
import eu.senla.management.utils.ReadFromJson;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class BaseTest {
    private final LoginStrategy loginStrategy;

    //Добавлен, т.к. не шнициализировался при запуске тестов И теперь работает без установки стратегии в тесте
    public BaseTest() {
        this.loginStrategy = new ApiLoginStrategy();
    }

    public BaseTest(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginStrategy.login();
        log.info("Login");
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        Logout.logout();
        log.info("Logout");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (Driver.driverRun() != null) {
            Driver.driverTearDown();
        }
    }

    public Map<String, ErrorMessages> getErrors() throws IOException {
       return ReadFromJson.readFromJsonToMap(new File("src/test/resources/files/Errors.json"),
                new TypeReference<Map<String, ErrorMessages>>() {});
    }
}
