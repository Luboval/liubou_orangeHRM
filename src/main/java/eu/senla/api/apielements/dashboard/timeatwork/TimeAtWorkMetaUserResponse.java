package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaUserResponse(
       int empNumber,
       String firstName,
       String middleName,
       String lastName,
       String terminationId
) { }
