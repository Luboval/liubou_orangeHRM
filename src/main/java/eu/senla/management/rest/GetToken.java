package eu.senla.management.rest;

import io.restassured.response.ValidatableResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetToken {
    public static String token;
    public static String cookie;

    public static CookieRequest getToken(){
//        String body =  RequestManager.tokenRequest(
//                SpecConfig.requestTokenSpecification(),
//                SpecConfig.responseTokenSpecification());


        ValidatableResponse response = RequestManager.tokenRequest(
               SpecConfig.requestTokenSpecification(),
                SpecConfig.responseTokenSpecification());

        Document document = Jsoup.parse(response.extract().body().asString());


        token = document.select("auth-login").attr(":token");
        System.out.println("Полученный токен" + token);
        cookie = response.extract().detailedCookie("orangehrm").getValue();
        System.out.println("First cookie " + cookie);

        return new CookieRequest (token,cookie);



    }
}
