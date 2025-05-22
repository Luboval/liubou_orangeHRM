package eu.senla.tests.login;

import eu.senla.elements.Employee;
import eu.senla.pages.PimPage;
import eu.senla.tests.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {
    private Employee employee;

    @BeforeClass
    void generateEmployee() {
        employee = Employee.builder()
                .firstName(RandomString.make())
                .middleName(RandomString.make())
                .lastName(RandomString.make())
                .userName(RandomString.make())
                .password(RandomString.make() + "#*" + Math.random())
                .build();

    }

    @Test (testName = "Create user with valid credentials")
    public void testCreateUser() {
        boolean successfulCreateUser = new PimPage()
            .openAddEmployeeForm()
            .addEmployeeWithPassword(employee)
            .succsessfulCreateUser();

        Assert.assertTrue(successfulCreateUser);

  }
}
