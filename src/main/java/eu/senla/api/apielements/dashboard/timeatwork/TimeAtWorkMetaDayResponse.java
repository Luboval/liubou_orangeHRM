package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaDayResponse(
        TimeAtWorkMetaDateResponse currentDate,
        TimeAtWorkMetaTimeResponse totalTime
) { }
