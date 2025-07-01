package eu.senla.management.rest;

import eu.senla.management.dataactions.ReadPropertyFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecConfig {

    public  static RequestSpecification requestSpecification() {
        CookieRequest request = GetToken.getToken();
        return new RequestSpecBuilder()
                .setBaseUri(ReadPropertyFile.getProperty("LOGINPAGEAPI"))
                //.setAuth(RestAssured.basic(ReadPropertyFile.getProperty("USERNAME"), ReadPropertyFile.getProperty("PASSWORD")))
                .addFormParam("_token", request.token())
                .addFormParam("username", ReadPropertyFile.getProperty("USERNAME"))
                .addFormParam("password", ReadPropertyFile.getProperty("PASSWORD"))
                .addCookie("orangehrm", request.cookie())
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType("text/html")
                .log(LogDetail.ALL)
                .expectStatusCode(302)
                .build();
    }

    public  static RequestSpecification requestTokenSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ReadPropertyFile.getProperty("AUTH"))
                .log(LogDetail.ALL)
                .build();
        }
    public static ResponseSpecification responseTokenSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType("text/html")
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }

}
