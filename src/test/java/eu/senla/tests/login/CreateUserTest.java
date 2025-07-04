package eu.senla.tests.login;

import eu.senla.elements.Employee;
import eu.senla.management.loginstrategy.UiLoginStrategy;
import eu.senla.pages.PimPage;
import eu.senla.tests.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {
    private Employee employee;

    public CreateUserTest() {
        super(new UiLoginStrategy());
    }


    @BeforeTest
    void generateEmployee() {
        employee = Employee.builder()
                .firstName(RandomString.make())
                .middleName(RandomString.make())
                .lastName(RandomString.make())
                .userName(RandomString.make())
                .password(RandomString.make() + "#*" + Math.random())
                .build();

    }

    @BeforeTest
    void loginBeforeTest() {
        BaseTest login = new CreateUserTest();
        login.login();
    }

    @Test (testName = "Create user with valid credentials")
    public void testCreateUser() {
        boolean successfulCreateUser = new PimPage()
                .switchToPimPage()
                .openAddEmployeeForm()
                .addEmployeeWithPassword(employee)
                .succsessfulCreateUser();

        Assert.assertTrue(successfulCreateUser);
    }

//    @Test (testName = "Create user with valid credentials using api")
//    public void testCreateUserApi() {
//
//
//        new PimPage().visitPIMPage();
//        System.out.println("Set new cookie " + Driver.driverRun().manage().getCookieNamed("orangehrm"));
//
//        boolean successfulCreateUser = new PimPage()
//                .switchToPimPage()
//                .openAddEmployeeForm()
//                .addEmployeeWithPassword(employee)
//                .succsessfulCreateUser();
//
//        Assert.assertTrue(successfulCreateUser);
//
//    }
}
