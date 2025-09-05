package eu.senla.tests.pim;

import eu.senla.elements.Employee;
import eu.senla.management.dataactions.CreateEntity;
import eu.senla.pages.pimpages.PimPage;
import eu.senla.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class CreateEmployeeTest extends BaseTest {
    private final Employee employee = CreateEntity.generateEmployee();

    @Test (testName = "Create employee with valid credentials", description = "Create employee with valid credentials", groups = {"smoke", "regression"})
    public void testCreateEmployee() {
        log.info("Start Create employee with valid credentials");
        boolean successfulCreateEmployee = new PimPage()
                .switchToPimPage()
                .openAddEmployeeForm()
                .addEmployeeWithPassword(employee)
                .successfulCreateUser();

        Assert.assertTrue(successfulCreateEmployee);
        log.info("Finish Create employee with valid credentials");
    }
}
