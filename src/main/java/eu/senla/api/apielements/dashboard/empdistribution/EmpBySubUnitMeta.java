package eu.senla.api.apielements.dashboard.empdistribution;

public record EmpBySubUnitMeta(
        int otherEmployeeCount,
        int unassignedEmployeeCount,
        int totalSubunitCount
) {
}
