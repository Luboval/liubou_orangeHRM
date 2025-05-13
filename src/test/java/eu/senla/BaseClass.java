package eu.senla;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

  protected static WebDriver driver;

//  public BaseClass(WebDriver driver) {
//    this.driver = driver;
//  }

  protected void visit(String url) {
    driver.get(url);
  }

  public String getCurrentUrl(){
    return driver.getCurrentUrl();
  }

  @BeforeSuite(alwaysRun = true)
  public void setup(){
    driver = new ChromeDriver();
  }



  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}
