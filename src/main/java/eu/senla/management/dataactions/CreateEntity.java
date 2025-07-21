package eu.senla.management.dataactions;

import eu.senla.apiactions.createemployee.CreateEmployeeRequest;
import eu.senla.apiactions.createemployee.CreateEmployeeResponse;
import eu.senla.apiactions.createemployee.EmployeeRequest;
import eu.senla.apiactions.createemployee.SendCreateEmployeeRequest;
import eu.senla.elements.AssignLeave;
import eu.senla.elements.Candidate;
import eu.senla.elements.Employee;
import eu.senla.elements.Title;
import net.bytebuddy.utility.RandomString;
import net.datafaker.Faker;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class CreateEntity {
     static  Faker faker = new Faker();
    private static String employeeName;
    private static AssignLeave assigneLeave;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");

    public static Employee generateEmployee() {
        return Employee.builder()
                .firstName(faker.name().firstName())
                .middleName(faker.name().nameWithMiddle())
                .lastName(faker.name().lastName())
                .userName(faker.internet().username())
                .password(RandomString.make() + "#*" + Math.random())
                .build();

    }

    public static Title createTitle() {
        return Title.builder()
                .jobTitle(faker.name().title())
                .jobDescription(faker.text().text(20))
                .note(faker.text().text(10))
                .build();


    }

    public static Pair<AssignLeave, String> createassignLeave() {

        EmployeeRequest employeeRequest = CreateEmployeeRequest.createEmployeeRequest();
        CreateEmployeeResponse createEmployeeResponse = SendCreateEmployeeRequest.sendCreateEmployeeRequest(employeeRequest);
        employeeName = createEmployeeResponse.getData().get(0).getFirstName() + " " + createEmployeeResponse.getData().get(0).getMiddleName() + " " + createEmployeeResponse.getData().get(0).getLastName();

        assigneLeave = AssignLeave.builder()
                .employeeName(employeeName)
                .fromDate(LocalDate.now().plus(Period.ofDays(0)).format(formatter))
                .toDate(LocalDate.now().plus(Period.ofDays(20)).format(formatter))
                .build();

        return Pair.of(assigneLeave, employeeName);

    }

    public static Candidate generateCandidate() {
        return Candidate.builder()
                .firstName(faker.name().firstName())
                .middleName(faker.name().nameWithMiddle())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .contactNumber(faker.phoneNumber().phoneNumber())
                .filePath("src/test/resources/files/Candidate.pdf")
                .build();
    }

    public static HashMap<String, String> createEmployee() {
        EmployeeRequest employeeRequest = CreateEmployeeRequest.createEmployeeRequest();
        CreateEmployeeResponse createEmployeeResponse = SendCreateEmployeeRequest.sendCreateEmployeeRequest(employeeRequest);
        HashMap<String, String> employee = new HashMap<>();
        employee.put("employeeNumber", "" + createEmployeeResponse.getData().get(0).getEmpNumber());
        employee.put("employeeFirstName", createEmployeeResponse.getData().get(0).getFirstName());
        employee.put("employeeMiddleName", createEmployeeResponse.getData().get(0).getMiddleName());
        employee.put("employeeLastName", createEmployeeResponse.getData().get(0).getLastName());
        employee.put("employeeId", createEmployeeResponse.getData().get(0).getEmployeeId());
        return employee;
    }


}
