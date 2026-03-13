package eu.senla.api.apielements.dashboard.timeatwork;

public record TimeAtWorkMetaResponse(
    TimeAtWorkMetaLastActionResponse lastAction,
    TimeAtWorkMetaDayResponse currentDay,
    TimeAtWorkMetaWeekResponse currentWeek,
    TimeAtWorkMetaUserResponse currentUser
) { }
