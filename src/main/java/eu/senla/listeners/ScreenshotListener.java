package eu.senla.listeners;

import eu.senla.management.common.Driver;
import eu.senla.management.common.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Driver.driverRun();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver);
        } else {
            System.out.println("WebDriver is null. Screenshot not taken.");
        }
    }

}
