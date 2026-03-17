package eu.senla.api.actions.dashboardwidgets.empdistribution;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.dashboard.empdistribution.EmpByLocation;
import eu.senla.api.apielements.dashboard.empdistribution.EmpByLocationMeta;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import static eu.senla.management.common.constants.ApiPaths.GET_EMPL_BY_LOCATION;

public class SendEmployeeDistribByLocation {


    public static ApiResponse<EmpByLocation, EmpByLocationMeta> sendEmployeeByLocationRequest() {
        return RequestManager.getRequestTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                GET_EMPL_BY_LOCATION,
                new TypeRef<>() {
                }
        );
    }
}
