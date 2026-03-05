package eu.senla.pages.dashboard;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Driver;
import eu.senla.management.common.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_TITLE;

public class DashboardPage {

   private By widgetTitleLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-header']//p");
   private By dashboardWidgetGridLocator = By.cssSelector("[class~='orangehrm-dashboard-grid']");
   private By myActionListItemsLocator = By.cssSelector(".orangehrm-todo-list-item p");
   private By quickLaunchButtonsLocator = By.cssSelector(".orangehrm-quick-launch-icon");
   private By timeAtWorkPunchedInStateLocator = By.cssSelector(".orangehrm-attendance-card-state");
   private By timeAtWorkPunchedInDetailsLocator = By.cssSelector(".orangehrm-attendance-card-details");
   private By timeAtWorkPuncedInTimeLocator = By.cssSelector(".orangehrm-attendance-card-bar .orangehrm-attendance-card-fulltime b");


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

   public Map<String, Integer> getAllMyActionsItems() {

      waitForDashboardGrid();
      List<String> webElements = BaseActions.getValueAll(myActionListItemsLocator);
      ArrayList<String> items = new ArrayList<>(webElements);

       return webElements.stream()
              .map(item -> {
                 Pattern pattern = Pattern.compile("\\((\\d+)\\)\\s*(.+)");
                 Matcher matcher = pattern.matcher(item);
                 if (matcher.find()) {
                    return new AbstractMap.SimpleEntry<>(
                            matcher.group(2),
                            Integer.parseInt(matcher.group(1))
                    );
                 }
                 return null;
              })
              .filter(Objects::nonNull)
              .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue
              ));

   }

   public  List<String> getAllQuickLaunchButtonsTitles() {
       return BaseActions.getValueAll(quickLaunchButtonsLocator, ATTRIBUTE_TITLE);
   }
}
