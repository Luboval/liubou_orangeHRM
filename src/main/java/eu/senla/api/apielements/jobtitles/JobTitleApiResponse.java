package eu.senla.api.apielements.jobtitles;

public record JobTitleApiResponse(
        String description,
        int id,
        JobSpecification jobSpecification,
        String note,
        String title) {
}
