package eu.senla.api.apielements.dashboard.emponleave;

import eu.senla.api.apielements.users.UserDataEmployee;

public record EmpOnLeaveMain(
        String date,
        String duration,
        UserDataEmployee employee,
        String endTime,
        int id,
        LeaveType leaveType,
        int lengthHours,
        String startTime
) {
}
