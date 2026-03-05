package eu.senla.api.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class ObjectToMapConverter {

    private ObjectToMapConverter() {
        // Приватный конструктор для утилитного класса
    }

    /*
     * Преобразует объект в Map, используя все поля
     */
    public static Map<String, Object> toMap(Object obj) {
        return toMap(obj, false);
    }

    /*
     * Преобразует объект в Map с опцией рекурсивного преобразования вложенных объектов
     */
    public static Map<String, Object> toMap(Object obj, boolean recursive) {
        if (obj == null) {
            return new HashMap<>();
        }

        return Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(JsonIgnore.class))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(obj);
                        if (recursive && value != null && !isPrimitiveOrWrapper(value.getClass())) {
                            value = toMap(value, true);
                        }
                        return Map.entry(getKey(field), value);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(entry -> entry != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /*
     * Преобразует объект в Map с булевыми значениями
     */
    public static Map<String, Boolean> toBooleanMap(Object obj) {
        if (obj == null) {
            return new HashMap<>();
        }

        return Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> field.getType() == boolean.class || field.getType() == Boolean.class)
                .filter(field -> !field.isAnnotationPresent(JsonIgnore.class))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return Map.entry(getKey(field), field.getBoolean(obj));
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(entry -> entry != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /*
     * Получает только true значения из булевых полей
     */
    public static Map<String, Boolean> getTrueValues(Object obj) {
        return toBooleanMap(obj).entrySet().stream()
                .filter(Map.Entry::getValue)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /*
     * Получает только false значения из булевых полей
     */
    public static Map<String, Boolean> getFalseValues(Object obj) {
        return toBooleanMap(obj).entrySet().stream()
                .filter(entry -> !entry.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /*
     * Получает список названий полей с true значением
     */
    public static java.util.List<String> getTrueFieldNames(Object obj) {
        return toBooleanMap(obj).entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /*
     * Преобразует объект в Map, используя геттеры вместо полей
     */
    public static Map<String, Object> toMapUsingGetters(Object obj) {
        if (obj == null) {
            return new HashMap<>();
        }

        return Arrays.stream(obj.getClass().getDeclaredMethods())
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .filter(method -> !method.getName().equals("getClass"))
                .map(method -> {
                    try {
                        String fieldName = method.getName().substring(3);
                        fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
                        Object value = method.invoke(obj);

                        JsonProperty annotation = method.getAnnotation(JsonProperty.class);
                        if (annotation != null && !annotation.value().isEmpty()) {
                            fieldName = annotation.value();
                        }

                        return Map.entry(fieldName, value);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(entry -> entry != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /*
     * Проверяет, является ли класс примитивным или оберткой
     */
    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive()
                || type == String.class
                || type == Integer.class
                || type == Long.class
                || type == Double.class
                || type == Float.class
                || type == Boolean.class
                || type == Byte.class
                || type == Short.class
                || type == Character.class;
    }

    /*
     * Получает ключ для поля из аннотации JsonProperty
     */
    private static String getKey(Field field) {
        JsonProperty annotation = field.getAnnotation(JsonProperty.class);
        if (annotation != null && !annotation.value().isEmpty()) {
            return annotation.value();
        }
        return field.getName();
    }
}
