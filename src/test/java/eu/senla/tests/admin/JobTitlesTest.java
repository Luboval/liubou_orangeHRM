package eu.senla.tests.admin;

import eu.senla.elements.Title;
import eu.senla.management.dataactions.create.CreateEntity;
import eu.senla.pages.adminpages.JobAdminPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class JobTitlesTest extends BaseTest {
    private final Title title = CreateEntity.createTitle();

    @Test (testName = "Create Job Title", description = "Create Job Title", groups = {"smoke", "regression"})
    public void addJobTitleTest() {
        log.info("Create Job Title");
        JobAdminPage page = new JobAdminPage()
                .createTitle(title);
        Assert.assertEquals(title.getJobTitle(), page.getJobsCreated(title.getJobTitle()), "JobTitle are not found");
        log.info("Create Job Title is completed");

    }

    @Test (testName = "Delete Job Title", description = "Delete Job Title", groups = {"regression"})
    public void deleteJobTitleTest() {
        JobAdminPage page = new JobAdminPage();
        log.info("Start Delete Job Title");
        Assert.assertTrue(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is missing");
        page.deleteTitle(title);
        Assert.assertFalse(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is not removed");
        log.info("Finish Delete Job Title");

    }
}
