package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaResponse(
    TimeAtWorkMetaLastActionResponse lastAction,
    TimeAtWorkDataWorkDayResponse currentDay,
    TimeAtWorkMetaWeekResponse currentWeek,
    TimeAtWorkMetaUserResponse currentUser
) { }
