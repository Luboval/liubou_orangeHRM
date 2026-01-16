package eu.senla.pages.pimpages;

import eu.senla.elements.Employee;
import eu.senla.management.utils.ReadPropertyFile;
import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import eu.senla.pages.SidePanelPage;
import org.openqa.selenium.By;

public class PimPage {
    private By pimMenuLocator = By.xpath("//aside//ul//li[2]");
    private By pimLabelLocator = By.xpath("//header//span/h6");
    private By addButtonLocator = By.xpath("//div[@class='orangehrm-header-container']//button");
    private By addEmployeeLabelLocator = By.xpath("//div[@class='orangehrm-card-container']//h6");
    private By inputFirstNameLocator = By.name("firstName");
    private By inputMiddleNameLocator = By.name("middleName");
    private By inputLastNameLocator = By.name("lastName");
    private By switchLocator = By.cssSelector("span[class~='oxd-switch-input']");
    private By usernameInputLocator = By.xpath("//form[1]/div[1]/div[2]/div[3]//*//div[1]/div[2]/input[1]");
    private By passwordInputLocator = By.xpath("//form[1]//*//div[4]//*//div[1]/div[1]/div[2]/input[1]");
    private By confirmPasswordInputLocator = By.xpath("//form[1]//*//div[4]//*//div[2]/div[1]/div[2]/input[1]");
    private By saveButtonLocator = By.xpath("//button[text()=' Save ']");
    private By personalDetalesLocator = By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding']>h6");

    public void visitPIMPage() {
        BaseActions.loginWithCookie(ReadPropertyFile.getProperty("DASHBOARDPAGEURL"));
    }

    public PimPage switchToPimPage() {
        return new SidePanelPage().switchToPimPage();
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
        //switchToPimPage();
       // waitForPimLabel();
        clickAddButton();
        waitForAddEmployeeLabel();
        waitForPimLabel();
        return new PimPage();
    }

    public PimPage addEmployeeWithPassword(Employee employee) {
        BaseActions.fillInput(inputFirstNameLocator, employee.getFirstName());
        BaseActions.fillInput(inputMiddleNameLocator, employee.getMiddleName());
        BaseActions.fillInput(inputLastNameLocator, employee.getLastName());
        BaseActions.clickButton(switchLocator);
        Wait.waitFIsDisplayed(usernameInputLocator);
        BaseActions.fillInput(usernameInputLocator, employee.getUserName());
        BaseActions.fillInput(passwordInputLocator, employee.getPassword());
        BaseActions.fillInput(confirmPasswordInputLocator, employee.getPassword());
        BaseActions.clickButton(saveButtonLocator);
        return new PimPage();
    }

    public Boolean successfulCreateUser() {
       return Wait.waitFIsDisplayed(personalDetalesLocator).isDisplayed();
    }
}
