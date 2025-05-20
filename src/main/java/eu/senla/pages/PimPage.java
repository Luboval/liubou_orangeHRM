package eu.senla.pages;

import eu.senla.elements.Employee;
import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Wait;
import org.openqa.selenium.By;

public class PimPage  {
    private By pimMenuLocator = By.xpath("//span[text()='PIM']");
    private By pimLabelLocator = By.xpath("//span//h6[text()='PIM']");
    private By addButtonLocator = By.xpath("//button[text()=' Add ']");
    private By addEmployeeLabelLocator = By.xpath("//h6[text()='Add Employee']");
    private By inputFirstNameLocator = By.name("firstName");
    private By inputMiddleNameLocator = By.name("middleName");
    private By inputLastNameLocator = By.name("lastName");
    private By switchLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/label[1]/span[1]");
    private By usernameInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]");
    private By passwordInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/input[1]");
    private By confirmPasswordInputLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/input[1]");
    private By saveButtonLocator = By.xpath("//button[text()=' Save ']");
    private By personalDetalesLocator = By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding']>h6");

//    private Wait wait = new Wait(driver);
//
//    public PimPage(WebDriver driver) {
//        super(driver);
//    }

    public void switchToPimPage() {
        BaseActions.clickButton(pimMenuLocator);
    }

    public void waitForPimLabel() {
        Wait.waitFIsDisplayed(pimLabelLocator);
    }

    public void clickAddButton() {
        BaseActions.clickButton(addButtonLocator);
    }

    public void waitForAddEmployeeLabel() {
        Wait.waitFIsDisplayed(addEmployeeLabelLocator);
    }

    public PimPage openAddEmployeeForm() {
        switchToPimPage();
        waitForPimLabel();
        clickAddButton();
        waitForAddEmployeeLabel();
        waitForPimLabel();
        return new PimPage();
    }

    public PimPage addEmployeeWithPassword(Employee employee) {
        BaseActions.fillInput(inputFirstNameLocator, employee.getEmployeeFirstName());
        BaseActions.fillInput(inputMiddleNameLocator, employee.getEmployeeMiddleName());
        BaseActions.fillInput(inputLastNameLocator, employee.getEmployeeLastName());
        BaseActions.clickButton(switchLocator);
        Wait.waitFIsDisplayed(usernameInputLocator);
        BaseActions.fillInput(usernameInputLocator, employee.getEmployeeUserName());
        BaseActions.fillInput(passwordInputLocator, employee.getEmployeePassword());
        BaseActions.fillInput(confirmPasswordInputLocator, employee.getEmployeePassword());
        BaseActions.clickButton(saveButtonLocator);
        return new PimPage();
    }

    public Boolean succsessfulCreateUser() {
       return Wait.waitFIsDisplayed(personalDetalesLocator).isDisplayed();
    }
}
