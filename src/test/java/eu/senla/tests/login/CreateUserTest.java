package eu.senla.tests.login;

import eu.senla.elements.Employee;
import eu.senla.pages.PimPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {
    private Employee employee;

    @BeforeClass
    void generateEmployee() {
        employee = Employee.builder()
                .firstName("123")
                .middleName("123")
                .lastName("123")
                .userName("123456789")
                .password("1964@#Lewrd")
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
