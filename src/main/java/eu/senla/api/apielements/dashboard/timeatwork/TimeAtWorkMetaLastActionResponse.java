package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaLastActionResponse(
        String state,
        String utcDate,
        String utcTime,
        String userDate,
        String userTime,
        String timezoneOffset
) { }
