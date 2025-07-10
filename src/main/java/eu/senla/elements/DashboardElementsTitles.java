package eu.senla.elements;

import java.util.ArrayList;
import java.util.List;

public class DashboardElementsTitles {
    public static List<String> dashboardWidgetTitles = new ArrayList<>();

    public static List<String> getDashboardWidgetTitles() {
        dashboardWidgetTitles.add("Time at Work");
        dashboardWidgetTitles.add("My Actions");
        dashboardWidgetTitles.add("Quick Launch");
        dashboardWidgetTitles.add("Buzz Latest Posts");
        dashboardWidgetTitles.add("Employees on Leave Today");
        dashboardWidgetTitles.add("Employee Distribution by Sub Unit");
        dashboardWidgetTitles.add("Employee Distribution by Location");
        return dashboardWidgetTitles;
    }
}
