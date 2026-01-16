package eu.senla.tests.recruitment;

import eu.senla.elements.Candidate;
import eu.senla.pages.recruitment.ErrorRecruitmentCandidatesPage;
import eu.senla.pages.recruitment.RecruitmentCandidatesPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static eu.senla.management.common.Constants.*;
import static eu.senla.management.dataactions.create.candidate.CreateCandidate.createCandidateWithWrongAttributes;
import static eu.senla.management.dataactions.create.candidate.CreateCandidate.createCorrectCandidate;


@Slf4j
public class CreateCandidateTest extends BaseTest {
    private final Candidate candidate = createCorrectCandidate();

    @Test (testName = "Create Candidate", groups = {"smoke", "regression"})
    public void createCandidateTest() {
        System.out.println("Start Create Candidate");
        RecruitmentCandidatesPage recruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .addCandidateWithFile(candidate);

        Assert.assertEquals(recruitmentCandidatesPage.getProfileFirsName("value"), candidate.getFirstName(), "Incorrect");

        System.out.println("Finish Create Candidate");
    }

    @Test (testName = "Create candidate with wrong First Name", groups = {"smoke", "regression"})
    public void createCandidateWithWrongFirstName() throws IOException {
        log.info("Start Test Create candidate with wrong First Name");
        //Candidate wrongCandidate = createCandidateWithWrongAttributes();
       // log.info(wrongCandidate.getLastName());
        ErrorRecruitmentCandidatesPage errorRecruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .errorAddCandidateWithoutFile(createCandidateWithWrongAttributes("CandidateWithWrongName"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_TOP_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_LEFT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_RIGHT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_BOTTOM_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldErrorTextColor(), ERROR_ICON_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldErrorText(), getErrors().get("required").getMessage());
        softAssert.assertAll();

        log.info("Finish Test Create candidate with wrong First Name");

    }

    @Test (testName = "Create candidate with wrong Email", groups = {"smoke", "regression"})
    public void createCandidateWithWrongEmail() throws IOException {
        log.info("Start Test Create candidate with wrong Email");
       // Candidate wrongCandidate = createCandidateWithWrongAttributes();
        ErrorRecruitmentCandidatesPage errorRecruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .errorAddCandidateWithoutFile(createCandidateWithWrongAttributes("CandidateWithWrongEmail"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_TOP_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_LEFT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_RIGHT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_BOTTOM_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputErrorTextColor(), ERROR_ICON_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputErrorText(), getErrors().get("wrongEmailFormat").getMessage());
        softAssert.assertAll();

        log.info("Finish Test Create candidate with wrong Email");

    }

    @Test (testName = "Create candidate with wrong First Name and Email", groups = {"smoke", "regression"})
    public void createCandidateWithWrongFirstNameAndEmail() throws IOException {
        log.info("Start Test Create candidate with wrong First Name and Email");
        // Candidate wrongCandidate = createCandidateWithWrongAttributes();
        ErrorRecruitmentCandidatesPage errorRecruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .errorAddCandidateWithoutFile(createCandidateWithWrongAttributes("CandidateWithWrongFirstNameAndEmail"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_TOP_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_LEFT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_RIGHT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldBordersColor(ATTRIBUTE_BORDER_BOTTOM_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldErrorTextColor(), ERROR_ICON_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getFirstNameFieldErrorText(), getErrors().get("required").getMessage());
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_TOP_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_LEFT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_RIGHT_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputBordersColor(ATTRIBUTE_BORDER_BOTTOM_COLOR), ERROR_FIELD_BORDER_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputErrorTextColor(), ERROR_ICON_COLOR);
        softAssert.assertEquals(errorRecruitmentCandidatesPage.getEmailInputErrorText(), getErrors().get("required").getMessage());
        softAssert.assertAll();

        log.info("Finish Test Create candidate with wrong First Name and Email");

    }
}
