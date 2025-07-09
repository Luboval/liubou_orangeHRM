package eu.senla.tests.pim;

import eu.senla.apiactions.createemployee.CreateEmployeeRequest;
import eu.senla.apiactions.createemployee.CreateEmployeeResponse;
import eu.senla.apiactions.createemployee.EmployeeRequest;
import eu.senla.apiactions.createemployee.SendCreateEmployeeRequest;
import eu.senla.management.common.Constants;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.pim.PersonalDetailsPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PersonalDetailsFormTest extends BaseTest {
    int employeeNumber;
    String employeeFirstName;
    String employeeMiddleName;
    String employeeLastName;
    String employeeId;


    public PersonalDetailsFormTest() {
        super(new ApiLoginStrategy());
    }

    @BeforeClass
    void createEmployee() {
        EmployeeRequest employeeRequest = CreateEmployeeRequest.createEmployeeRequest();
        CreateEmployeeResponse createEmployeeResponse = SendCreateEmployeeRequest.sendCreateEmployeeRequest(employeeRequest);
        employeeNumber = createEmployeeResponse.getData().get(0).getEmpNumber();
        employeeFirstName = createEmployeeResponse.getData().get(0).getFirstName();
        employeeMiddleName = createEmployeeResponse.getData().get(0).getMiddleName();
        employeeLastName = createEmployeeResponse.getData().get(0).getLastName();
        employeeId = createEmployeeResponse.getData().get(0).getEmployeeId();
    }

    @Test
    public void validatePersonalDetailsForm() {
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage()
                .openPersonalDetailsForm(employeeNumber);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(Constants.ATTRIBUTE_VALUE), employeeFirstName, "Wrong first name");
        softAssert.assertEquals(personalDetailsPage.getFirstNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "First Name", "Wrong first name placeholder");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(Constants.ATTRIBUTE_VALUE), employeeMiddleName, "Wrong middle name");
        softAssert.assertEquals(personalDetailsPage.getMiddleNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Middle Name", "Wrong middle name placeholder");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(Constants.ATTRIBUTE_VALUE), employeeLastName, "Wrong last name");
        softAssert.assertEquals(personalDetailsPage.getLastNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Last Name", "Wrong last name placeholder");
        softAssert.assertEquals(personalDetailsPage.getEmployeeIdInputAttr(Constants.ATTRIBUTE_VALUE), employeeId, "Wrong employeeId");
        softAssert.assertEquals(personalDetailsPage.getlicenceExpirityDateAttr(Constants.ATTRIBUTE_PLACEHOLDER), Constants.DATE_PLACEHOLDER, "Wrong license expiry date placeholder");
        softAssert.assertAll();
    }
}
