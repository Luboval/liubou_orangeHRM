package eu.senla.tests.dashboard;

import eu.senla.api.actions.dashboardwidgets.myactions.MyActionsResponse;
import eu.senla.api.actions.dashboardwidgets.myactions.SendMyActionsRequest;
import eu.senla.api.apielements.ApiResponse;
import eu.senla.elements.DashboardElementsTitles;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

        ApiResponse<MyActionsResponse> myActionsApiResponse = SendMyActionsRequest.sendMyActionRequest();

        log.info(myActionsApiResponse.toString());


        log.info("Finish MyAction widget validation");

    }

}
