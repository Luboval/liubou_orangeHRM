package eu.senla.management.common.constants;

import lombok.Getter;

@Getter
public class PagesPaths {
    public static final String JOB_TITLES_PAGE = "/admin/viewJobTitleList";
    public static final String RECRUITMENT_PAGE = "/recruitment/viewCandidates";
    public static final String RECRUITMENT_ADD_CANDIDATE_PAGE = "/recruitment/addCandidate";
    public static final String ASSIGN_LEAVE_FORM = "/leave/assignLeave";
    public static final String PERSONAL_DETAILS_FORM = "/pim/viewPersonalDetails/empNumber/";
    public static final String LOGOUT_URL = "/auth/logout";
    public static final String SUCCESSFUL_LOGIN_PAGE = "/dashboard/index";
    public static final String LOGIN_PAGE = "/auth/login";
    public static final String RECRUITMENT_VACANCIES_PAGE = "/recruitment/viewJobVacancy";
}
