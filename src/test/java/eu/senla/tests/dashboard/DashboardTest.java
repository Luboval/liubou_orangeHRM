package eu.senla.tests.dashboard;

import eu.senla.api.actions.dashboardwidgets.buzz.SendBuzzLatastPostRequest;
import eu.senla.api.actions.dashboardwidgets.employeeonleave.SendEmployeeOnLeaveRequest;
import eu.senla.api.actions.dashboardwidgets.myactions.SendMyActionsRequest;
import eu.senla.api.actions.dashboardwidgets.quicklaunch.SendQuickLaunchRequest;
import eu.senla.api.actions.dashboardwidgets.timeatwork.SendTimeAtWorkRequest;
import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.buzzlatestposts.BuzzLatestPost;
import eu.senla.api.apielements.dashboard.empdistribution.EmpByLocation;
import eu.senla.api.apielements.dashboard.empdistribution.EmpByLocationMeta;
import eu.senla.api.apielements.dashboard.empdistribution.EmpBySubUnit;
import eu.senla.api.apielements.dashboard.empdistribution.EmpBySubUnitMeta;
import eu.senla.api.apielements.dashboard.emponleave.EmpOnLeaveMain;
import eu.senla.api.apielements.dashboard.emponleave.EmpOnLeaveMeta;
import eu.senla.api.apielements.dashboard.myactions.MyActionsResponse;
import eu.senla.api.apielements.dashboard.quicklaunch.QuickLaunchResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkDataResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkMetaResponse;
import eu.senla.api.utils.DateTimeUtil;
import eu.senla.api.utils.ObjectToMapConverter;
import eu.senla.api.utils.StringFormatUtil;
import eu.senla.elements.dashboard.BuzzLatestPosts;
import eu.senla.elements.dashboard.DashboardElementsTitles;
import eu.senla.elements.dashboard.EmpOnLeave;
import eu.senla.elements.dashboard.PieChartTooltip;
import eu.senla.elements.dashboard.QuickLaunchEnum;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eu.senla.api.actions.dashboardwidgets.empdistribution.SendEmployeeDistribByLocation.sendEmployeeByLocationRequest;
import static eu.senla.api.actions.dashboardwidgets.empdistribution.SendEmployeeDistribBySubUnit.sendEmployeeBySubUnitRequest;
import static eu.senla.api.utils.DateTimeUtil.convertDateFormatSafe;
import static eu.senla.api.utils.DateTimeUtil.convertUtcToLocal;
import static eu.senla.api.utils.DateTimeUtil.formatDateWithoutSuffix;
import static eu.senla.api.utils.DateTimeUtil.formatTimeTo12Hour;
import static eu.senla.api.utils.StringFormatUtil.capitalizeWordsStream;

@Slf4j
public class DashboardTest extends BaseTest {

    @Test (testName = "Dashboard validation", groups = {"regression", "ext"})
    public void testDashboard() {
        log.info("Start Dashboard validation");
        List<String> widgetTitles = new DashboardPage()
                .getAllWidgetTitles();

        Assert.assertEquals(new HashSet<>(widgetTitles), new HashSet<>(DashboardElementsTitles.getDashboardWidgetTitles()), "Widgets do not match");
        log.info("Finish Dashboard validation");
    }

    @Test (testName = "MyAction widget validation", groups = {"smoke", "regression", "ext"})
    public void testMyActionWidget() {
        log.info("Start MyAction widget validation");

        Map<String, Integer> myActions = new DashboardPage()
                .getAllMyActionsItems();

        log.info(myActions.toString());

        ApiResponse<MyActionsResponse, Meta> myActionsApiResponse = SendMyActionsRequest.sendMyActionRequest();

        log.info(myActionsApiResponse.toString());

        Assertions.assertThat(myActions)
                        .isEqualTo(
                                myActionsApiResponse
                                        .data()
                                        .stream()
                                        .collect(Collectors.toMap(MyActionsResponse::getGroup, MyActionsResponse::getPendingActionCount))
                        );
        log.info("Finish MyAction widget validation");
    }

