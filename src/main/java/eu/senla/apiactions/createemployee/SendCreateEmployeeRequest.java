package eu.senla.apiactions.createemployee;

import eu.senla.elements.ApiPoints;
import eu.senla.management.auth.RequestManager;
import eu.senla.management.auth.SpecConfig;

public class SendCreateEmployeeRequest {

    public static CreateEmployeeResponse sendCreateEmployeeRequest(Object employeeRequest) {
        return RequestManager.postRequest(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                ApiPoints.CREATE_EMPLOYEE_API,
                employeeRequest,
                CreateEmployeeResponse.class);
    }
}
