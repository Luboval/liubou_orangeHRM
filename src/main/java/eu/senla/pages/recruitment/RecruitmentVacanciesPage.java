package eu.senla.pages.recruitment;

import eu.senla.management.utils.ReadPropertyFile;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.List;

import static eu.senla.management.common.BaseActions.clickElement;
import static eu.senla.management.common.BaseActions.getValueAll;
import static eu.senla.management.common.BaseActions.visit;
import static eu.senla.management.common.Wait.waitFPresence;
import static eu.senla.management.common.constants.PagesPaths.RECRUITMENT_VACANCIES_PAGE;

@Slf4j
public class RecruitmentVacanciesPage {
    private By jobDropDownFieldLocator = By.cssSelector(".oxd-grid-item:nth-child(1)  .oxd-select-text");
    private By jobTitleListLocator = By.cssSelector("[role='listbox']");
    private By jobTitleListEntityLocator = By.cssSelector("[role='option'] span");


    public  void  switchToRecruitmentVacanciesPage() {
        visit(ReadPropertyFile.getProperty("BASEURL") + RECRUITMENT_VACANCIES_PAGE);
    }

    public List<String> getJobTitlesList() {
        switchToRecruitmentVacanciesPage();
        clickElement(jobDropDownFieldLocator);
        waitFPresence(jobTitleListLocator);
        log.info(getValueAll(jobTitleListEntityLocator).toString());
        return getValueAll(jobTitleListEntityLocator);
    }

}
