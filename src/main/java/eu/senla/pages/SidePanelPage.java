package eu.senla.pages;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import eu.senla.pages.admin.UserManagementAdminPage;
import eu.senla.pages.login.LoginPage;
import eu.senla.pages.pim.PimPage;
import org.openqa.selenium.By;

public class SidePanelPage {

    private By adminPageLocator = By.xpath("//aside//ul//li[1]");
    private By pageLabelLocator = By.cssSelector("h6[class~='oxd-topbar-header-breadcrumb-module']");
    private By pimPageLocator = By.xpath("//aside//ul//li[2]");
    private By userDropDowmLocator = By.xpath("//header//*//p[@class='oxd-userdropdown-name']");
    private By logoutMenuItemLocator = By.xpath("//header//*//a[text() = 'Logout']");
    private By loginLableLocator = By.xpath("//h5[text() = 'Login']");


    public UserManagementAdminPage switchToAdminPage() {

        BaseActions.clickButton(adminPageLocator);
        Wait.waitFPresence(pageLabelLocator);
        return new UserManagementAdminPage();

    }

    public PimPage switchToPimPage() {

        BaseActions.clickButton(pimPageLocator);
        Wait.waitFPresence(pageLabelLocator);
        return new PimPage();

    }

    public LoginPage executeLogout() {
        BaseActions.clickElement(userDropDowmLocator);
        BaseActions.clickElement(logoutMenuItemLocator);
        Wait.waitFPresence(loginLableLocator);
        return new LoginPage();
    }

}
