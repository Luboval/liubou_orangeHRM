package eu.senla.management.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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

    public static WebElement moveToElement(WebElement element) {
        Actions moveToElement = new Actions(driver);
        moveToElement.moveToElement(element).click().perform();
        return element;
    }


}
