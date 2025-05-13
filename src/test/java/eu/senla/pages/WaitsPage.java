package eu.senla.pages;

import eu.senla.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitsPage extends BaseClass {

//    public WaitsPage(WebDriver driver){
//        super(driver);
//    }

    public static void waitFluentPresence (By locator) {
        Wait<WebDriver> waitFluentPresence = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        waitFluentPresence.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static void waitFluentIsDisplayed(By locator) {
        Wait<WebDriver> waitFluentPresence = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        waitFluentPresence.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
