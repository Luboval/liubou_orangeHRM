package eu.senla.management.general;

import org.openqa.selenium.By;

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

  public static void fillInput(By locator, String value) {
    Driver.moveToElement(Wait.waitFInteractable(locator)).sendKeys(value);
  }
}
