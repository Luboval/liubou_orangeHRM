package eu.senla.tests.admin;

import eu.senla.elements.Title;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.adminpages.JobAdminPage;
import eu.senla.tests.BaseTest;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JobTitlesTest extends BaseTest {
    private Title title;
    static Faker faker = new Faker();

    public JobTitlesTest() {
        super(new ApiLoginStrategy());
    }

    @BeforeClass
    void createTitle() {
        title = Title.builder()
                .jobTitle(faker.name().title())
                .jobDescription(faker.text().text(20))
                .note(faker.text().text(10))
                .build();
        System.out.println(title.getJobTitle());

    }

    @Test
    public void addJobTitleTest() {
        JobAdminPage page = new JobAdminPage()
                .createTitle(title);
        Assert.assertEquals(title.getJobTitle(), page.getJobsCreated(title.getJobTitle()), "JobTitle are not found");

    }

    @Test
    public void deleteJobTitleTest() {

        JobAdminPage page = new JobAdminPage();
        Assert.assertTrue(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is missing");
        page.deleteTitle(title);
        Assert.assertFalse(page.getAllTitles().contains(title.getJobTitle()), "JobTitle is not removed");

    }
}
