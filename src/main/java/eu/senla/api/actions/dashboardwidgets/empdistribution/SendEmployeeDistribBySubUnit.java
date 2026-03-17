package eu.senla.api.actions.dashboardwidgets.empdistribution;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.dashboard.empdistribution.EmpBySubUnit;
import eu.senla.api.apielements.dashboard.empdistribution.EmpBySubUnitMeta;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import static eu.senla.management.common.constants.ApiPaths.GET_EMPL_BY_SUB_UNIT;

public class SendEmployeeDistribBySubUnit {


    public static ApiResponse<EmpBySubUnit, EmpBySubUnitMeta> sendEmployeeBySubUnitRequest() {
        return RequestManager.getRequestTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                GET_EMPL_BY_SUB_UNIT,
                new TypeRef<>() {
                }
        );
    }
}
