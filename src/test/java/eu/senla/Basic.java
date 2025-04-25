package eu.senla;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basic {

  protected WebDriver driver;

  public Basic() {
    this.driver = new ChromeDriver();
  }

  protected void visit(String url) {
    driver.get(url);
  }

  protected void tearDown() {
    driver.quit();
  }
}
