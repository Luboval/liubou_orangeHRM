package eu.senla.management.common;

import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.management.auth.GetCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class BaseActions {

  public static void visit(String url) {
    Driver.driverRun().navigate().to(url);
  }

  public static void loginWithCookie(String url) {

    Cookie cookie = new Cookie.Builder("orangehrm", GetCookie.getCookie())
            .domain("opensource-demo.orangehrmlive.com")
            .path("/web")
            .isSecure(true)
            .isHttpOnly(true)
            .sameSite("Lax")
            .build();

    Driver.driverRun().get(ReadPropertyFile.getProperty("DASHBOARDPAGEURL"));
    Driver.driverRun().manage().deleteCookieNamed("orangehrm");
    Driver.driverRun().manage().addCookie(cookie);
    Driver.driverRun().navigate().refresh();
  }

  public static String getCurrentUrl() {
    return Driver.driverRun().getCurrentUrl();
  }

  public static void clickButton(By locator) {
     Wait.waitFInteractable(locator).click();
  }

  public static void clickElement(By locator) {
    Wait.waitFPresence(locator).click();
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

  public static String getValue(By locator, String attribute) {
    return Wait.waitFPresence(locator).getAttribute(attribute);
  }

  public static void uploadFile(By locator, String filename) {
    File file = new File(filename);
    String path = file.getAbsolutePath();
    Driver.driverRun().findElement(locator).sendKeys(path);
    //Driver.driverRun().findElement(By.id("file-submit")).click();
  }

  public static WebElement clearWithKeys(By locator) {
    WebElement webElement = moveToElement(Wait.waitFInteractable(locator));
    webElement.sendKeys(Keys.CONTROL + "a");
    webElement.sendKeys(Keys.DELETE);
    return webElement;
  }

}
