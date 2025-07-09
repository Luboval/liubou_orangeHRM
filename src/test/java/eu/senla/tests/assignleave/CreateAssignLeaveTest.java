package eu.senla.tests.assignleave;

import eu.senla.apiactions.createemployee.CreateEmployeeRequest;
import eu.senla.apiactions.createemployee.CreateEmployeeResponse;
import eu.senla.apiactions.createemployee.EmployeeRequest;
import eu.senla.apiactions.createemployee.SendCreateEmployeeRequest;
import eu.senla.elements.AssignLeave;
import eu.senla.management.loginstrategy.ApiLoginStrategy;
import eu.senla.pages.leave.AssignLeavePage;
import eu.senla.tests.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CreateAssignLeaveTest extends BaseTest {
    private String employeeName;
    private AssignLeave assigneLeave;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");

    public CreateAssignLeaveTest() {
        super(new ApiLoginStrategy());
    }

    @BeforeClass
    void createEmployee() {
        EmployeeRequest employeeRequest = CreateEmployeeRequest.createEmployeeRequest();
        CreateEmployeeResponse createEmployeeResponse = SendCreateEmployeeRequest.sendCreateEmployeeRequest(employeeRequest);
        employeeName = createEmployeeResponse.getData().get(0).getFirstName() + " " + createEmployeeResponse.getData().get(0).getMiddleName() + " " + createEmployeeResponse.getData().get(0).getLastName();

        assigneLeave = AssignLeave.builder()
                .employeeName(employeeName)
                .fromDate(LocalDate.now().plus(Period.ofDays(0)).format(formatter))
                .toDate(LocalDate.now().plus(Period.ofDays(20)).format(formatter))
                .build();

    }

    @Test
    public void createAssignLeave() {

       AssignLeavePage assignLeavePage = new AssignLeavePage()
                .assignleave(assigneLeave);

    }
}
