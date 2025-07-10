package eu.senla.tests.assignleave;

import eu.senla.management.common.Constants;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.leave.AssignLeavePage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssignLeaveFormTest extends BaseTest {

    public AssignLeaveFormTest() {
        super(new ApiLoginStrategy());
    }

    @Test
    public void validateAssignFormTest() {
        AssignLeavePage assignLeavePage = new AssignLeavePage()
                .openAssignLeaveForm();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(assignLeavePage.getEmployeeNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Type for hints...", "Wrong placeholder");
        softAssert.assertTrue(assignLeavePage.getLeaveTypeLabelAttr("class").contains("oxd-input-field-required"));
        softAssert.assertEquals(assignLeavePage.getLeaveTypeInputAttr("innerText"), "-- Select --", "Wrong text");
        softAssert.assertAll();

    }
}
