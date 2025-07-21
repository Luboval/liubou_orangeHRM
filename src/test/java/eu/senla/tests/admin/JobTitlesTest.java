package eu.senla.tests.admin;

import eu.senla.elements.Title;
import eu.senla.management.dataactions.CreateEntity;
import eu.senla.pages.adminpages.JobAdminPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JobTitlesTest extends BaseTest {
    private final Title title = CreateEntity.createTitle();;

    @Test (testName = "Create Job Title", groups = {"smoke", "regression"})
    public void addJobTitleTest() {
        System.out.println("Create Job Title");
        JobAdminPage page = new JobAdminPage()
                .createTitle(title);
        Assert.assertEquals(title.getJobTitle(), page.getJobsCreated(title.getJobTitle()), "JobTitle are not found");
        System.out.println("Create Job Title is completed");

    }

    @Test (testName = "Delete Job Title", groups = {"regression"})
    public void deleteJobTitleTest() {

        JobAdminPage page = new JobAdminPage();
        Assert.assertTrue(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is missing");
        page.deleteTitle(title);
        Assert.assertFalse(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is not removed");

    }
}
