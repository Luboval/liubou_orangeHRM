package eu.senla.api.actions.dashboardwidgets.myactions;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.myactions.MyActionsResponse;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import static eu.senla.management.common.constants.ApiPaths.GET_MY_ACTIONS_API;

public class SendMyActionsRequest {

    public static ApiResponse<MyActionsResponse, Meta> sendMyActionRequest() {
        return RequestManager.getRequestTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                GET_MY_ACTIONS_API,
                new TypeRef<ApiResponse<MyActionsResponse, Meta>>() { }
        );
    }
}
