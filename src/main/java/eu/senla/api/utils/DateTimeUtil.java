package eu.senla.api.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Slf4j
public class DateTimeUtil {
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // ============= ФОРМАТИРОВАНИЕ ДАТЫ С СУФФИКСОМ =============

    /**
     * Форматирует дату из строки в формат "MMM d" с суффиксом (st, nd, rd, th).
     * @param dateString дата в формате "yyyy-MM-dd".
     * @return отформатированная дата, например "Mar 29th".
     */
    public static String formatDateWithSuffix(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return formatDateWithSuffix(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты. Ожидается: yyyy-MM-dd", e);
        }
    }

    /**
     * Форматирует LocalDate в формат "MMM d" с суффиксом (st, nd, rd, th).
     * @param date объект LocalDate.
     * @return отформатированная дата, например "Mar 29th".
     */
    public static String formatDateWithSuffix(LocalDate date) {
        String month = date.format(DateTimeFormatter.ofPattern("MMM", DEFAULT_LOCALE));
        int day = date.getDayOfMonth();
        String suffix = getDaySuffix(day);

        return String.format("%s %d%s", month, day, suffix);
    }

    // ============= ФОРМАТИРОВАНИЕ ДАТЫ БЕЗ СУФФИКСА =============

    /**
     * Форматирует дату из строки в формат "MMM dd" (с ведущим нулем для дня).
     * @param dateString дата в формате "yyyy-MM-dd".
     * @return отформатированная дата, например "Mar 02".
     */
    public static String formatDateWithoutSuffix(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return formatDateWithoutSuffix(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты. Ожидается: yyyy-MM-dd", e);
        }
    }

    /**
     * Форматирует LocalDate в формат "MMM dd" (с ведущим нулем для дня).
     * @param date объект LocalDate.
     * @return отформатированная дата, например "Mar 02".
     */
    public static String formatDateWithoutSuffix(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd", DEFAULT_LOCALE);
        return date.format(formatter);
    }

    /**
     * Форматирует дату из строки в формат "MMM d" (без ведущего нуля и без суффикса).
     * @param dateString дата в формате "yyyy-MM-dd".
     * @return отформатированная дата, например "Mar 2".
     */
    public static String formatDateSimple(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return formatDateSimple(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты. Ожидается: yyyy-MM-dd", e);
        }
    }

    /**
     * Форматирует LocalDate в формат "MMM d" (без ведущего нуля и без суффикса).
     * @param date объект LocalDate.
     * @return отформатированная дата, например "Mar 2".
     */
    public static String formatDateSimple(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d", DEFAULT_LOCALE);
        return date.format(formatter);
    }


    public static String convertDateFormatSafe(String inputDate) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");

