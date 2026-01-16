package eu.senla.pages.pimpages;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Constants;
import eu.senla.management.common.Wait;
import eu.senla.management.utils.ReadPropertyFile;
import org.openqa.selenium.By;

public class PersonalDetailsPage {
    private By personalDetalesLocator = By.cssSelector("div[class='orangehrm-horizontal-padding orangehrm-vertical-padding']>h6");
    private By firstNameInputLocator = By.name("firstName");
    private By middleNameInputLocator = By.name("middleName");
    private By lastNameInputLocator = By.name("lastName");
    private By employeeIdInputLocator = By.xpath("//form/div[2]/div[1]/div[1]//input");
    private By licenceExpirityDateLocator = By.xpath("//form/div[2]/div[2]/div[2]//input");




    public void switchDirectlyToPersonalDetails(int empNumber) {
        BaseActions.visit(ReadPropertyFile.getProperty("BASEURL") + Constants.PERSONAL_DETAILS_FORM + empNumber);
    }

    public PersonalDetailsPage openPersonalDetailsForm(int empNumber) {
        switchDirectlyToPersonalDetails(empNumber);
        Wait.waitFPresence(personalDetalesLocator);
        return new PersonalDetailsPage();
    }

    public String getFirstNameInputAttr(String attribute) {
        return BaseActions.getValue(firstNameInputLocator, attribute);
    }

    public String getMiddleNameInputAttr(String attribute) {
        return BaseActions.getValue(middleNameInputLocator, attribute);
    }

    public String getLastNameInputAttr(String attribute) {
        return BaseActions.getValue(lastNameInputLocator, attribute);
    }

    public String getEmployeeIdInputAttr(String attribute) {
        return BaseActions.getValue(employeeIdInputLocator, attribute);
    }

    public String getlicenceExpirityDateAttr(String attribute) {
        return BaseActions.getValue(licenceExpirityDateLocator, attribute);
    }




}
