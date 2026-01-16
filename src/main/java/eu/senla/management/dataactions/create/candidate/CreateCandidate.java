package eu.senla.management.dataactions.create.candidate;

import com.fasterxml.jackson.core.type.TypeReference;
import eu.senla.elements.Candidate;
import eu.senla.management.utils.ReadFromJson;
import net.datafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class CreateCandidate {
     static  Faker faker = new Faker();

     public static Candidate createCorrectCandidate() {
        return Candidate.builder()
                .firstName(faker.name().firstName())
                .middleName(faker.name().nameWithMiddle())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .contactNumber(faker.phoneNumber().phoneNumber())
                .filePath("src/test/resources/files/Candidate.pdf")
                .build();
    }

    public static Candidate createCandidateWithWrongAttributes(String typeOfWrongCandidate) throws IOException {
         Map<String, Candidate> map = ReadFromJson.readFromJsonToMap(new File("src/test/resources/files/CandidateData.json"), new TypeReference<Map<String, Candidate>>() {});
        return map.get(typeOfWrongCandidate);
    }




}
