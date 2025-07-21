package eu.senla.tests.dashboard;

import eu.senla.elements.DashboardElementsTitles;
import eu.senla.pages.dashboard.DashboardPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class DashboardTest extends BaseTest {

    @Test (testName = "Dashboard validation", groups = {"regression", "ext"})
    public void testDashboard() {
        System.out.println("Start Dashboard validation");
        List<String> widgetTitles = new DashboardPage()
                .getAllWidgetTitles();

        Assert.assertEquals(new HashSet<>(widgetTitles), new HashSet<>(DashboardElementsTitles.getDashboardWidgetTitles()), "Widgets do not match");
        System.out.println("Finish Dashboard validation");
    }
}
