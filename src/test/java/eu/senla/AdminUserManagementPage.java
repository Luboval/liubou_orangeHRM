package eu.senla;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class AdminUserManagementPage extends Basic{
    private By userNameFieldLocator = RelativeLocator.with(By.tagName("input")).below(By.xpath("//*[@class='oxd-label']"));
    private By userManagementPageLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");
    private By expandLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[3]/button/i");
    private By searchButtonLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private By addButtonLocator = By.xpath("//button[text()=\" Add \"]");
    private By dropDownUserRoleLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div");
    private By adminItemLocator = By.xpath("//*[@role='listbox']//*[text()='Admin']");

    public AdminUserManagementPage(WebDriver driver){
        super(driver);
    }



    public void findElement() {
        driver.findElement(userNameFieldLocator);
    }

    public  void switchToUserManagementPage(){

        driver.findElement(userManagementPageLocator).click();

    }

    public void expandArea(){
        driver.findElement(expandLocator).click();
    }

    public void findSearchButton(){
        driver.findElement(searchButtonLocator);
    }

    public void findAddButton(){
        driver.findElement(addButtonLocator);
    }

    public void findDropDownUserRole() {
        driver.findElement(dropDownUserRoleLocator).click();
    }

    public void findAdminItemLocator(){
       driver.findElement(adminItemLocator).click();

    }

}
