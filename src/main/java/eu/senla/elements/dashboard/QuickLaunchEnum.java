package eu.senla.elements.dashboard;

import lombok.Getter;

@Getter
public enum QuickLaunchEnum {
    AL("leave.assign_leave", "Assign Leave"),
    LL("leave.leave_list", "Leave List"),
    TS("time.employee_timesheet", "Timesheets"),
    APL("leave.apply_leave", "Apply Leave"),
    ML("leave.my_leave", "My Leave"),
    MTS("time.my_timesheet", "My Timesheet");

    private final String responseName;
    private final String nameToSee;

    QuickLaunchEnum(String responseName, String nameToSee) {
        this.responseName = responseName;
        this.nameToSee = nameToSee;
    }

}
