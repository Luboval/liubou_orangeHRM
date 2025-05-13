package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

  protected static WebDriver driver;


  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  protected void visit(String url) {
    driver.get(url);
  }

  public String getCurrentUrl(){
    return driver.getCurrentUrl();
  }

  public void clickButton (By locator) {
    driver.findElement(locator).click();
  }

  public void fillInput(By locator, String value) {
    driver.findElement(locator).sendKeys(value);
  }



}
