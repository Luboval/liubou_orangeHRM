package eu.senla.tests.recruitment;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.jobtitles.JobTitleApiResponse;
import eu.senla.management.common.RequestManager;
import eu.senla.pages.recruitment.RecruitmentVacanciesPage;
import eu.senla.tests.BaseTest;
import io.restassured.common.mapper.TypeRef;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.senla.management.common.SpecConfig.requestSpecification;
import static eu.senla.management.common.SpecConfig.responseSpecification;
import static eu.senla.management.common.constants.ApiPaths.GET_JOB_TITLES_API;

@Slf4j
public class VacanciesTest extends BaseTest {

    @Test
    public void jobTitleListTest() {
        log.info("Start JobTitle Test on Vacancies Page");

        List<String> recruitmentVacanciesPage = new RecruitmentVacanciesPage().getJobTitlesList();

        log.info(recruitmentVacanciesPage.toString());

        ApiResponse<JobTitleApiResponse> response = RequestManager.getRequestWithQueryParametersTypeRef(
                requestSpecification(),
                responseSpecification(),
                GET_JOB_TITLES_API,
                Map.of("limit", 0),
                new TypeRef<ApiResponse<JobTitleApiResponse>>() { }
        );

        log.info(response.toString());

        Assertions.assertThat(recruitmentVacanciesPage)
                .isEqualTo(response
                        .data()
                        .stream()
                        .map(JobTitleApiResponse::title)
                        .collect(Collectors.toList()));
    }
}
