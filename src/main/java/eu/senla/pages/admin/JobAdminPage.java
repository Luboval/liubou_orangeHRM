package eu.senla.pages.admin;

import eu.senla.elements.ApiPoints;
import eu.senla.elements.Title;
import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Driver;
import eu.senla.management.general.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class JobAdminPage {
    private String name;
    private By jobTitleLableLocator = By.cssSelector("div[class='orangehrm-paper-container']>div>h6[class~='orangehrm-main-title']");
    private By addTitleButtonLocator = By.cssSelector("button[class~='oxd-button--secondary']");
    private By formAddTitleLabelLocator = By.cssSelector("h6[class~='orangehrm-main-title']");
    private By jobTitleInputLocator = By.xpath("//form//*//input[@class='oxd-input oxd-input--active']");
    private By jobDiscriptionInputLocator = By.xpath("//form/div[2]//*//textarea");
    private By jobNoteInputLocator = By.xpath("//form/div[4]//*//textarea");
    private By saveButtonLocator = By.xpath("//form//button[@type='submit']");
    //private By jobsTitlesSelectLocator = By.xpath("//div[@class='oxd-table-body']//*//div[text() ='"+name+"']");
    private By deleteModalLocator = By.cssSelector("[class~='orangehrm-dialog-popup']");
    private By deletebutonLocator = By.cssSelector("[class~='oxd-button--label-danger']");
    private By jobTitleListLocator = By.xpath("//*[@class='oxd-table-card']//div//div[2]//div");

    //private By jobTitleListLocator = By.xpath("//div[1][@class='oxd-table-card']//div[1]//div[2]//div");
    private By jobTableLocator = By.cssSelector("[class = 'oxd-table-body']");



    public void switchDirectlyToJobTitlesPage() {
        BaseActions.visit(ReadPropertyFile.getProperty("BASEURL") + ApiPoints.JOBTITLESPAGE);
    }

    public void clickAddTitleButton() {
        BaseActions.clickButton(addTitleButtonLocator);
    }

    public void waitForFormAddTitleLabel() {
        Wait.waitFIsDisplayed(formAddTitleLabelLocator);
    }

    public void waitForJobTitleLabel() {
        Wait.waitFIsDisplayed(jobTitleLableLocator);
    }
    public void waitForJobTable() {
        Wait.waitFPresence(jobTableLocator);
    }

    public String getJobsCreated(String createdName) {
        By jobsTitlesSelectLocator = By.xpath("//div[@class='oxd-table-body']//*//div[text() ='" + createdName + "']");
        return Wait.waitFIsDisplayed(jobsTitlesSelectLocator).getText();

    }

    public List<String> getAllTitles() {

        waitForJobTable();

        List<WebElement> webElement = Driver.driverRun().findElements(jobTitleListLocator);
        return webElement.stream()
                .map((WebElement::getText))
                .collect(Collectors.toList());
    }

    public JobAdminPage deleteTitle(Title title) {
        By jobsTitlesDeleteLocator = By.xpath("//div[text() ='" + title.getJobTitle() + "']/parent::div/following-sibling::div[2]//button[1]");
        BaseActions.clickButton(jobsTitlesDeleteLocator);
        Wait.waitFPresence(deleteModalLocator);
        BaseActions.clickButton(deletebutonLocator);
        waitForJobTitleLabel();
        return new JobAdminPage();
    }

    public JobAdminPage createTitle(Title title) {
        switchDirectlyToJobTitlesPage();
        clickAddTitleButton();
        waitForFormAddTitleLabel();
        BaseActions.fillInput(jobTitleInputLocator, title.getJobTitle());
        BaseActions.fillInput(jobDiscriptionInputLocator, title.getJobDescription());
        BaseActions.fillInput(jobNoteInputLocator, title.getJobTitle());
        BaseActions.submitButton(saveButtonLocator);
        waitForJobTitleLabel();

        return new JobAdminPage();
    }
}
