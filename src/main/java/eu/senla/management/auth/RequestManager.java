package eu.senla.management.auth;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

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
