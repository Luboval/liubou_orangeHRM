package eu.senla.management.common;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RequestManager {
    public static <T> T postRequest(
            RequestSpecification requestSpecification,
            ResponseSpecification responseSpecification,
            String path,
            Object request,
            Class<T> clazz) {

            return
                given()
                        .spec(requestSpecification)
                        .basePath(path)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpecification)
                        .extract()
                        .as(clazz);
    }

    public static ValidatableResponse getRequest(
            RequestSpecification requestSpecification, String path) {
        return given()
                .spec(requestSpecification)
                .basePath(path)
                .when()
                .get()
                .then();
    }

    public static ValidatableResponse getRequest(
            RequestSpecification requestSpecification, String path,  Map<String,?> queryParametersMap) {
        return given()
                .spec(requestSpecification)
                .basePath(path)
                .queryParams(queryParametersMap)
                .when()
                .get()
                .then();
    }




    public static <T> T getRequestWithQueryParameters(
            RequestSpecification requestSpecification,
            ResponseSpecification responseSpecification,
            String path,

            Map<String,?> queryParametersMap,
            Class<T> clazz) {
        return given()
                .spec(requestSpecification)
                .basePath(path)
                .queryParams(queryParametersMap)
                .when()
                .get()
                .then()
                .spec(responseSpecification)
                .extract()
                .as(clazz);
    }

    public static String cookieRequest(
            RequestSpecification requestSpecification,
            ResponseSpecification responseSpecification
            ) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpecification)
                        .extract()
                        .detailedCookie("orangehrm").getValue();

    }

    public static ValidatableResponse tokenRequest(
            RequestSpecification requestSpecification,
            ResponseSpecification responseSpecification) {
        return given()
                .spec(requestSpecification)
                .when()
                .get()
                .then();
                //.getCookie("orangehrm")
                //.getBody().asString();
    }
}
