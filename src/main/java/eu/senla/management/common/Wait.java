package eu.senla.management.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Wait {
    private static int timeoutSec = 300;
    private static int poolingSec = 1;

    public static WebElement wait(ExpectedCondition<WebElement> expectedConditions) {
        return new FluentWait<>(Driver.driverRun())
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofSeconds(poolingSec))
                .ignoring(NoSuchElementException.class)
                .until(expectedConditions);
    }

    public static boolean waitBool(ExpectedCondition<Boolean> expectedConditions) {
        return new FluentWait<>(Driver.driverRun())
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofSeconds(poolingSec))
                .ignoring(NoSuchElementException.class)
                .until(expectedConditions);
    }

    public static WebElement waitFPresence(By locator) {

        return wait(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public static WebElement waitFIsDisplayed(By locator) {

        return wait(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitFInteractable(By locator) {

        return wait(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitFChanged(By locator, String attr, String value) {

        waitBool(ExpectedConditions.attributeContains(locator, attr, value));

        return wait(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
