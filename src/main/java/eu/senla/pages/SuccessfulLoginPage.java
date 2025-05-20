package eu.senla.pages;

import eu.senla.management.general.BaseActions;

public class SuccessfulLoginPage {
//    private By dashboardLabelLocator = By.xpath("//span//h6[text()='Dashboard']");


//    public SuccessfulLoginPage(WebDriver driver) {
//        super(driver);
//    }

//    public  void waitForDashboardLabelPresence() {
//        Wait.waitFPresence(dashboardLabelLocator);
//    }
    public String getPageUrl() {
        return BaseActions.getCurrentUrl();
    }

}
