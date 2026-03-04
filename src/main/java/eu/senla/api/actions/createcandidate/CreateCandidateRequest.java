package eu.senla.api.actions.createcandidate;

import eu.senla.api.apielements.candidate.CandidateApiRequest;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateCandidateRequest {
    static Faker faker = new Faker();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static CandidateApiRequest createCandidateRequest() {
        return new CandidateApiRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().nameWithMiddle(),
                faker.phoneNumber().subscriberNumber(),
                faker.internet().emailAddress(),
                LocalDate.now().format(formatter),
                null,
                null,
                false);
    }

}
