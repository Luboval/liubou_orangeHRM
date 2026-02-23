package eu.senla.api.apielements.candidate;

public record CandidateApiResponse(
        String email,
        String firstName,
        String lastName,
        String middleName,
        int id) {
}
