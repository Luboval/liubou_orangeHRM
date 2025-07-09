package eu.senla.apiactions.createemployee;

import eu.senla.management.common.Constants;
import eu.senla.management.auth.RequestManager;
import eu.senla.management.auth.SpecConfig;

public class SendCreateEmployeeRequest {

    public static CreateEmployeeResponse sendCreateEmployeeRequest(Object employeeRequest) {
        return RequestManager.postRequest(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                Constants.CREATE_EMPLOYEE_API,
                employeeRequest,
                CreateEmployeeResponse.class);
    }
}
