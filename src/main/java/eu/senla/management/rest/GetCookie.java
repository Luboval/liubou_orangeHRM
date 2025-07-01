package eu.senla.management.rest;

public class GetCookie {

    public static String getCookie() {
         return RequestManager.cookieRequest(
                SpecConfig.requestSpecification(),
                SpecConfig.responseSpecification()
                );
    }
}
