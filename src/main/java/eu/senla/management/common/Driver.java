package eu.senla.management.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class Driver {
    private static WebDriver driver;

    //Private constructor to prevent creation a new instance from outside
    private Driver() { }

    public static WebDriver driverRun() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void driverTearDown() {
        driver.quit();
        driver = null;
    }
}
