package eu.senla.api.apielements.dashboard.empdistribution;

public record EmpByLocation(
        int count,
        SubUnitOrLocation location
) {
}