    @Test (testName = "Time at Work widget validation", groups = {"smoke", "regression", "ext"})
    public void testTimeAtWorkWidget() {
        log.info("Starting Time at Work widget validation");

        DashboardPage dashboardPage = new DashboardPage()
                .waitForDashboardGrid();

        ApiResponse<TimeAtWorkDataResponse, TimeAtWorkMetaResponse> apiResponse = SendTimeAtWorkRequest.sendTimeAtWorkRequest();
        log.info(apiResponse.toString());

        TimeAtWorkMetaResponse metaResponse = apiResponse.meta().get(0);

        String expectedDays = (metaResponse.lastAction().userDate().equals(LocalDate.now().toString()))
                ? "Today at " + formatTimeTo12Hour(metaResponse.lastAction().userTime())
                : DateTimeUtil.formatDateTime(
                    metaResponse.lastAction().userDate(),
                    metaResponse.lastAction().userTime()
        );

        // Display workTimeToday validation
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dashboardPage.getTimeAtWorkPuncedInTime())
                    .isEqualTo(metaResponse.currentDay().totalTime().hours() + "h "
                            + metaResponse.currentDay().totalTime().minutes() + "m Today");
            softly.assertThat(dashboardPage.getTimeAtWorkPurchedOut())
                    .isEqualTo(capitalizeWordsStream(metaResponse.lastAction().state()) + ": "
                            + expectedDays + " "
                            + "(GMT " + metaResponse.lastAction().timezoneOffset() + ")"
                    );
            softly.assertThat(dashboardPage.getThisWeekDates())
                    .isEqualTo(formatDateWithoutSuffix(metaResponse.currentWeek().startDate().date())
                    + " - "
                    + metaResponse.currentWeek().endDate().label()
                    );
            softly.assertThat(dashboardPage.getThisWeekTime())
                    .isEqualTo(metaResponse.currentWeek().totalTime().hours() + "h "
                    + metaResponse.currentWeek().totalTime().minutes() + "m "
                    );

        });

        log.info("Finishing Time at Work widget validation");
    }

    @Test (testName = "Quick Launch widget validation", groups = {"smoke", "regression", "ext"})
    public void testQuickLaunchWidget() {
        log.info("Starting Quick Launch widget validation");
        DashboardPage dashboardPage = new DashboardPage();
        ApiResponse<QuickLaunchResponse, Meta> apiResponse = SendQuickLaunchRequest.sendQuickLaunchRequest();
        log.info(apiResponse.toString());

        if (dashboardPage.getQuickLaunchState()) {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(dashboardPage.getQuickLaunchNoContentImg())
                        .isEqualTo("No Content");
                softly.assertThat(dashboardPage.getQuickLaunchNoContentText())
                        .isEqualTo("Not Available");
                softly.assertThat(ObjectToMapConverter.getTrueFieldNames(apiResponse.data().get(0)).isEmpty()).isTrue();
            });
            log.info("No Quick Launch Icons");
        } else {

            List<String> quickLaunchButtonsTitles = dashboardPage.getAllQuickLaunchButtonsTitles();
            log.info(quickLaunchButtonsTitles.toString());

            //ApiResponse<QuickLaunchResponse, Meta> apiResponse = SendQuickLaunchRequest.sendQuickLaunchRequest();
            //log.info(apiResponse.toString());

            List<String> trueApiResponseFields = ObjectToMapConverter.getTrueFieldNames(apiResponse.data().get(0));


            // Buttons Number Validation
            Assertions.assertThat(quickLaunchButtonsTitles.size())
                    .isEqualTo(trueApiResponseFields.size());

            Map<String, String> expectedQuickLaunchButtons = Stream.of(QuickLaunchEnum.values())
                    .collect(Collectors
                            .toMap(
                                    QuickLaunchEnum::getResponseName,
                                    QuickLaunchEnum::getNameToSee));

            // Button Titles Validation
            Assertions.assertThat(quickLaunchButtonsTitles
                            .stream()
                            .sorted()
                            .collect(Collectors.toList()))
                    .isEqualTo(expectedQuickLaunchButtons
                            .entrySet()
                            .stream()
                            .filter(entry -> trueApiResponseFields.contains(entry.getKey()))
                            .map(Map.Entry::getValue)
                            .sorted()
                            .collect(Collectors.toList())
                    );
            log.info("Finishing Quick Launch widget validation");
        }
    }

    @Test(testName = "Buzz Last Post Test", groups = {"smoke", "regression", "ext"})
    public void testBuzzLastPost() {
        log.info("Starting Buzz Last Post Test");

        List<BuzzLatestPosts> uiMessages = new DashboardPage()
                .getUIBuzzLatestPostMessages();

        log.info(uiMessages.toString());

        ApiResponse<BuzzLatestPost, Meta> apiResponse = SendBuzzLatastPostRequest.sendBuzzLastPostRequest();
        log.info(apiResponse.toString());

        List<BuzzLatestPosts> apiMessages = apiResponse.data().stream()
                .map(data -> BuzzLatestPosts.ofNormalized(
                        data.employee().getFirstName()
                                + " " + data.employee().getMiddleName()
                                + " " + data.employee().getLastName(),
                        convertDateFormatSafe(data.createdDate())
                                + " " + convertUtcToLocal(data.createdTime()),
                        data.text()
                ))
                .collect(Collectors.toList());

        log.info(apiMessages.toString());

        Assertions.assertThat(uiMessages)
                .isEqualTo(apiMessages);

        log.info("Finishing Buzz Last Post Test");
    }

    @Test(testName = "Employee Distribution By Sub Unit Test", groups = {"smoke", "regression", "ext"})
    public void testEmployeeDistributionBySubUnit() {
        log.info("Starting Employee Distribution By Sub Unit Test");

        ApiResponse<EmpBySubUnit, EmpBySubUnitMeta> apiResponse =  sendEmployeeBySubUnitRequest();

        List<String> tooltips = new DashboardPage()
                .getEmployeeDistributionBySubUnitChartTooltips();

        List<PieChartTooltip> chartTooltips = StringFormatUtil.parseTooltipsFromList(tooltips);

        int total = apiResponse.data().stream()
                .mapToInt(EmpBySubUnit::count)
                .sum() + apiResponse.meta().get(0).unassignedEmployeeCount();

        List<PieChartTooltip> apiTooltips = apiResponse.data().stream()
                .map(data -> new PieChartTooltip(
                        data.subunit().name(),
                        data.count(),
                        BigDecimal.valueOf((double) data.count() / total * 100.00)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ))
                .collect(Collectors.toList());

        apiTooltips.add(new PieChartTooltip("Unassigned",
                apiResponse.meta().get(0).unassignedEmployeeCount(),
                BigDecimal.valueOf((double) apiResponse.meta().get(0).unassignedEmployeeCount() / total * 100.00)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()));
        chartTooltips.sort(Comparator.comparing(PieChartTooltip::title));
        apiTooltips.sort(Comparator.comparing(PieChartTooltip::title));

        List<String> legend = new DashboardPage()
                .getLegendOfEmployeeDistributionBySubUnit();

        legend.sort(null);

        log.info("initial tooltips " + tooltips);
        log.info("sorted chart tooltips " + chartTooltips);
        log.info("sorted api tooltips " + apiTooltips);
        log.info("sorted chart legend " + legend);

       SoftAssertions.assertSoftly(softly -> {
           softly.assertThat(chartTooltips)
                                   .isEqualTo(apiTooltips);
           softly.assertThat(legend)
                   .isEqualTo(apiTooltips
                           .stream()
                           .map(PieChartTooltip::title)
                           .collect(Collectors.toList()));

                       }
                       );
       log.info("Finishing Employee Distribution By Sub Unit Test");
    }

    @Test(testName = "Employee Distribution By Location Test", groups = {"smoke", "regression", "ext"})
    public void testEmployeeDistributionByLocation() {
        log.info("Starting Employee Distribution By Location Test");

        ApiResponse<EmpByLocation, EmpByLocationMeta> apiResponse =  sendEmployeeByLocationRequest();

        List<String> tooltips = new DashboardPage()
                .getEmployeeDistributionByLocationChartTooltips();

        List<PieChartTooltip> chartTooltips = StringFormatUtil.parseTooltipsFromList(tooltips);

        int total = apiResponse.data().stream()
                .mapToInt(EmpByLocation::count)
                .sum() + apiResponse.meta().get(0).unassignedEmployeeCount();

        List<PieChartTooltip> apiTooltips = apiResponse.data().stream()
                .map(data -> new PieChartTooltip(
                        data.location().name(),
                        data.count(),
                        BigDecimal.valueOf((double) data.count() / total * 100.00)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ))
                .collect(Collectors.toList());

        apiTooltips.add(new PieChartTooltip("Unassigned",
                apiResponse.meta().get(0).unassignedEmployeeCount(),
                BigDecimal.valueOf((double) apiResponse.meta().get(0).unassignedEmployeeCount() / total * 100.00)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue()));
        chartTooltips.sort(Comparator.comparing(PieChartTooltip::title));
        apiTooltips.sort(Comparator.comparing(PieChartTooltip::title));

        List<String> legend = new DashboardPage()
                .getLegendOfEmployeeDistributionByLocation();

        legend.sort(null);

        log.info("initial tooltips " + tooltips);
        log.info("sorted chart tooltips " + chartTooltips);
        log.info("sorted api tooltips " + apiTooltips);
        log.info("sorted chart legend " + legend);

        SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(chartTooltips)
                            .isEqualTo(apiTooltips);
                    softly.assertThat(legend)
                            .isEqualTo(apiTooltips
                                    .stream()
                                    .map(PieChartTooltip::title)
                                    .collect(Collectors.toList()));

                }
        );
        log.info("Finishing Employee Distribution By Location Test");
    }

    @Test(testName = "Employee On Leave Test", groups = {"smoke", "regression", "ext"})
    public void testEmployeeOnLeaveToday() {
        log.info("Starting Employee On Leave Test");
        DashboardPage dashboardPage = new DashboardPage()
                .waitForDashboardGrid();
        ApiResponse<EmpOnLeaveMain, EmpOnLeaveMeta> apiResponse = SendEmployeeOnLeaveRequest.sendEmpOnLeaveRequest();

        if (dashboardPage.getEmployeesOnLeaveTodayState()) {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(dashboardPage.getEmployeeOnLeaveNoContentImg())
                        .isEqualTo("No Content");
                softly.assertThat(dashboardPage.getEmployeeOnLeaveNoContentTxt())
                        .isEqualTo("No Employees are on Leave Today");
                softly.assertThat(apiResponse.data().isEmpty()).isTrue();
                softly.assertThat(apiResponse.meta().get(0).total())
                        .isEqualTo(0);
            });
            log.info("No Employees on leave today");
        } else {
            List<EmpOnLeave> uiEmpOnLeave = dashboardPage.getEmployeeOnLeaveList();
            // ApiResponse<EmpOnLeaveMain, EmpOnLeaveMeta> apiResponse = SendEmployeeOnLeaveRequest.sendEmpOnLeaveRequest();

            List<EmpOnLeave> apiEmpOnLeave = apiResponse.data().stream()
                            .map(data -> new EmpOnLeave(
                                    data.employee().getFirstName() + " " + data.employee().getLastName(),
                                    data.employee().getEmployeeId(),
                                    data.leaveType().name() + " ("
                                    + data.startTime() + " - "
                                    + data.endTime() + ")"
                            ))
                                    .collect(Collectors.toList());
            uiEmpOnLeave.sort(Comparator.comparing(EmpOnLeave::name));
            apiEmpOnLeave.sort(Comparator.comparing(EmpOnLeave::name));

            log.info("EmpList UI " + uiEmpOnLeave);
            log.info("EmpList Api " + apiEmpOnLeave);

            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(uiEmpOnLeave)
                                        .isEqualTo(apiEmpOnLeave);
                softly.assertThat(uiEmpOnLeave.size())
                        .isEqualTo(apiResponse.meta().get(0).total());
            });

        }

        log.info("Finishing Employee On Leave Test");
    }

}
