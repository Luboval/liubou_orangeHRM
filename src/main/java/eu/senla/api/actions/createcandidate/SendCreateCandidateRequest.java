package eu.senla.api.actions.createcandidate;

import eu.senla.api.apielements.ApiResponse;
import eu.senla.api.apielements.candidate.CandidateApiResponse;
import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;
import io.restassured.common.mapper.TypeRef;

import static eu.senla.management.common.constants.ApiPaths.POST_CREATE_CANDIDATE_API;

public class SendCreateCandidateRequest {

    public static ApiResponse<CandidateApiResponse> sendCreateCandidateRequest(Object createCandidate) {
        return RequestManager.postRequestTypeRef(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification(),
                POST_CREATE_CANDIDATE_API,
                createCandidate,
                new TypeRef<ApiResponse<CandidateApiResponse>>() { }
        );
    }
}
