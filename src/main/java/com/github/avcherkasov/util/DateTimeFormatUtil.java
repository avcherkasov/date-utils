package com.github.avcherkasov.util;

import com.ethlo.time.DateTime;
import com.ethlo.time.ITU;
import com.github.avcherkasov.constant.DateFormat;
import com.github.avcherkasov.constant.DatePattern;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DateTimeFormatUtil {

    // 1 Variant
    public static String parse(String date) {
        var formatter = DateFormat.FULL_DATE_TIME_WITHOUT_MIL_SEC;
        if (matches(DatePattern.DATE_TIME_3_DIGIT_MIL_SEC, date)) {
            formatter = DateFormat.FULL_DATE_TIME_WITH_3_MIL_SEC;
        }
        if (matches(DatePattern.DATE_TIME_6_DIGIT_MIL_SEC, date)) {
            formatter = DateFormat.FULL_DATE_TIME_WITH_6_MIL_SEC;
        }
        var ltd = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(formatter));
        return ltd.format(DatePattern.OUTPUT_FULL_DATE_TIME_FORMAT);
    }

    public static boolean matches(Pattern pattern, String date) {
        return pattern.matcher(date).matches();
    }

    // 2 Variant
    public static String parseLocalDateTime(String date) {
        var in = DateTimeFormatter.ofPattern(DateFormat.FULL_DATE_TIME_WITH_MIL_SEC);
        return LocalDateTime.parse(date, in).format(DatePattern.OUTPUT_FULL_DATE_TIME_FORMAT);
    }

    // 3 Variant
    public static String parseInstant(String date) {
        Instant instant = Instant.parse(date);
        return DatePattern.OUTPUT_FULL_DATE_TIME_FORMAT.format(instant.atZone(ZoneOffset.UTC));
    }

    // 4 Variant
    public static String parseZoneDateTime(String date) {
        OffsetDateTime odt = OffsetDateTime.parse(date);
        ZonedDateTime zdt = odt.atZoneSameInstant(ZoneOffset.UTC);
        return zdt.format(DatePattern.OUTPUT_FULL_DATE_TIME_FORMAT);
    }

    // 5 Variant
    public static String parseITU(String date) {
        DateTime parseDateTime = ITU.parseLenient(date);
        return parseDateTime.toLocalDatetime().format(DatePattern.OUTPUT_FULL_DATE_TIME_FORMAT);
    }

}