            LocalDate date = LocalDate.parse(inputDate, inputFormatter);
            return date.format(outputFormatter);
        } catch (Exception e) {
            System.err.println("Ошибка преобразования даты: " + inputDate);
            return inputDate; // или выбросить исключение
        }
    }

    // ============= ФОРМАТИРОВАНИЕ ДАТЫ С ВОЗМОЖНОСТЬЮ ВЫБОРА =============

    /**
     * Форматирует дату с возможностью выбора стиля.
     * @param dateString дата в формате "yyyy-MM-dd".
     * @param style стиль форматирования (WITH_SUFFIX, WITHOUT_SUFFIX, SIMPLE).
     * @return отформатированная дата.
     */
    public static String formatDate(String dateString, DateFormatStyle style) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        return formatDate(date, style);
    }

    /**
     * Форматирует LocalDate с возможностью выбора стиля.
     * @param date объект LocalDate.
     * @param style стиль форматирования.
     * @return отформатированная дата.
     */
    public static String formatDate(LocalDate date, DateFormatStyle style) {
        switch (style) {
            case WITH_SUFFIX:
                return formatDateWithSuffix(date);
            case WITHOUT_SUFFIX:
                return formatDateWithoutSuffix(date);
            case SIMPLE:
                return formatDateSimple(date);
            default:
                return formatDateWithoutSuffix(date);
        }
    }

    /**
     * Стили форматирования даты.
     */
    public enum DateFormatStyle {
        WITH_SUFFIX,    // "Mar 29th"
        WITHOUT_SUFFIX, // "Mar 02"
        SIMPLE          // "Mar 2"
    }

    // ============= ФОРМАТИРОВАНИЕ ВРЕМЕНИ =============

    /**
     * Форматирует время из строки в 12-часовой формат с AM/PM.
     * @param timeString время в формате "HH:mm" (например "13:19").
     * @return отформатированное время, например "01:19 PM".
     */
    public static String formatTimeTo12Hour(String timeString) {
        try {
            LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
            return formatTimeTo12Hour(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат времени. Ожидается: HH:mm", e);
        }
    }

    /**
     * Форматирует LocalTime в 12-часовой формат с AM/PM.
     * @param time объект LocalTime.
     * @return отформатированное время, например "01:19 PM".
     */
    public static String formatTimeTo12Hour(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", DEFAULT_LOCALE);
        return time.format(formatter);
    }

    /**
     * Форматирует время из строки в 12-часовой формат с AM/PM и секундами.
     * @param timeString время в формате "HH:mm:ss".
     * @return отформатированное время, например "01:19:30 PM".
     */
    public static String formatTimeTo12HourWithSeconds(String timeString) {
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return formatTimeTo12HourWithSeconds(time);
    }

    /**
     * Форматирует LocalTime в 12-часовой формат с AM/PM и секундами.
     * @param time объект LocalTime.
     * @return отформатированное время, например "01:19:30 PM".
     */
    public static String formatTimeTo12HourWithSeconds(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a", DEFAULT_LOCALE);
        return time.format(formatter);
    }

    public static String convertUtcToLocal(String utcTime) {
        return LocalTime.parse(utcTime, DateTimeFormatter.ofPattern("HH:mm"))
                .atDate(java.time.LocalDate.now())
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("hh:mm a", Locale.US));
    }





    // ============= ФОРМАТИРОВАНИЕ ДАТЫ И ВРЕМЕНИ =============

    /**
     * Форматирует дату и время из строк.
     * @param dateString дата в формате "yyyy-MM-dd".
     * @param timeString время в формате "HH:mm".
     * @return отформатированные дата и время, например "Mar 29th at 01:19 PM".
     */
    public static String formatDateTime(String dateString, String timeString) {
        return formatDateTime(dateString, timeString, DateFormatStyle.WITH_SUFFIX);
    }

    /**
     * Форматирует дату и время из строк с указанием стиля даты.
     * @param dateString дата в формате "yyyy-MM-dd".
     * @param timeString время в формате "HH:mm".
     * @param dateStyle стиль форматирования даты.
     * @return отформатированные дата и время.
     */
    public static String formatDateTime(String dateString, String timeString, DateFormatStyle dateStyle) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return formatDateTime(date, time, dateStyle);
    }

    /**
     * Форматирует LocalDateTime.
     * @param dateTime объект LocalDateTime.
     * @return отформатированные дата и время, например "Mar 29th at 01:19 PM".
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime.toLocalDate(), dateTime.toLocalTime(), DateFormatStyle.WITH_SUFFIX);
    }

    /**
     * Форматирует дату и время из отдельных объектов.
     * @param date объект LocalDate.
     * @param time объект LocalTime.
     * @return отформатированные дата и время, например "Mar 29th at 01:19 PM".
     */
    public static String formatDateTime(LocalDate date, LocalTime time) {
        return formatDateTime(date, time, DateFormatStyle.WITH_SUFFIX);
    }

    /**
     * Форматирует дату и время из отдельных объектов с указанием стиля даты.
     * @param date объект LocalDate.
     * @param time объект LocalTime.
     * @param dateStyle стиль форматирования даты.
     * @return отформатированные дата и время.
     */
    public static String formatDateTime(LocalDate date, LocalTime time, DateFormatStyle dateStyle) {
        String formattedDate = formatDate(date, dateStyle);
        String formattedTime = formatTimeTo12Hour(time);
        return String.format("%s at %s", formattedDate, formattedTime);
    }

    /**
     * Форматирует дату и время с кастомным разделителем.
     * @param dateString дата в формате "yyyy-MM-dd".
     * @param timeString время в формате "HH:mm".
     * @param separator разделитель между датой и временем.
     * @return отформатированные дата и время.
     */
    public static String formatDateTime(String dateString, String timeString, String separator) {
        return formatDateTime(dateString, timeString, DateFormatStyle.WITH_SUFFIX, separator);
    }

    /**
     * Форматирует дату и время с указанием стиля даты и разделителя.
     * @param dateString дата в формате "yyyy-MM-dd".
     * @param timeString время в формате "HH:mm".
     * @param dateStyle стиль форматирования даты.
     * @param separator разделитель между датой и временем.
     * @return отформатированные дата и время.
     */
    public static String formatDateTime(String dateString, String timeString, DateFormatStyle dateStyle, String separator) {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        String formattedDate = formatDate(date, dateStyle);
        String formattedTime = formatTimeTo12Hour(time);
        return String.format("%s%s%s", formattedDate, separator, formattedTime);
    }

    // ============= ТЕКУЩИЕ ДАТА И ВРЕМЯ =============

    /**
     * Возвращает текущую дату и время в отформатированном виде.
     * @return текущие дата и время.
     */
    public static String getCurrentDateTime() {
        return formatDateTime(LocalDateTime.now());
    }

    /**
     * Возвращает текущую дату в отформатированном виде с суффиксом.
     * @return текущая дата, например "Mar 29th".
     */
    public static String getCurrentDateWithSuffix() {
        return formatDateWithSuffix(LocalDate.now());
    }

    /**
     * Возвращает текущую дату в отформатированном виде без суффикса.
     * @return текущая дата, например "Mar 02".
     */
    public static String getCurrentDateWithoutSuffix() {
        return formatDateWithoutSuffix(LocalDate.now());
    }

    /**
     * Возвращает текущую дату в простом формате.
     * @return текущая дата, например "Mar 2".
     */
    public static String getCurrentDateSimple() {
        return formatDateSimple(LocalDate.now());
    }

    /**
     * Возвращает текущее время в 12-часовом формате.
     * @return текущее время, например "01:19 PM".
     */
    public static String getCurrentTime() {
        return formatTimeTo12Hour(LocalTime.now());
    }

    // ============= ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ =============

    /**
     * Определяет суффикс для дня месяца (st, nd, rd, th).
     * @param day день месяца.
     * @return суффикс.
     */
    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
}
