package eu.senla.tests.dashboard;

import eu.senla.api.actions.dashboardwidgets.quicklaunch.SendQuickLaunchRequest;
import eu.senla.api.actions.dashboardwidgets.timeatwork.SendTimeAtWorkRequest;
import eu.senla.api.apielements.dashboard.myactions.MyActionsResponse;
import eu.senla.api.actions.dashboardwidgets.myactions.SendMyActionsRequest;
import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.quicklaunch.QuickLaunchResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkDataResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkMetaResponse;
import eu.senla.api.utils.ObjectToMapConverter;
import eu.senla.elements.dashboard.DashboardElementsTitles;
import eu.senla.elements.dashboard.QuickLaunchEnum;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //Дописать с UI тестом

        ApiResponse<TimeAtWorkDataResponse, TimeAtWorkMetaResponse> apiResponse = SendTimeAtWorkRequest.sendTimeAtWorkRequest();
        log.info(apiResponse.toString());



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
