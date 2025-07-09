package eu.senla.pages.login;

import eu.senla.management.common.Wait;
import org.openqa.selenium.By;

public class ErrorLoginPage {
    private By errorMessageLocator = By.cssSelector("div[class='orangehrm-login-error']>div[role='alert']");
    private By errorIconLocator = By.cssSelector("div[class='orangehrm-login-error']>div[role='alert']>*>i");

    public String getErrorMessage() {
        return Wait.waitFIsDisplayed(errorMessageLocator).getText();
    }

    public String getErrorIconColor() {
        return Wait.waitFIsDisplayed(errorIconLocator).getCssValue("color");
    }
}
