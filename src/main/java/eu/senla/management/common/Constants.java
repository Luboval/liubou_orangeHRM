package eu.senla.management.common;

import lombok.Getter;

@Getter
public class Constants {
    public static final String JOB_TITLES_PAGE = "/admin/viewJobTitleList";
    public static final String RECRUITMENT_PAGE = "/recruitment/viewCandidates";
    public static final String RECRUITMENT_ADD_CANDIDATE = "/recruitment/addCandidate";
    public static final String CREATE_EMPLOYEE_API = "/api/v2/pim/employees";
    public static final String ASSIGN_LEAVE_FORM = "/leave/assignLeave";
    public static final String PERSONAL_DETAILS_FORM = "/pim/viewPersonalDetails/empNumber/";
    public static final String ATTRIBUTE_PLACEHOLDER = "placeholder";
    public static final String ATTRIBUTE_VALUE = "_value";
    public static final String DATE_PLACEHOLDER = "yyyy-dd-mm";
    public static final String ERROR_ICON_COLOR = "rgba(235, 9, 16, 1)";
    public static final String LOGOUT_URL = "/auth/logout";
    public static final String SUCCESSFUL_LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    public static final String LOGIN_PAGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

}
