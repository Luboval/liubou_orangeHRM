package eu.senla.management.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

public class ReadFromJsonResponse {
    // Универсальный метод
     public static <T> T getJsonNode(ValidatableResponse response, String path, Class<T> clazz) {
         String json = response.extract().asString();
         JsonPath jp = new JsonPath(json);
         return jp.getObject(path, clazz);
     }

    // Перегрузки для самых частых случаев
     public static String getString(ValidatableResponse response, String path) {
         return getJsonNode(response, path, String.class);
     }

     public static Integer getInt(ValidatableResponse response, String path) {
         return getJsonNode(response, path, Integer.class);
     }

     public static List<?> getList(ValidatableResponse response, String path) {
         return getJsonNode(response, path, List.class);
     }

     public static Map<?,?> getMap(ValidatableResponse response, String path) {
         return getJsonNode(response, path, Map.class);
     }
}
