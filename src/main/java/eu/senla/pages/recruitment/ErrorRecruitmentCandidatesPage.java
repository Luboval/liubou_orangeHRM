package eu.senla.pages.recruitment;

import org.openqa.selenium.By;

import static eu.senla.management.common.Constants.ATTRIBUTE_CSS_COLOR;
import static eu.senla.management.common.Wait.waitFIsDisplayed;


public class ErrorRecruitmentCandidatesPage {
    private By firstNameFieldLocator = By.name("firstName");
    private By errorFirstNameFieldLocator = By.cssSelector("span[class~='oxd-input-field-error-message']");
    private By emailInputLocator = By.xpath("//*[@class= 'oxd-form-row'][3]/div/div[1]/*//input");
    private By errorEmailInputLocator = By.xpath("//*[@class= 'oxd-form-row'][3]/div/div[1]/*//span");

    public String getFirstNameFieldErrorText() {
        return waitFIsDisplayed(errorFirstNameFieldLocator).getText();
    }

    public String getFirstNameFieldErrorTextColor() {
        return waitFIsDisplayed(errorFirstNameFieldLocator).getCssValue(ATTRIBUTE_CSS_COLOR);
    }

    public String getFirstNameFieldBordersColor(String borderColor) {
        return waitFIsDisplayed(firstNameFieldLocator).getCssValue(borderColor);
    }

    public String getEmailInputErrorText() {
        return waitFIsDisplayed(errorEmailInputLocator).getText();
    }

    public String getEmailInputErrorTextColor() {
        return waitFIsDisplayed(errorEmailInputLocator).getCssValue(ATTRIBUTE_CSS_COLOR);
    }

    public String getEmailInputBordersColor(String borderColor) {
        return waitFIsDisplayed(emailInputLocator).getCssValue(borderColor);
    }





}
