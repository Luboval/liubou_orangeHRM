package eu.senla.tests.assignleave;

import eu.senla.management.common.Constants;
import eu.senla.management.dataactions.create.CreateEntity;
import eu.senla.pages.leave.AssignLeavePage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAssignLeaveTest extends BaseTest {

    @Test (testName = "Create Assign Leave Test", groups = {"smoke", "regression"})
    public void createAssignLeave() {
        System.out.println("Start Create Assign Leave Test");

       AssignLeavePage assignLeavePage = new AssignLeavePage()
                .assignLeave(CreateEntity.createassignLeave().getKey());

        Assert.assertEquals(assignLeavePage.getSuccessMessageText(Constants.ATTRIBUTE_TEXTCONTENT), "SuccessSuccessfully Saved×", "Message is incorrect");

        System.out.println("Finish Create Assign Leave Test");

    }
}
