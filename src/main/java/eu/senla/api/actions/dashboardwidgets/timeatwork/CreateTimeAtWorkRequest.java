package eu.senla.api.actions.dashboardwidgets.timeatwork;

import net.datafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CreateTimeAtWorkRequest {
    static Faker faker = new Faker();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static  Map<String, ?> createMyActionRequest() {
        return Map.of(
                "timezoneOffset", faker.number().numberBetween(1, 7)
                );
    }

}
