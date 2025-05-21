package eu.senla.pages;

import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Wait;
import org.openqa.selenium.By;

public class AdminUserManagementPage {
    private By userNameFieldLocator = By.xpath("//form//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input");
    private By userManagementPageLocator = By.xpath("//aside//ul//li[1]");
    private By searchButtonLocator = By.xpath("//form//button[2]");
    private By addButtonLocator = By.xpath("//div[@class='orangehrm-header-container']//button");
    private By dropDownUserRoleLocator = By.xpath("//form//div[@class='oxd-select-text--after'][1]");
    private By adminItemLocator = By.xpath("//*[@role='listbox']//*[text()='Admin']");
    private By userManagementLabelLocator = By.xpath("//header//span//h6[2]");


    public boolean findPageElements() {
        boolean b = Wait.waitFIsDisplayed(userNameFieldLocator).isDisplayed()
               && Wait.waitFIsDisplayed(searchButtonLocator).isDisplayed()
               && Wait.waitFIsDisplayed(addButtonLocator).isDisplayed()
               && BaseActions.displayAfterClick(dropDownUserRoleLocator, adminItemLocator).isDisplayed();
        return b;
   }

    public  AdminUserManagementPage switchToUserManagementPage() {

        BaseActions.clickButton(userManagementPageLocator);
        Wait.waitFPresence(userManagementLabelLocator);
        return new AdminUserManagementPage();

    }

}
