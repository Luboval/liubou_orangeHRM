package eu.senla.tests.dashboard;

import eu.senla.api.actions.dashboardwidgets.buzz.SendBuzzLatastPostRequest;
import eu.senla.api.actions.dashboardwidgets.myactions.SendMyActionsRequest;
import eu.senla.api.actions.dashboardwidgets.quicklaunch.SendQuickLaunchRequest;
import eu.senla.api.actions.dashboardwidgets.timeatwork.SendTimeAtWorkRequest;
import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.buzzlatestposts.BuzzLatestPost;
import eu.senla.api.apielements.dashboard.myactions.MyActionsResponse;
import eu.senla.api.apielements.dashboard.quicklaunch.QuickLaunchResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkDataResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkMetaResponse;
import eu.senla.api.utils.DateTimeUtil;
import eu.senla.api.utils.ObjectToMapConverter;
import eu.senla.elements.dashboard.DashboardElementsTitles;
import eu.senla.elements.dashboard.QuickLaunchEnum;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eu.senla.api.utils.DateTimeUtil.formatDateWithoutSuffix;
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
        // Дописать с UI тестом


        DashboardPage dashboardPage = new DashboardPage()
                .waitForDashboardGrid();

        ApiResponse<TimeAtWorkDataResponse, TimeAtWorkMetaResponse> apiResponse = SendTimeAtWorkRequest.sendTimeAtWorkRequest();
        log.info(apiResponse.toString());

        TimeAtWorkMetaResponse metaResponse = apiResponse.meta().get(0);

        String expectedDays = (metaResponse.lastAction().userDate().equals(LocalDate.now().toString()))
                ? "Today at " + DateTimeUtil.formatTimeTo12Hour(metaResponse.lastAction().userTime())
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

        List<String> quickLaunchButtonsTitles = new DashboardPage()
                .getAllQuickLaunchButtonsTitles();
        log.info(quickLaunchButtonsTitles.toString());

        ApiResponse<QuickLaunchResponse, Meta> apiResponse = SendQuickLaunchRequest.sendQuickLaunchRequest();
        log.info(apiResponse.toString());

        List<String> trueApiResponseFields = ObjectToMapConverter.getTrueFieldNames(apiResponse.data().get(0));


        // Buttons Number Validation
        Assertions.assertThat(quickLaunchButtonsTitles
                                .stream()
                                .count())
                        .isEqualTo(trueApiResponseFields
                                .stream()
                                .count()
                        );

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

    @Test(testName = "Buzz Last Post Test", groups = {"smoke", "regression", "ext"})
    public void testBuzzLastPost() {
        ApiResponse<BuzzLatestPost, Meta> apiResponse = SendBuzzLatastPostRequest.sendBuzzLastPostRequest();
        log.info(apiResponse.toString());
    }




}
