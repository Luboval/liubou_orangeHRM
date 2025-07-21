package eu.senla.tests.recruitment;

import eu.senla.management.common.Constants;
import eu.senla.pages.recruitment.RecruitmentCandidatesPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateCandidateFormTest extends BaseTest {

    @Test (testName = "Validate Create Candidate Form", groups = {"regression", "ext"})
    public void validateCreateCandidateForm() {
        System.out.println("Start Validate Create Candidate Form");
        RecruitmentCandidatesPage recruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .openAddCandidateForm();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(recruitmentCandidatesPage.getFirstNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "First Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getMiddleNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Middle Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getLastNameInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Last Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getEmailInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Type here", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getContactNumberInputAttr(Constants.ATTRIBUTE_PLACEHOLDER), "Type here", "Wrong placeholder");
        softAssert.assertAll();

        System.out.println("Finish Validate Create Candidate Form");

    }
}
