package eu.senla.management.common.constants;

import lombok.Getter;

@Getter
public class ApiPaths {
    public static final String CREATE_EMPLOYEE_API = "/api/v2/pim/employees";
    public static final String GET_USERS_API_POINT = "/api/v2/admin/users";
    public static final String GET_JOB_TITLES_API = "/api/v2/admin/job-titles";
    public static final String POST_CREATE_CANDIDATE_API = "api/v2/recruitment/candidates";
    public static final String GET_MY_ACTIONS_API = "/api/v2/dashboard/employees/action-summary";
    public static final String GET_TIME_AT_WORK_API = "/api/v2/dashboard/employees/time-at-work";
    public static final String GET_QUICK_LAUNCH_API = "/api/v2/dashboard/shortcuts";
    public static final String GET_BUZZ_LAST_POST_API = "/api/v2/buzz/feed";
    public static final String GET_EMPL_BY_SUB_UNIT = "/api/v2/dashboard/employees/subunit";
    public static final String GET_EMPL_BY_LOCATION = "/api/v2/dashboard/employees/locations";
}
