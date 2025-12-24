package com.io.tedtalks.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {
    
    @Test
    void testParseDate_December2021() {
        LocalDate result = DateParser.parseDate("December 2021");
        assertEquals(LocalDate.of(2021, 12, 1), result);
    }
    
    @Test
    void testParseDate_February2022() {
        LocalDate result = DateParser.parseDate("February 2022");
        assertEquals(LocalDate.of(2022, 2, 1), result);
    }
    
    @Test
    void testParseDate_InvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            DateParser.parseDate("2021-12");
        });
    }
}

