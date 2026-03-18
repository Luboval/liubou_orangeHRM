package eu.senla.api.actions.dashboardwidgets.employeeonleave;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.dashboard.emponleave.EmpOnLeaveMain;
import eu.senla.api.apielements.dashboard.emponleave.EmpOnLeaveMeta;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import java.time.LocalDate;
import java.util.Map;

import static eu.senla.management.common.constants.ApiPaths.GET_EMP_ON_LEAVE_API;

public class SendEmployeeOnLeaveRequest {

    public static ApiResponse<EmpOnLeaveMain, EmpOnLeaveMeta> sendEmpOnLeaveRequest() {
        return RequestManager.getRequestWithQueryParametersTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                GET_EMP_ON_LEAVE_API,
                Map.of("date", LocalDate.now().toString()),
                new TypeRef<>() {
                }
        );
    }

}
