package eu.senla.pages;

import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Wait;
import eu.senla.pages.admin.UserManagementAdminPage;
import org.openqa.selenium.By;

public class SidePanelPage {

    private By adminPageLocator = By.xpath("//aside//ul//li[1]");
    private By pageLabelLocator = By.cssSelector("h6[class~='oxd-topbar-header-breadcrumb-module']");
    private By pimPageLocator = By.xpath("//aside//ul//li[2]");



    public UserManagementAdminPage switchToAdminPage() {

        BaseActions.clickButton(adminPageLocator);
        Wait.waitFPresence(pageLabelLocator);
        return new UserManagementAdminPage();

    }

    public  PimPage switchToPimPage() {

        BaseActions.clickButton(pimPageLocator);
        Wait.waitFPresence(pageLabelLocator);
        return new PimPage();

    }



}
