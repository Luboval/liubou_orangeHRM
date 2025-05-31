package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {
    private By pimMenuLocator = By.xpath("//span[text()='PIM']");
    private By pimLabelLocator = By.xpath("//span//h6[text()='PIM']");
    private By addButtonLocator = By.xpath("//button[text()=' Add ']");
    private By addEmployeeLabelLocator = By.xpath("//h6[text()='Add Employee']");
    private By inputFirstNameLocator = By.name("firstName");
    private By inputMiddleNameLocator = By.name("middleName");
    private By inputLastNameLocator = By.name("lastName");
    private By switchLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/label[1]/span[1]");
    private By usernameInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]");
    private By passwordInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]");
    private By confirmPasswordInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/input[1]");
    private By saveButtonLocator = By.xpath("//button[text()=' Save ']");

    private WaitsPage wait = new WaitsPage(driver);

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public void switchToPimPage() {
        super.clickButton(pimMenuLocator);
    }

    public void waitForPimLabel() {
        wait.waitFluentIsDisplayed(pimLabelLocator);
    }

    public void clickAddButton() {
        super.clickButton(addButtonLocator);
    }

    public void waitForAddEmployeeLabel() {
        wait.waitFluentIsDisplayed(addEmployeeLabelLocator);
    }

    public void addEmployeeWithPassword() {
        super.fillInput(inputFirstNameLocator, "AT1");
        super.fillInput(inputMiddleNameLocator, "AT11");
        super.fillInput(inputLastNameLocator, "AT111");
        super.clickButton(switchLocator);
        wait.waitFluentIsDisplayed(usernameInputLocator);
        super.fillInput(usernameInputLocator, "AT1");
        super.fillInput(passwordInputLocator, "Ql321jdfanki4#@");
        super.fillInput(confirmPasswordInputLocator, "Ql321jdfanki4#@");
        super.clickButton(saveButtonLocator);
    }
}
