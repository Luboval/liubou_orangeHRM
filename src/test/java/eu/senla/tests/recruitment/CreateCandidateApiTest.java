package eu.senla.tests.recruitment;

import eu.senla.api.actions.createcandidate.CreateCandidateRequest;
import eu.senla.api.actions.createcandidate.SendCreateCandidateRequest;
import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.candidate.CandidateApiRequest;
import eu.senla.api.apielements.candidate.CandidateApiResponse;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CreateCandidateApiTest  extends BaseTest {

    @Test
    public void createCandidateApiTest() {
        log.info("Starting create Candidate Api Test");
        CandidateApiRequest candidateApiRequest = CreateCandidateRequest.createCandidateRequest();
        ApiResponse<CandidateApiResponse> candidateApiResponse = SendCreateCandidateRequest
                .sendCreateCandidateRequest(candidateApiRequest);

        log.info(candidateApiResponse.toString());

        assertThat(candidateApiRequest)
                .usingRecursiveComparison()
                .ignoringFields("contactNumber",
                        "dateOfApplication",
                        "comment",
                        "keywords",
                        "consentToKeepData",
                        "id")
                .isEqualTo(candidateApiResponse.data().get(0));
        log.info("Finish create Candidate Api Test");

    }
}
