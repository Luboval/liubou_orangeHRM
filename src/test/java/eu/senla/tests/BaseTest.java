package eu.senla.tests;

import eu.senla.management.general.Driver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        Driver.driverRun();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Driver.driverTearDown();
    }
}
