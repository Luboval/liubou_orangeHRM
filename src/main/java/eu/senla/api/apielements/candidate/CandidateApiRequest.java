package eu.senla.api.apielements.candidate;

public record CandidateApiRequest(
        String firstName,
        String lastName,
        String middleName,
        String contactNumber,
        String email,
        String dateOfApplication,
        String comment,
        String keywords,
        boolean consentToKeepData
        ) {
}
