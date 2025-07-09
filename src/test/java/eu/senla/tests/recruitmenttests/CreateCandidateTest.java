package eu.senla.tests.recruitmenttests;

import eu.senla.elements.Candidate;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.recruitment.RecruitmentCandidatesPage;
import eu.senla.tests.BaseTest;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateCandidateTest extends BaseTest {
    private Candidate candidate;

    public CreateCandidateTest() {
        super(new ApiLoginStrategy());
    }


    @BeforeClass
    void generateCandidate() {
        Faker faker = new Faker();
        candidate = Candidate.builder()
                .firstName(faker.name().firstName())
                .middleName(faker.name().nameWithMiddle())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .contactNumber(faker.phoneNumber().phoneNumber())
                .filePath("src/test/resources/files/Candidate.pdf")
                .build();
    }


    @Test (testName = "Create Candidate")
    public void createCandidateTest() {
        RecruitmentCandidatesPage recruitmentCandidatesPage = new RecruitmentCandidatesPage()
                .addCandidate(candidate);

        Assert.assertEquals(recruitmentCandidatesPage.getProfileFirsName("value"), candidate.getFirstName(), "Incorrect");
    }
}
