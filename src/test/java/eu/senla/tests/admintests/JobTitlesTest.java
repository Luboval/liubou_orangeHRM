package eu.senla.tests.admintests;

import eu.senla.elements.Title;
import eu.senla.management.loginstrategy.UiLoginStrategy;
import eu.senla.pages.admin.JobAdminPage;
import eu.senla.tests.BaseTest;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JobTitlesTest extends BaseTest {
    private Title title;
    static Faker faker = new Faker();

    public JobTitlesTest() {
        super(new UiLoginStrategy());
    }


    @BeforeTest
    void loginBeforeTest() {
        BaseTest login = new JobTitlesTest();
        login.login();
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
        JobAdminPage page = new JobAdminPage()
                .deleteTitle(title);

        Assert.assertFalse(page.getAllTitles().contains(title.getJobTitle()), "JobTitle are removed");


        System.out.println("delete test");

    }

}
