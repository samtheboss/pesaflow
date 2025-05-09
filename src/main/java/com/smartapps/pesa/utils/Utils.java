package com.smartapps.pesa.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public class Utils {

    private static final List<DateTimeFormatter> INPUT_FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX z VV"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX z"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
    );

    public static ZonedDateTime parseFlexibleDate(String input) {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                TemporalAccessor ta = formatter.parse(input);
                if (ta instanceof ZonedDateTime) {
                    return (ZonedDateTime) ta;
                } else if (ta.isSupported(ChronoField.OFFSET_SECONDS)) {
                    return ZonedDateTime.of(LocalDateTime.from(ta), ZoneOffset.from(ta));
                } else {
                    return LocalDateTime.from(ta).atZone(ZoneId.systemDefault());
                }
            } catch (DateTimeParseException ignored) {
                // Try next formatter
            }
        }
        throw new IllegalArgumentException("Unrecognized date format: " + input);
    }
    public static String getFirstPart(String input) {
        if (input.contains("-")) {
            return input.split("-", 2)[0];
        } else {
            return input;
        }
    }
}
