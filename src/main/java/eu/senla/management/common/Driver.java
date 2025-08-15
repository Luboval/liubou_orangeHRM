package eu.senla.management.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Driver {
    private static WebDriver driver;

    //Private constructor to prevent creation a new instance from outside
    private Driver() { }

    public static WebDriver driverRun() {
        if (driver == null) {
            switch (System.getProperty("browser").toLowerCase()) {
                case "chrome" -> driver = new ChromeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                default -> throw new IllegalArgumentException("Unknown browser");
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void driverTearDown() {
        driver.quit();
        driver = null;
    }
}
