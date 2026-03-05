package eu.senla.api.actions.dashboardwidgets.quicklaunch;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.quicklaunch.QuickLaunchResponse;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import eu.senla.management.common.constants.ApiPaths;
import io.restassured.common.mapper.TypeRef;

public class SendQuickLaunchRequest {

    public static ApiResponse<QuickLaunchResponse, Meta> sendQuickLaunchRequest() {
        return RequestManager
                .getRequestTypeRef(
                        SpecConfig.requestSpecification(),
                        SpecConfig.responseSpecification(),
                        ApiPaths.GET_QUICK_LAUNCH_API,
                        new TypeRef<ApiResponse<QuickLaunchResponse, Meta>>() { }
                );
    }
}
