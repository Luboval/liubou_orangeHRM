package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitsPage extends BasePage {

   public WaitsPage(WebDriver driver){
        super(driver);
    }

    Wait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(60))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);


    public void waitFluentPresence(By locator) {

        waitFluent.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void waitFluentIsDisplayed(By locator) {

        waitFluent.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitFluentInteractable(By locator) {
        waitFluent.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
