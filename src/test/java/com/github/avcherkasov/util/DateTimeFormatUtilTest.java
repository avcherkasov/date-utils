package com.github.avcherkasov.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeFormatUtilTest {

    private static Stream<Named<Function<String, String>>> parsers() {
        return Stream.of(
                Named.of("Legacy (regexp)", DateTimeFormatUtil::parse),
                Named.of("LocalDateTime.parse", DateTimeFormatUtil::parseLocalDateTime),
                Named.of("Instant.parse", DateTimeFormatUtil::parseInstant),
                Named.of("ZoneDateTime(UTC)", DateTimeFormatUtil::parseZoneDateTime),
                Named.of("ITU", DateTimeFormatUtil::parseITU)
        );
    }

    @ParameterizedTest(name = "Parser: {0}")
    @MethodSource("parsers")
    @DisplayName("Проверка всех реализаций парсинга даты")
    public void parseAllImplementations(Function<String, String> parser) {
        var expected = "20261120100405";
        assertEquals(expected, parser.apply("2026-11-20T10:04:05Z"), "Не совпало для строки без мс");
        assertEquals(expected, parser.apply("2026-11-20T10:04:05.906Z"), "Не совпало для строки с 3 мс");
        assertEquals(expected, parser.apply("2026-11-20T10:04:05.991178Z"), "Не совпало для строки с 6 мс");
    }
}