package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkDataResponse(
        TimeAtWorkDataWorkDayResponse workDay,
        TimeAtWorkDataTotalTimeResponse totalTime
) { }
