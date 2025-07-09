package eu.senla.management.auth;

import io.restassured.response.ValidatableResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
* 1. Переименуй класс (но это не самое важное)
 * 2 Тебе надо описать два запроса
 * Первый запрос просто получает токен
 * Второй запрос получает авторазационную куку которую ты должна подкинуть в браузер
 * Итак
*/


public class GetToken {
    public static String token;
    public static String cookie;

    public static CookieRequest getToken() {
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

        return new CookieRequest(token, cookie);

    }


    // ЗАПРОС НОМЕР 1
/*    private static void getTokenFromLoginPage(){
        Response response =  given().
                spec(getRequestSpecification()).
                when().
                get(LOGIN).
                then().spec(getResponseSpecification()).
                extract().
                response();
        cookie = response.detailedCookie("orangehrm");
        Document doc = Jsoup.parse(
                response.body().prettyPrint()
        );
        Element element  = doc.selectFirst("auth-login");
        token = element.attr("token");
    }*/

    //запрос 2
   // Обрати внимание что здесь в строке 63 ты вызываешь первый запрос, инициализируешь токен и потом его передаешь в обмен на куки
    // это кстати можно отрефакторить и сделать по-своему
    // в целом ты получишь куки ее то и надо положить в браузер во время его инициализации
    // НО етьс хитрость, что ты получила Cookie из рест ашурда
    // а у селениума свой тип Cookie
/*   public static Cookie getAuthCookie(){
        getTokenFromLoginPage();
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("_token", token);
        formParams.put("username", ConfigLoader.getInstance().getUsername());
        formParams.put("password", ConfigLoader.getInstance().getPassword());
        return given().
                spec(getRequestSpecification()).
                cookie(cookie).
                formParams(formParams).
                when().
                post(VALIDATE).
                then().spec(getResponseSpecification()).
                statusCode(302).
                extract().
                response().detailedCookie("orangehrm");
    }*/
}
