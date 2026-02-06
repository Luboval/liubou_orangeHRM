package eu.senla.management.dataactions.create.candidate;

import com.fasterxml.jackson.core.type.TypeReference;
import eu.senla.elements.Candidate;
import eu.senla.management.utils.ReadFromExcelOrCsvFile;
import eu.senla.management.utils.ReadFromJsonFile;
import net.datafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

    public static Candidate createCandidateWithWrongAttributesAndNoFileToUpload(String typeOfWrongCandidate) throws IOException {
         Map<String, Candidate> map = ReadFromJsonFile.readFromJsonFileToMap(new File("src/test/resources/files/CandidateData.json"), new TypeReference<Map<String, Candidate>>() { });
        return map.get(typeOfWrongCandidate);
    }

    public static Candidate createCorrectCandidateFromCsv() throws Exception {
        ReadFromExcelOrCsvFile<Candidate> reader = new ReadFromExcelOrCsvFile<>(Candidate.class);
        List<Candidate> candidates = reader.readCsv("src/test/resources/files/CandidateData.csv");
        System.out.println(Arrays.toString(candidates.toArray()));

        return candidates.get(0);
    }

    public static Candidate createCorrectCandidateFromXls() throws Exception {
        ReadFromExcelOrCsvFile<Candidate> reader = new ReadFromExcelOrCsvFile<>(Candidate.class);
        List<Candidate> candidates = reader.readExcel("src/test/resources/files/CandidateData.xlsx");
        System.out.println(Arrays.toString(candidates.toArray()));

        return candidates.get(0);
    }




}
