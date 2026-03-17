package eu.senla.api.utils;

import eu.senla.elements.dashboard.PieChartTooltip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static List<PieChartTooltip> parseTooltipsFromList(List<String> inputList) {
        List<PieChartTooltip> result = new ArrayList<>();

        // Регулярное выражение для парсинга: текст(число.число%)
        Pattern pattern = Pattern.compile("(.+?)(\\d+)\\((\\d+\\.\\d+)%\\)");

        for (String input : inputList) {
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String title = matcher.group(1).trim();  // Engineering
                int number = Integer.parseInt(matcher.group(2));  // 2
                double percent = Double.parseDouble(matcher.group(3));  // 1.50

                result.add(new PieChartTooltip(title, number, percent));
            }
        }

        return result;
    }
}
