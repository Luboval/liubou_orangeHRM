package eu.senla.management.general;

import eu.senla.management.rest.GetCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BaseActions {

  public static void visit(String url) {
    Driver.driverRun().get(url);
  }

  public static void loginWithCookie(String url) {
    Cookie cookie = new Cookie.Builder("orangehrm", GetCookie.getCookie())
            .domain("opensource-demo.orangehrmlive.com")
            .path("/web")
            .isSecure(true)
            .isHttpOnly(true)
            .sameSite("Lax")
            .build();
    System.out.println("Полученные куки " + cookie);
    Driver.driverRun().manage().deleteCookieNamed("orangehrm");
    System.out.println("deleted " + Driver.driverRun().manage().getCookieNamed("orangehrm"));
    Driver.driverRun().manage().addCookie(cookie);
    System.out.println("Set cookie " + Driver.driverRun().manage().getCookieNamed("orangehrm"));
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

  public static void submitButton(By locator) {
    Wait.waitFInteractable(locator).submit();
  }
}
