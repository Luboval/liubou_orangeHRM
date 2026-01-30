package eu.senla.management.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value(); // имя колонки в файле
}
