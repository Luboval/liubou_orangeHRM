package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
  private By userNameInputLocator = By.name("username");
  private By passwordInputLocator = By.name("password");
  private By loginButonLocator = By.xpath("//button[@type='submit']");

 // private By loginTitlelocator = By.className("oxd-text oxd-text--h5 orangehrm-login-title");

  public LoginPage (WebDriver driver) {
    super(driver);
  }


  public void visitLoginPage() {
    super.visit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }

  public void enterUserName(String username) {
    driver.findElement(userNameInputLocator).sendKeys(username);
  }

  public  void enterPassword(String password) {
    driver.findElement(passwordInputLocator).sendKeys(password);
  }

  public void clickLoginButton() {
    driver.findElement(loginButonLocator).click();
  }

  public void waitForUserNamePresence() {
    WaitsPage wait = new WaitsPage(driver);
    wait.waitFluentPresence(userNameInputLocator);

  }


}
