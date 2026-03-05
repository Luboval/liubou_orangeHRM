package eu.senla.api.actions.dashboardwidgets.timeatwork;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkDataResponse;
import eu.senla.api.apielements.dashboard.timeatwork.TimeAtWorkMetaResponse;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import eu.senla.management.common.constants.ApiPaths;
import io.restassured.common.mapper.TypeRef;

public class SendTimeAtWorkRequest {

    public static ApiResponse<TimeAtWorkDataResponse, TimeAtWorkMetaResponse> sendTimeAtWorkRequest() {
        return RequestManager.getRequestWithQueryParametersTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                ApiPaths.GET_TIME_AT_WORK_API,
                CreateTimeAtWorkRequest.createMyActionRequest(),
                new TypeRef<ApiResponse<TimeAtWorkDataResponse, TimeAtWorkMetaResponse>>() { }
        );
    }
}
