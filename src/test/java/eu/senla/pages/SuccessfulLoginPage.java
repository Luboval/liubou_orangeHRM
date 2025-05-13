package eu.senla.pages;

import eu.senla.BaseClass;
import org.openqa.selenium.By;

public class SuccessfulLoginPage extends BaseClass {
    private static By dashboardLabelLocator = By.xpath("//span//h6[text()='Dashboard']");


    public static void waitForDashboardLabelPresence(){
        WaitsPage.waitFluentIsDisplayed(dashboardLabelLocator);
    }

}
