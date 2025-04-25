package eu.senla;

import org.openqa.selenium.WebDriver;

public class Basic {

  protected WebDriver driver;

  public Basic(WebDriver driver) {
    this.driver = driver;
  }

  protected void visit(String url) {
    driver.get(url);
  }

  protected void tearDown() {
    driver.quit();
  }
}
