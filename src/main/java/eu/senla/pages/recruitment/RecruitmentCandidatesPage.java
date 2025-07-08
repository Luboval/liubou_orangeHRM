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
    private By resumeInputLocator = By.cssSelector("input[type='file']");

    //Candidate Profile
    private By profileFirstNameLocator = By.cssSelector("[name='firstName']");

    public void switchDirectlyToRecruitmentCandidatesPage() {
        BaseActions.visit(ReadPropertyFile.getProperty("BASEURL") + ApiPoints.RECRUITMENTPAGE);
    }

    public RecruitmentCandidatesPage openAddCandidateForm() {
        switchDirectlyToRecruitmentCandidatesPage();
        BaseActions.clickButton(addCandidateButtonLocator);
        Wait.waitFPresence(addCandidateFormTitleLocator);
        return new RecruitmentCandidatesPage();

    }

    public RecruitmentCandidatesPage addCandidate(Candidate candidate) {
        openAddCandidateForm();
        BaseActions.fillInput(firstNameInputLocator, candidate.getFirstName());
        BaseActions.fillInput(middleNameInputLocator, candidate.getMiddleName());
        BaseActions.fillInput(lastNameInputLocator, candidate.getLastName());
        BaseActions.fillInput(emailInputLocator, candidate.getEmail());
        BaseActions.fillInput(contactNumberLocator, candidate.getContactNumber());
        BaseActions.uploadFile(resumeInputLocator, candidate.getFilePath());
        BaseActions.clickButton(saveButtonLocator);
        Wait.waitFPresence(candidateProfileFormLocator);

        return  new RecruitmentCandidatesPage();
    }

    public String getProfileFirsName(String attribute) {
        return BaseActions.getValue(profileFirstNameLocator, attribute);
    }

    public String getFirstNameInputAttr(String attribute) {
        return BaseActions.getValue(firstNameInputLocator, attribute);
    }

    public String getMiddleNameInputAttr(String attribute) {
        return BaseActions.getValue(middleNameInputLocator, attribute);
    }
    public String getLastNameInputAttr(String attribute) {
        return BaseActions.getValue(lastNameInputLocator, attribute);
    }
    public String getEmailInputAttr(String attribute) {
        return BaseActions.getValue(emailInputLocator, attribute);
    }
    public String getContactNumberInputAttr(String attribute) {
        return BaseActions.getValue(contactNumberLocator, attribute);
    }

}
