package eu.senla.pages.login;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import org.openqa.selenium.By;

public class SuccessfulLoginPage {
    private By dashboardLabelLocator = By.xpath("//span//h6[text()='Dashboard']");

    public String getSuccessfulLoginPageUrl() {
        Wait.waitFPresence(dashboardLabelLocator);
        return BaseActions.getCurrentUrl();
    }
}
