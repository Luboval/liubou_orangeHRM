package eu.senla.tests.dashboard;

import eu.senla.elements.DashboardElementsTitles;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class DashboardTest extends BaseTest {

    public DashboardTest() {
        super(new ApiLoginStrategy());
    }

    @Test (testName = "Dashboard validation", groups = {"regression", "pages"})
    public void testDashboard() {
        List<String> widgetTitles = new DashboardPage()
                .getAllWidgetTitles();

        Assert.assertEquals(new HashSet<>(widgetTitles), new HashSet<>(DashboardElementsTitles.getDashboardWidgetTitles()), "Widgets do not match");


    }



}
