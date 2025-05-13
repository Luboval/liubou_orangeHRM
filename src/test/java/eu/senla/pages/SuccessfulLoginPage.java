package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulLoginPage extends BasePage {
    private By dashboardLabelLocator = By.xpath("//span//h6[text()='Dashboard']");

    public SuccessfulLoginPage(WebDriver driver) {
        super(driver);
    }

    public  void waitForDashboardLabelPresence(){
        WaitsPage wait = new WaitsPage(driver);
        wait.waitFluentIsDisplayed(dashboardLabelLocator);
    }

}
