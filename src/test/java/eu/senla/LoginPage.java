package eu.senla;

import org.openqa.selenium.By;

public class LoginPage extends Basic {
  private By userNameInputLocator = By.name("username");
  private By passwordInputLocator = By.name("password");
  private By loginButonLocator = By.xpath("//button[@type='submit']");
 // private By loginTitlelocator = By.className("oxd-text oxd-text--h5 orangehrm-login-title");


  public LoginPage() {
    super();
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


}
