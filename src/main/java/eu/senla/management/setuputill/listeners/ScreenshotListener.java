package eu.senla.management.setuputill.listeners;

import eu.senla.management.common.Driver;
import eu.senla.management.setuputill.ScreenshotUtil;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Driver.driverRun();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver);
        } else {
            log.info("WebDriver is null. Screenshot not taken.");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // save to disk
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            File dest = new File("build/reports/tests/test/screenshots/" + timestamp + ".png");
            dest.getParentFile().mkdirs();
            Files.copy(screenshotFile.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotBytes;
    }
}
