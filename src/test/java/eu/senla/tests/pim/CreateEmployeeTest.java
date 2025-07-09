package eu.senla.tests.pim;

import eu.senla.elements.Employee;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.pim.PimPage;
import eu.senla.tests.BaseTest;
import net.bytebuddy.utility.RandomString;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateEmployeeTest extends BaseTest {
    private Employee employee;
    Faker faker = new Faker();

    public CreateEmployeeTest() {
        super(new ApiLoginStrategy());
    }


    @BeforeTest
    void generateEmployee() {
        employee = Employee.builder()
                .firstName(faker.name().firstName())
                .middleName(faker.name().nameWithMiddle())
                .lastName(faker.name().lastName())
                .userName(faker.internet().username())
                .password(RandomString.make() + "#*" + Math.random())
                .build();

    }

//    @BeforeTest
//    void loginBeforeTest() {
//        BaseTest login = new CreateEmployeeTest();
//        login.login();
//    }

    @Test (testName = "Create employee with valid credentials")
    public void testCreateEmployee() {
        boolean successfulCreateEmployee = new PimPage()
                .switchToPimPage()
                .openAddEmployeeForm()
                .addEmployeeWithPassword(employee)
                .successfulCreateUser();

        Assert.assertTrue(successfulCreateEmployee);
    }

}
