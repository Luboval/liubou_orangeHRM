package eu.senla.pages.recruitment;

import eu.senla.elements.Candidate;
import eu.senla.management.utils.ReadPropertyFile;
import org.openqa.selenium.By;

import static eu.senla.management.common.BaseActions.clickButton;
import static eu.senla.management.common.BaseActions.fillInput;
import static eu.senla.management.common.BaseActions.getValue;
import static eu.senla.management.common.BaseActions.uploadFile;
import static eu.senla.management.common.BaseActions.visit;
import static eu.senla.management.common.Wait.waitFPresence;
import static eu.senla.management.common.constants.PagesPaths.RECRUITMENT_PAGE;

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
        visit(ReadPropertyFile.getProperty("BASEURL") + RECRUITMENT_PAGE);
    }

    public RecruitmentCandidatesPage openAddCandidateForm() {
        switchDirectlyToRecruitmentCandidatesPage();
        clickButton(addCandidateButtonLocator);
        waitFPresence(addCandidateFormTitleLocator);
        return new RecruitmentCandidatesPage();

    }

    public RecruitmentCandidatesPage addCandidateWithFile(Candidate candidate) {
        openAddCandidateForm();
        fillInput(firstNameInputLocator, candidate.getFirstName());
        fillInput(middleNameInputLocator, candidate.getMiddleName());
        fillInput(lastNameInputLocator, candidate.getLastName());
        fillInput(emailInputLocator, candidate.getEmail());
        fillInput(contactNumberLocator, candidate.getContactNumber());
        uploadFile(resumeInputLocator, candidate.getFilePath());
        clickButton(saveButtonLocator);
        waitFPresence(candidateProfileFormLocator);

        return  new RecruitmentCandidatesPage();
    }

    public RecruitmentCandidatesPage addCandidateWithoutFile(Candidate candidate) {
        openAddCandidateForm();
        fillInput(firstNameInputLocator, candidate.getFirstName());
        fillInput(middleNameInputLocator, candidate.getMiddleName());
        fillInput(lastNameInputLocator, candidate.getLastName());
        fillInput(emailInputLocator, candidate.getEmail());
        fillInput(contactNumberLocator, candidate.getContactNumber());
        clickButton(saveButtonLocator);
        waitFPresence(candidateProfileFormLocator);
        return  new RecruitmentCandidatesPage();
    }

    public ErrorRecruitmentCandidatesPage errorAddCandidateFromJsonFile(Candidate candidate) {
        openAddCandidateForm();
        fillInput(firstNameInputLocator, candidate.getFirstName());
        fillInput(middleNameInputLocator, candidate.getMiddleName());
        fillInput(lastNameInputLocator, candidate.getLastName());
        fillInput(emailInputLocator, candidate.getEmail());
        fillInput(contactNumberLocator, candidate.getContactNumber());
        clickButton(saveButtonLocator);
        return  new ErrorRecruitmentCandidatesPage();
    }

    public String getProfileFirsName(String attribute) {
        return getValue(profileFirstNameLocator, attribute);
    }

    public String getFirstNameInputAttr(String attribute) {
        return getValue(firstNameInputLocator, attribute);
    }

    public String getMiddleNameInputAttr(String attribute) {
        return getValue(middleNameInputLocator, attribute);
    }
    public String getLastNameInputAttr(String attribute) {
        return getValue(lastNameInputLocator, attribute);
    }
    public String getEmailInputAttr(String attribute) {
        return getValue(emailInputLocator, attribute);
    }
    public String getContactNumberInputAttr(String attribute) {
        return getValue(contactNumberLocator, attribute);
    }

}
