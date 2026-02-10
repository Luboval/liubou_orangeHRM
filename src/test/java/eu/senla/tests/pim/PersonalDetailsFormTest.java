package eu.senla.tests.pim;

import eu.senla.management.dataactions.create.CreateEntity;
import eu.senla.pages.pimpages.PersonalDetailsPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_PLACEHOLDER;
import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_VALUE;
import static eu.senla.management.common.constants.AttributesForUITests.DATE_PLACEHOLDER;

@Slf4j
public class PersonalDetailsFormTest extends BaseTest {
    HashMap<String, String> employee;

    @Test (testName = "Personal Details Form Validation", groups = {"regression", "ext"})
    public void validatePersonalDetailsForm() {
        System.out.println("Start Personal Details Form Validation");
        employee = CreateEntity.createEmployee();

        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage()
                .openPersonalDetailsForm(Integer.parseInt(employee.get("employeeNumber")));

        log.info(employee.get("employeeNumber"));
        log.info(personalDetailsPage.getEmployeeIdInputAttr(ATTRIBUTE_VALUE));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(ATTRIBUTE_VALUE), employee.get("employeeFirstName"), "Wrong first name");
        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(ATTRIBUTE_PLACEHOLDER), "First Name", "Wrong first name placeholder");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(ATTRIBUTE_VALUE), employee.get("employeeMiddleName"), "Wrong middle name");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(ATTRIBUTE_PLACEHOLDER), "Middle Name", "Wrong middle name placeholder");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(ATTRIBUTE_VALUE), employee.get("employeeLastName"), "Wrong last name");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(ATTRIBUTE_PLACEHOLDER), "Last Name", "Wrong last name placeholder");
        softAssert.assertEquals(personalDetailsPage.getEmployeeIdInputAttr(ATTRIBUTE_VALUE), employee.get("employeeId"), "Wrong employeeId");
        softAssert.assertEquals(personalDetailsPage.getlicenceExpirityDateAttr(ATTRIBUTE_PLACEHOLDER), DATE_PLACEHOLDER, "Wrong license expiry date placeholder");
        softAssert.assertAll();

        System.out.println("Finish Personal Details Form Validation");
    }
}
