package eu.senla;

import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class AdminUserManagementPage extends Basic{
    private By userNameFieldLocator = RelativeLocator.with(By.tagName("input")).below(By.xpath("//*[@class='oxd-label']"));
    // private By userManagementPageLocator = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
    // private By userManagementPageLocator = By.cssSelector("a[href$='viewAdminModule']");
   // private By userManagementPageLocator = By.xpath(".//span[text()='Admin']");




    public void findElement() {
        driver.findElement(userNameFieldLocator).sendKeys("smth");
    }

    public  void switchToUserManagementPage(){

        System.out.println("Searching...");
        driver.findElement(userManagementPageLocator).click();

    }

}
