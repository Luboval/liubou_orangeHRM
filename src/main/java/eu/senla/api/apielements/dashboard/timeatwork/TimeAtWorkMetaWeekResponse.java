package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaWeekResponse(
        TimeAtWorkMetaDateResponse startDate,
        TimeAtWorkMetaDateResponse endDate,
        TimeAtWorkDataTotalTimeResponse totalTime
) { }
