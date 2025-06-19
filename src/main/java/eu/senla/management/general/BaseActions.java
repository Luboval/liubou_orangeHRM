package eu.senla.management.general;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BaseActions {

//
//  public BaseActions(WebDriver driver) {
//    this.driver = driver;
//  }

  public static void visit(String url) {
    Driver.driverRun().get(url);
  }

  public static String getCurrentUrl() {
    return Driver.driverRun().getCurrentUrl();
  }

  public static void clickButton(By locator) {
     Wait.waitFInteractable(locator).click();
  }

  public static WebElement moveToElement(WebElement element) {
    Actions moveToElement = new Actions(Driver.driverRun());
    moveToElement.moveToElement(element).click().perform();
    return element;
  }

  public static void fillInput(By locator, String value) {
    moveToElement(Wait.waitFInteractable(locator)).sendKeys(value);
  }

  public static WebElement displayAfterClick(By locatorToClick, By locatorToDisplay) {
    Wait.waitFIsDisplayed(locatorToClick).click();
   return Wait.waitFIsDisplayed(locatorToDisplay);
  }
}
