package eu.senla.pages.admin;

import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import eu.senla.pages.SidePanelPage;
import org.openqa.selenium.By;

public class UserManagementAdminPage {
    private By userNameFieldLocator = By.xpath("//form//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input");
    private By userManagementPageLocator = By.xpath("//aside//ul//li[1]");
    private By searchButtonLocator = By.xpath("//form//button[2]");
    private By addButtonLocator = By.xpath("//div[@class='orangehrm-header-container']//button");
    private By dropDownUserRoleLocator = By.xpath("//form//div[@class='oxd-select-text--after'][1]");
    private By adminItemLocator = By.xpath("//*[@role='listbox']//*[text()='Admin']");
    private By userManagementLabelLocator = By.xpath("//header//span//h6[2]");


    public boolean findPageElements() {
        return Wait.waitFIsDisplayed(userNameFieldLocator).isEnabled()
               && Wait.waitFIsDisplayed(searchButtonLocator).isEnabled()
               && Wait.waitFIsDisplayed(addButtonLocator).isEnabled()
               && BaseActions.displayAfterClick(dropDownUserRoleLocator, adminItemLocator).isEnabled();

   }

    public UserManagementAdminPage switchToUserManagementPage() {

//        BaseActions.clickButton(userManagementPageLocator);
//        Wait.waitFPresence(userManagementLabelLocator);
        return new SidePanelPage().switchToAdminPage();
      //  return new UserManagementAdminPage();

    }
}
