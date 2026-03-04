package eu.senla.api.actions.createemployee;


import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;

import static eu.senla.management.common.constants.ApiPaths.CREATE_EMPLOYEE_API;

public class SendCreateEmployeeRequest {

    public static CreateEmployeeResponse sendCreateEmployeeRequest(Object employeeRequest) {
        return RequestManager.postRequest(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                CREATE_EMPLOYEE_API,
                employeeRequest,
                CreateEmployeeResponse.class);
    }
}
