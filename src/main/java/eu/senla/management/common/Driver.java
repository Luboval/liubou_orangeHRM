package eu.senla.management.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Driver {
   // private static WebDriver driver;
   private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //Private constructor to prevent creation a new instance from outside
    private Driver() { }

    public static WebDriver driverRun() {
        if (driver.get() == null) {
            switch (System.getProperty("browser").toLowerCase()) {
                case "chrome" -> driver.set(new ChromeDriver()); //= new ChromeDriver();
                case "firefox" -> driver.set(new FirefoxDriver()); //driver = new FirefoxDriver();
                default -> throw new IllegalArgumentException("Unknown browser");
            }
            //driver.manage().window().maximize();
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void driverTearDown() {
        driver.get().quit();
       // driver = null;
        driver.remove();
    }
}
