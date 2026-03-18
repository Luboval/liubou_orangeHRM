package eu.senla.pages.dashboard;

import eu.senla.elements.dashboard.BuzzLatestPosts;
import eu.senla.elements.dashboard.EmpOnLeave;
import eu.senla.management.common.Driver;
import eu.senla.management.common.Wait;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static eu.senla.management.common.BaseActions.getValue;
import static eu.senla.management.common.BaseActions.getValueAll;
import static eu.senla.management.common.BaseActions.moveToPoint;
import static eu.senla.management.common.Wait.waitFNotPresenceBool;
import static eu.senla.management.common.Wait.waitFPresence;
import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_ALT;
import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_TEXTCONTENT;
import static eu.senla.management.common.constants.AttributesForUITests.ATTRIBUTE_TITLE;

@Slf4j
public class DashboardPage {

   private final By widgetTitleLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-header']//p");
   private final By dashboardWidgetGridLocator = By.cssSelector("[class~='orangehrm-dashboard-grid']");
   private final By myActionListItemsLocator = By.cssSelector(".orangehrm-todo-list-item p");
   private final By quickLaunchButtonsLocator = By.cssSelector(".orangehrm-quick-launch-icon");
  // private final By timeAtWorkPunchedInStateLocator = By.cssSelector(".orangehrm-attendance-card-state");
  // private final By timeAtWorkPunchedInDetailsLocator = By.cssSelector(".orangehrm-attendance-card-details");
   private final By timeAtWorkPuncedInTimeLocator = By.cssSelector(".orangehrm-attendance-card-bar .orangehrm-attendance-card-fulltime");
   private final By timeAtWorkPunchedOutLocator = By.cssSelector(".orangehrm-attendance-card-details");
   private final By thisWeekDatesLocator = By.cssSelector(".orangehrm-attendance-card-summary-week p:nth-child(2)");
   private final By thisWeekTimeLocator = By.cssSelector(".orangehrm-attendance-card-summary-total p");
   private final By buzzLatestPostsLocator = By.cssSelector(".orangehrm-buzz-widget-card");
   private final By buzzLatestPostsImageLocator = By.cssSelector(".orangehrm-buzz-widget-picture");
   private final By buzzLatestPostsAuthorLocator = By.cssSelector(".orangehrm-buzz-widget-header-emp");
   private final By buzzLatestPostsTimeLocator = By.cssSelector(".orangehrm-buzz-widget-header-time");
   private final By buzzLatestPostsTextLocator = By.cssSelector(".orangehrm-buzz-widget-body");
   private final By employeeDistributionChartTooltipLocator = By.id("oxd-pie-chart-tooltip");
   private final By employeeDistributionBySubUnitChartLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-name'][.//p[text()='Employee Distribution by Sub Unit']]/../..//canvas");
   private final By employeeDistributionBySubUnitChartLegendLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-name'][.//p[text()='Employee Distribution by Sub Unit']]/../..//*//li/span[@class='oxd-text oxd-text--span']");
   private final By employeeDistributionByLocationChartLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-name'][.//p[text()='Employee Distribution by Location']]/../..//canvas");
   private final By employeeDistributionByLocationChartLegendLocator = By.xpath("//div[@class='orangehrm-dashboard-widget-name'][.//p[text()='Employee Distribution by Location']]/../..//*//li/span[@class='oxd-text oxd-text--span']");
   private final By employeesOnLeaveNoContentLocator = By.cssSelector(".emp-leave-chart .orangehrm-dashboard-widget-body-nocontent");
   private final By employeeOnLeaveNoContentImgLocator = By.cssSelector(".emp-leave-chart .orangehrm-dashboard-widget-body-nocontent img");
   private final By employeeOnLeaveNoContentTxtLocator = By.cssSelector(".emp-leave-chart .orangehrm-dashboard-widget-body-nocontent p");
   private final By empliyeeOnLeaveCardLocator = By.cssSelector(".orangehrm-leave-card");
   private final By empliyeeOnLeaveCardEmpNameLocator = By.cssSelector(".orangehrm-leave-card-emp-name");
   private final By empliyeeOnLeaveCardLeaveDetailsLocator = By.cssSelector(".orangehrm-leave-card-leave-details");
   private final By empliyeeOnLeaveCardEmpIdLocator = By.cssSelector(".orangehrm-leave-card-emp-id");
   private final By empOnLeaveSpinnerLocator = By.cssSelector(".emp-leave-chart .oxd-loading-spinner");
   private final By quickLaunchSpinnerLocator = By.cssSelector("[class='oxd-grid-item oxd-grid-item--gutters orangehrm-dashboard-widget']:nth-child(2) .oxd-loading-spinner");
   private final By quickLaunchNoContentLocator = By.cssSelector(".orangehrm-quick-launch .orangehrm-dashboard-widget-body-nocontent");
   private final By quickLaunchNoContentImgLocator = By.cssSelector(".orangehrm-quick-launch .orangehrm-dashboard-widget-body-nocontent img");
   private final By quickLaunchNoContentTxtLocator = By.cssSelector(".orangehrm-quick-launch .orangehrm-dashboard-widget-body-nocontent p");



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
      List<String> webElements = getValueAll(myActionListItemsLocator);

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
       return getValueAll(quickLaunchButtonsLocator, ATTRIBUTE_TITLE);
   }

   public boolean getQuickLaunchState() {
        return getWidgetState(quickLaunchSpinnerLocator, quickLaunchNoContentLocator);
   }

   public String getQuickLaunchNoContentImg() {
        return getValue(quickLaunchNoContentImgLocator, ATTRIBUTE_ALT);
   }

   public String getQuickLaunchNoContentText() {
       return getValue(quickLaunchNoContentTxtLocator, ATTRIBUTE_TEXTCONTENT);
   }

   public String getTimeAtWorkPuncedInTime() {
       return getValue(timeAtWorkPuncedInTimeLocator, ATTRIBUTE_TEXTCONTENT);
   }

   public String getTimeAtWorkPurchedOut() {
       return getValue(timeAtWorkPunchedOutLocator, ATTRIBUTE_TEXTCONTENT);
   }

   public String getThisWeekDates() {
       return getValue(thisWeekDatesLocator, ATTRIBUTE_TEXTCONTENT);
   }

   public String getThisWeekTime() {
       return getValue(thisWeekTimeLocator, ATTRIBUTE_TEXTCONTENT);
   }

   public List<BuzzLatestPosts> getUIBuzzLatestPostMessages() {
       List<BuzzLatestPosts> uiMessages = new ArrayList<>();
       List<WebElement> buzzLatestPostsList = Wait.waitFPresenceAll(buzzLatestPostsLocator);

       for (WebElement message : buzzLatestPostsList) {
           String userName = message.findElement(buzzLatestPostsAuthorLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);
           String time = message.findElement(buzzLatestPostsTimeLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);
           //String text = message.findElement(buzzLatestPostsTextLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);
            String text;
           try {
               // Пробуем найти текстовый элемент
               text = message.findElement(buzzLatestPostsTextLocator)
                       .getAttribute(ATTRIBUTE_TEXTCONTENT);
           } catch (NoSuchElementException e) {
               // Если текста нет, проверяем наличие изображения
               try {
                   message.findElement(buzzLatestPostsImageLocator);
                   text = null; // Это пост с изображением
               } catch (NoSuchElementException ex) {
                   text = ""; // Ни текста, ни изображения
               }
           }

           uiMessages.add(BuzzLatestPosts.ofNormalized(userName, time, text));

       }
       return uiMessages;
   }

   public List<String> getTooltipsForPieChart(By pieChartLocator) {
       Set<String> collectedTooltips = new HashSet<>();
       int steps = 360;

       WebElement canvas = waitFPresence(pieChartLocator);
       int centerX = canvas.getSize().getWidth() / 2;
       int centerY = canvas.getSize().getHeight() / 2;
       log.info("Coord x " + centerX + " Coord y " + centerY);

       // Идем к середине канваса и ждем его полной отрисовки
       moveToPoint(canvas);

       for (int i = 0; i <= steps; i++) {
           // Вычисляем угол в радианах
           double angle = 2 * Math.PI * i / steps;

           // Вычисляем координаты точки на окружности
           int xOffset = (int) Math.round(centerX * 0.8 * Math.cos(angle));
           int yOffset = (int) Math.round(centerY * 0.8 * Math.sin(angle));

           // log.info(i + " angle " + angle + " x " + xOffset + " y " + yOffset);

           moveToPoint(canvas, xOffset, yOffset);
           collectedTooltips.add(getValue(employeeDistributionChartTooltipLocator, ATTRIBUTE_TEXTCONTENT));
       }
       log.info(collectedTooltips.toString());
       return new ArrayList<>(collectedTooltips);
   }

   public List<String> getEmployeeDistributionBySubUnitChartTooltips() {

       return getTooltipsForPieChart(employeeDistributionBySubUnitChartLocator);
   }

   public List<String> getLegendOfEmployeeDistributionBySubUnit() {
       return getValueAll(employeeDistributionBySubUnitChartLegendLocator);
   }

   public List<String> getEmployeeDistributionByLocationChartTooltips() {
       return getTooltipsForPieChart(employeeDistributionByLocationChartLocator);
   }

   public List<String> getLegendOfEmployeeDistributionByLocation() {
       return getValueAll(employeeDistributionByLocationChartLegendLocator);
   }

   public boolean getWidgetState(By spinnerLocator, By noContentLocator) {
       waitFNotPresenceBool(spinnerLocator);

       return !Driver.driverRun().findElements(noContentLocator).isEmpty();

   }
   public boolean getEmployeesOnLeaveTodayState() {
       return getWidgetState(empOnLeaveSpinnerLocator, employeesOnLeaveNoContentLocator);

   }

   public String getEmployeeOnLeaveNoContentImg() {
       return getValue(employeeOnLeaveNoContentImgLocator, ATTRIBUTE_ALT);
   }

    public String getEmployeeOnLeaveNoContentTxt() {
        return getValue(employeeOnLeaveNoContentTxtLocator, ATTRIBUTE_TEXTCONTENT);
    }

    public List<EmpOnLeave> getEmployeeOnLeaveList() {
       List<EmpOnLeave> empOnLeaveList = new ArrayList<>();
       List<WebElement> webElementList = Wait.waitFPresenceAll(empliyeeOnLeaveCardLocator);

       for (WebElement webElement : webElementList) {
           String name = webElement.findElement(empliyeeOnLeaveCardEmpNameLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);
           String cardDetails = webElement.findElement(empliyeeOnLeaveCardLeaveDetailsLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);
           String empId = webElement.findElement(empliyeeOnLeaveCardEmpIdLocator).getAttribute(ATTRIBUTE_TEXTCONTENT);

           empOnLeaveList.add(new EmpOnLeave(name, empId, cardDetails));
       }
       return empOnLeaveList;
    }




}
