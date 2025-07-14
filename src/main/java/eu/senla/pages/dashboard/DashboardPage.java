package eu.senla.pages.dashboard;

import eu.senla.management.common.Driver;
import eu.senla.management.common.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardPage {

   private By widgetTitleLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-header']//p");
   private By dashboardWidgetGridLocator = By.cssSelector("[class~='orangehrm-dashboard-grid']");

   public DashboardPage waitForDashboardGrid() {
      Wait.waitFPresence(dashboardWidgetGridLocator);
      return new DashboardPage();
   }

   public List<String> getAllWidgetTitles() {

      waitForDashboardGrid();

      List<WebElement> webElement = Driver.driverRun().findElements(widgetTitleLocator);
      return webElement.stream()
              .map((WebElement::getText))
              .collect(Collectors.toList());
   }
}
