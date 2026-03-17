package eu.senla.api.apielements.dashboard.empdistribution;

public record EmpByLocationMeta(
        int otherEmployeeCount,
        int unassignedEmployeeCount,
        int totalLocationCount
) {
}
