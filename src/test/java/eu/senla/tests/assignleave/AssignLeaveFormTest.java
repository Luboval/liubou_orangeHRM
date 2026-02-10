package eu.senla.tests.assignleave;

import eu.senla.pages.leave.AssignLeavePage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_PLACEHOLDER;

public class AssignLeaveFormTest extends BaseTest {

    @Test (testName = "Assign Form Validation", groups = {"ext", "regression"})
    public void validateAssignFormTest() {
        System.out.println("Start Assign Form Validation");
        AssignLeavePage assignLeavePage = new AssignLeavePage()
                .openAssignLeaveForm();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(assignLeavePage.getEmployeeNameInputAttr(ATTRIBUTE_PLACEHOLDER), "Type for hints...", "Wrong placeholder");
        softAssert.assertTrue(assignLeavePage.getLeaveTypeLabelAttr("class").contains("oxd-input-field-required"));
        softAssert.assertEquals(assignLeavePage.getLeaveTypeInputAttr("innerText"), "-- Select --", "Wrong text");
        softAssert.assertAll();

        System.out.println("Finish Assign Form Validation");

    }
}
