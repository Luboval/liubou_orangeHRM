package eu.senla.management.auth;

import eu.senla.management.common.RequestManager;
import eu.senla.management.common.SpecConfig;

public class GetCookie {

    public static String getCookie() {
         return RequestManager.cookieRequest(
                SpecConfig.requestCookieSpecification(),
                SpecConfig.responseCookieSpecification()
                );
    }
}
