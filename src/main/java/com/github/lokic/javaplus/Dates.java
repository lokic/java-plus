package com.github.lokic.javaplus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dates {

    private final static Map<String, DateTimeFormatter> DATE_FORMATTER_CACHE = new ConcurrentHashMap<>();

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String format(LocalDate localDate, String format) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(getDateTimeFormatter(format));
    }

    public static String format(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(getDateTimeFormatter(format));
    }

    public static LocalDate parseLocalDate(String date, String format) {
        return LocalDate.parse(date, getDateTimeFormatter(format));
    }

    public static LocalDateTime parseLocalDateTime(String date, String format) {
        return LocalDateTime.parse(date, getDateTimeFormatter(format));
    }

    private static DateTimeFormatter getDateTimeFormatter(String format) {
        return DATE_FORMATTER_CACHE.computeIfAbsent(format, DateTimeFormatter::ofPattern);
    }
}
