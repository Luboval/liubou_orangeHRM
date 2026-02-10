package eu.senla.api.createemployee;

public record EmployeeRequest(
        String employeeId,
        String firstName,
        String lastName,
        String empPicture,
        String middleName) {
}
