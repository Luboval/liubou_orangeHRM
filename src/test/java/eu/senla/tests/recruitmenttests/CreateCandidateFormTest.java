package eu.senla.tests.recruitmenttests;

import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.recruitment.RecruitmentCandidatesPage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateCandidateFormTest extends BaseTest {
    private final String attrPlaceholder = "placeholder";

    public CreateCandidateFormTest() {
        super(new ApiLoginStrategy());
    }

    @Test (testName = "Validate Create Candidate Form")
    public void validateCreateCandidateForm() {
        RecruitmentCandidatesPage recruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .openAddCandidateForm();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(recruitmentCandidatesPage.getFirstNameInputAttr(attrPlaceholder), "First Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getMiddleNameInputAttr(attrPlaceholder), "Middle Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getLastNameInputAttr(attrPlaceholder), "Last Name", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getEmailInputAttr(attrPlaceholder), "Type here", "Wrong placeholder");
        softAssert.assertEquals(recruitmentCandidatesPage.getContactNumberInputAttr(attrPlaceholder), "Type here", "Wrong placeholder");
        softAssert.assertAll();

    }
}
