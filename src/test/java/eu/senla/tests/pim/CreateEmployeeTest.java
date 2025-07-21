package eu.senla.tests.pim;

import eu.senla.elements.Employee;
import eu.senla.management.dataactions.CreateEntity;
import eu.senla.pages.pimpages.PimPage;
import eu.senla.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmployeeTest extends BaseTest {
    private final Employee employee = CreateEntity.generateEmployee();

    @Test (testName = "Create employee with valid credentials", groups = {"smoke", "regression"})
    public void testCreateEmployee() {
        System.out.println("Start Create employee with valid credentials");
        boolean successfulCreateEmployee = new PimPage()
                .switchToPimPage()
                .openAddEmployeeForm()
                .addEmployeeWithPassword(employee)
                .successfulCreateUser();

        Assert.assertTrue(successfulCreateEmployee);
        System.out.println("Finish Create employee with valid credentials");
    }
}
