package eu.senla.api.apielements.dashboard.quicklaunch;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuickLaunchResponse(
        @JsonProperty("leave.assign_leave")
        boolean leaveAssignLeave,
        @JsonProperty("leave.leave_list")
        boolean leaveLeaveList,
        @JsonProperty("leave.apply_leave")
        boolean leaveApplyLeave,
        @JsonProperty("leave.my_leave")
        boolean leaveMyLeave,
        @JsonProperty("time.employee_timesheet")
        boolean timeEmployeeTimesheet,
        @JsonProperty("time.my_timesheet")
        boolean timeMyTimesheet
) {
}
