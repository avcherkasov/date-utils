package com.github.avcherkasov.constant;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DatePattern {
    public static final Pattern DATE_TIME_3_DIGIT_MIL_SEC = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z");
    public static final Pattern DATE_TIME_6_DIGIT_MIL_SEC = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}Z");

    public static final DateTimeFormatter OUTPUT_FULL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DateFormat.OUTPUT_FULL_DATE_TIME);
}
