package eu.senla.pages.recruitment;

import eu.senla.elements.ApiPoints;
import eu.senla.elements.Candidate;
import eu.senla.management.dataactions.ReadPropertyFile;
import eu.senla.management.general.BaseActions;
import eu.senla.management.general.Wait;
import org.openqa.selenium.By;

public class RecruitmentCandidatesPage {
    //common
    private By addCandidateButtonLocator = By.cssSelector("[class= 'orangehrm-header-container']>button");

    //Add Candidate form
    private By addCandidateFormTitleLocator = By.cssSelector("[class= 'orangehrm-card-container']>h6");
    private By firstNameInputLocator = By.cssSelector("[class~= 'orangehrm-firstname']");
    private By middleNameInputLocator = By.cssSelector("[class~= 'orangehrm-middlename']");
    private By lastNameInputLocator = By.cssSelector("[class~= 'orangehrm-lastname']");
    private By emailInputLocator = By.xpath("//*[@class= 'oxd-form-row'][3]/div/div[1]/*//input");
    private By contactNumberLocator = By.xpath("//*[@class= 'oxd-form-row'][3]/div/div[2]/*//input");
    private By saveButtonLocator = By.cssSelector("button[type= 'submit']");
    private By candidateProfileFormLocator = By.xpath("//*[@class='orangehrm-background-container']//form");

    //Candidate Profile
    private By profileFirstNameLocator = By.cssSelector("[name='firstName']");

    public void switchDirectlyToRecruitmentCandidatesPage() {
        BaseActions.visit(ReadPropertyFile.getProperty("BASEURL") + ApiPoints.RECRUITMENTPAGE);
    }

    public void openAddCandidateForm() {
        switchDirectlyToRecruitmentCandidatesPage();
        BaseActions.clickButton(addCandidateButtonLocator);
        Wait.waitFPresence(addCandidateFormTitleLocator);

    }

    public RecruitmentCandidatesPage addCandidate(Candidate candidate) {
        openAddCandidateForm();
        BaseActions.fillInput(firstNameInputLocator, candidate.getFirstName());
        BaseActions.fillInput(middleNameInputLocator, candidate.getMiddleName());
        BaseActions.fillInput(lastNameInputLocator, candidate.getLastName());
        BaseActions.fillInput(emailInputLocator, candidate.getEmail());
        BaseActions.fillInput(contactNumberLocator, candidate.getContactNumber());
        BaseActions.clickButton(saveButtonLocator);
        Wait.waitFPresence(candidateProfileFormLocator);

        return  new RecruitmentCandidatesPage();
    }

    public String getProfileFirsName() {
        return BaseActions.getValue(profileFirstNameLocator);

    }
}
