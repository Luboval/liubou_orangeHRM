package eu.senla.pages.login;

import eu.senla.management.general.BaseActions;
import eu.senla.management.dataactions.ReadPropertyFile;
import org.openqa.selenium.By;

public class LoginPage  {
  private By userNameInputLocator = By.name("username");
  private By passwordInputLocator = By.name("password");
  private By loginButonLocator = By.xpath("//button[@type='submit']");

  public LoginPage visitLoginPage() {
    BaseActions.visit(ReadPropertyFile.getProperty("LOGINPAGEURL"));
    return new LoginPage();
  }

  public void enterUserName(String username) {
    BaseActions.fillInput(userNameInputLocator, username);
  }

  public  void enterPassword(String password) {
    BaseActions.fillInput(passwordInputLocator, password);
  }

  public void clickLoginButton() {
    BaseActions.clickButton(loginButonLocator);
  }

  public void loginUser(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    clickLoginButton();
  }

  public SuccessfulLoginPage loginWithValidCredentials() {
    loginUser(ReadPropertyFile.getProperty("USERNAME"), ReadPropertyFile.getProperty("PASSWORD"));
    return new SuccessfulLoginPage();
  }

  public ErrorLoginPage loginWithIncorrectCredentials(String username, String password) {
    loginUser(username, password);
    return new ErrorLoginPage();
  }
}
