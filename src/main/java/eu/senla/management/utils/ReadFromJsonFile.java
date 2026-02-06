package eu.senla.management.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReadFromJsonFile {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static <K, V> Map<K, V> readFromJsonFileToMap(File file, TypeReference<Map<K, V>> typeRef) throws IOException {
        return  JSON_MAPPER.readValue(file, typeRef);
    }
    // Parse a single object
    public static <T> T readFromJsonToObject(File file, Class<T> clazz) throws IOException {
        return JSON_MAPPER.readValue(file, clazz);
    }


    // Parse a list of objects
    public static <T> T readFromJsonFileToList(File file, TypeReference<T> typeRef) throws IOException {
        return JSON_MAPPER.readValue(file, typeRef);
    }

    //Usage
    // Single object User user = JReadFromJson.readFromJsonToObject(new File("user.json"), User.class);
    // List of objects List<User> users = ReadFromJsonFile.readFromJsonToList( new File("users.json"), new TypeReference<List<User>>() {} );
    // Map Map<String, User> userMap = ReadFromJsonFile.readFromJsonToMap( new File("usersMap.json"), new TypeReference<Map<String, User>>() {} );



}
