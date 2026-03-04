package eu.senla.api.apielements.employee;

public record EmployeeRequest(
        String employeeId,
        String firstName,
        String lastName,
        String empPicture,
        String middleName) {
}
