package eu.senla.management.auth;

public class GetCookie {

    public static String getCookie() {
         return RequestManager.cookieRequest(
                SpecConfig.requestCookieSpecification(),
                SpecConfig.responseCookieSpecification()
                );
    }
}
