package eu.senla.apiactions.createemployee;

import net.datafaker.Faker;

public class CreateEmployeeRequest {
    static Faker faker = new Faker();

    public static EmployeeRequest createEmployeeRequest() {

        return new EmployeeRequest(
                faker.number().digits(4),
                faker.name().firstName(),
                faker.name().lastName(),
                null,
                faker.name().firstName()
        );
    }
}
