package eu.senla.tests.recruitment;

import eu.senla.elements.Candidate;
import eu.senla.management.dataactions.CreateEntity;
import eu.senla.pages.recruitment.RecruitmentCandidatesPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCandidateTest extends BaseTest {
    private final Candidate candidate = CreateEntity.generateCandidate();

    @Test (testName = "Create Candidate", groups = {"smoke", "regression"})
    public void createCandidateTest() {
        System.out.println("Start Create Candidate");
        RecruitmentCandidatesPage recruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .addCandidate(candidate);

        Assert.assertEquals(recruitmentCandidatesPage.getProfileFirsName("value"), candidate.getFirstName(), "Incorrect");

        System.out.println("Finish Create Candidate");
    }
}
