package eu.senla.pages.leave;

import eu.senla.management.common.Constants;
import eu.senla.elements.AssignLeave;
import eu.senla.management.utils.ReadPropertyFile;
import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import org.openqa.selenium.By;

public class AssignLeavePage {
    private By formLocator = By.cssSelector("form");
    private By employeeNameInputLocator = By.xpath("//form//input[1]");
    private By leaveTypeLabelLocator = By.xpath("//form/div[2]//*//label[text()='Leave Type']");
    private By leaveTypeInputLocator = By.xpath("//form/div[2]//*//div[@class='oxd-select-text-input']");
    private By leaveTypeArrowLocator = By.cssSelector("i[class~='oxd-select-text--arrow']");
    private By leaveTypeItemLocator = By.xpath("//*[@role='listbox']//*[text()='CAN - Bereavement']");
    private By fromDateLocator = By.xpath("//form/div[3]/div/div[1]//input[1]");
    private By toDateLocator = By.xpath("//form/div[3]/div/div[2]//input[1]");
    private By assignButtonLocator = By.cssSelector("button[type='submit']");
    private By confirmFormLocator = By.xpath("//*[@role='document']");
    private By okButtonLocator = By.xpath("//*[@role='document']//button[2]");
    private By successMessageLocator = By.cssSelector("[aria-live='assertive']");



    public void switchDirectlyToAssignLeavePage() {
        BaseActions.visit(ReadPropertyFile.getProperty("BASEURL") + Constants.ASSIGN_LEAVE_FORM);
    }

    public AssignLeavePage openAssignLeaveForm() {
        switchDirectlyToAssignLeavePage();
        Wait.waitFPresence(formLocator);
        return new AssignLeavePage();

    }

    public AssignLeavePage assignLeave(AssignLeave assignLeave) {
        openAssignLeaveForm();
        BaseActions.fillInput(employeeNameInputLocator, assignLeave.getEmployeeName());
        Wait.waitFPresence(By.xpath("//*[@role='listbox']//*[text()='" + assignLeave.getEmployeeName() + "']")).click();
        BaseActions.displayAfterClick(leaveTypeArrowLocator, leaveTypeItemLocator).click();
        BaseActions.fillInput(fromDateLocator, assignLeave.getFromDate());
        BaseActions.clickButton(toDateLocator);
        Wait.waitFChanged(toDateLocator, Constants.ATTRIBUTE_VALUE, assignLeave.getFromDate());
        BaseActions.clearWithKeys(toDateLocator);
        BaseActions.fillInput(toDateLocator, assignLeave.getToDate());
        BaseActions.clickButton(assignButtonLocator);
        Wait.waitFPresence(confirmFormLocator);
        BaseActions.clickButton(okButtonLocator);
        Wait.waitFPresence(formLocator);

        return new AssignLeavePage();

    }

    public String getEmployeeNameInputAttr(String attribute) {
        return BaseActions.getValue(employeeNameInputLocator, attribute);
    }

    public String getLeaveTypeInputAttr(String attribute) {
        return BaseActions.getValue(leaveTypeInputLocator, attribute);
    }

    public String getLeaveTypeLabelAttr(String attribute) {
        return BaseActions.getValue(leaveTypeLabelLocator, attribute);
    }

    public String getSuccessMessageText(String attribute) {
        return BaseActions.getValue(successMessageLocator, attribute);
    }
}
