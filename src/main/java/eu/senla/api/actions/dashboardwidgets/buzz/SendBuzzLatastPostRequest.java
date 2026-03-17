package eu.senla.api.actions.dashboardwidgets.buzz;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.Meta;
import eu.senla.api.apielements.dashboard.buzzlatestposts.BuzzLatestPost;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import java.util.Map;

import static eu.senla.management.common.constants.ApiPaths.GET_BUZZ_LAST_POST_API;

public class SendBuzzLatastPostRequest {

    public static ApiResponse<BuzzLatestPost, Meta> sendBuzzLastPostRequest() {
        return RequestManager.getRequestWithQueryParametersTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                GET_BUZZ_LAST_POST_API,
                Map.of("limit", 5,
                        "offset", 0,
                        "sortOrder", "DESC",
                        "sortField", "share.createdAtUtc"),
                new TypeRef<>() {
                }
        );
    }
}
