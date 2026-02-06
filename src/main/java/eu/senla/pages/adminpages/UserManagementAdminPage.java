package eu.senla.pages.adminpages;

import eu.senla.elements.UserManagementTable;
import eu.senla.management.common.BaseActions;
import eu.senla.management.common.Wait;
import eu.senla.management.utils.table.Table;
import eu.senla.pages.SidePanelPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;


import static eu.senla.management.common.BaseActions.clickAfterClick;
import static eu.senla.management.common.BaseActions.clickButton;
import static eu.senla.management.common.BaseActions.getValue;
import static eu.senla.management.common.Constants.ATTRIBUTE_TEXTCONTENT;

@Slf4j
public class UserManagementAdminPage {
    private By userNameFieldLocator = By.xpath("//form//div[@class='oxd-grid-item oxd-grid-item--gutters'][1]//input");
    private By userManagementPageLocator = By.xpath("//aside//ul//li[1]");
    private By searchButtonLocator = By.xpath("//form//button[2]");
    private By addButtonLocator = By.xpath("//div[@class='orangehrm-header-container']//button");
    private By dropDownUserRoleLocator = By.xpath("//form//div[@class='oxd-select-text--after'][1]");
    private By adminItemLocator = By.xpath("//*[@role='listbox']//*[text()='Admin']");
    private By userManagementLabelLocator = By.xpath("//header//span//h6[2]");
    private By statusDropDownLocator = By.xpath("//form//div[@class='oxd-grid-item oxd-grid-item--gutters'][4]//div[@class='oxd-select-text--after']");
    private By statusItemLocator = By.xpath("//*[@role='listbox']//*[text()='Enabled']");
    private By recordFoundLocator = By.cssSelector(" div[class ='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']");


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
     public void selectStatus() {
        clickAfterClick(statusDropDownLocator, statusItemLocator);
     }
     public void selectUserRole() {
        clickAfterClick(dropDownUserRoleLocator, adminItemLocator);
     }

     public UserManagementAdminPage executeSearchByUserRoleAndStatus() {
        selectUserRole();
        selectStatus();
        clickButton(searchButtonLocator);
        return new UserManagementAdminPage();
     }

     public int getRecordFound() {
         String[] value = getValue(recordFoundLocator, ATTRIBUTE_TEXTCONTENT).split("\\)");

        return Integer.parseInt(value[0].trim().substring(1));

     }

     public Table<UserManagementTable> userManagementAdminPageTable() {
        return   new Table<>(
                Wait.waitFPresence(By.cssSelector(".oxd-table")),
                cells -> new UserManagementTable(
                        cells.get(0).getText(),
                        cells.get(1).getText(),
                        cells.get(2).getText(),
                        cells.get(3).getText(),
                        cells.get(4).getText(),
                        cells.get(5).getText()
                )
        );

     }
}
