package eu.senla.api.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatUtil {

    public static String capitalizeWordsStream(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        return Arrays.stream(string.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase()
                 + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
