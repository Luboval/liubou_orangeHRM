package eu.senla.pages.login;

import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Wait;
import org.openqa.selenium.By;

public class SuccessfulLoginPage {
    private By dashboardLabelLocator = By.xpath("//span//h6[text()='Dashboard']");

    public String getSuccessfulLoginPageUrl() {
        Wait.waitFPresence(dashboardLabelLocator);
        return BaseActions.getCurrentUrl();
    }
}
