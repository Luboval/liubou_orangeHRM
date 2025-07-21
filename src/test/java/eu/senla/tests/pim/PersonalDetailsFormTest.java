package eu.senla.tests.pim;

import eu.senla.management.common.Constants;
import eu.senla.management.dataactions.CreateEntity;
import eu.senla.pages.pimpages.PersonalDetailsPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class PersonalDetailsFormTest extends BaseTest {
    HashMap<String, String> employee;

    @Test (testName = "Personal Details Form Validation", groups = {"regression", "ext"})
    public void validatePersonalDetailsForm() {
        System.out.println("Start Personal Details Form Validation");
        employee = CreateEntity.createEmployee();

        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage()
                .openPersonalDetailsForm(Integer.parseInt(employee.get("employeeNumber")));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(Constants.ATTRIBUTE_VALUE), employee.get("employeeFirstName"), "Wrong first name");
        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "First Name", "Wrong first name placeholder");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(Constants.ATTRIBUTE_VALUE), employee.get("employeeMiddleName"), "Wrong middle name");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Middle Name", "Wrong middle name placeholder");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(Constants.ATTRIBUTE_VALUE), employee.get("employeeLastName"), "Wrong last name");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Last Name", "Wrong last name placeholder");
        softAssert.assertEquals(personalDetailsPage.getEmployeeIdInputAttr(Constants.ATTRIBUTE_VALUE), employee.get("employeeId"), "Wrong employeeId");
        softAssert.assertEquals(personalDetailsPage.getlicenceExpirityDateAttr(Constants.ATTRIBUTE_PLACEHOLDER), Constants.DATE_PLACEHOLDER, "Wrong license expiry date placeholder");
        softAssert.assertAll();

        System.out.println("Finish Personal Details Form Validation");
    }
}
