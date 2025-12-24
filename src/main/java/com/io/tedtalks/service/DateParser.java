package com.io.tedtalks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DateParser {
    
    private static final Logger logger = LoggerFactory.getLogger(DateParser.class);
    
    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();
    
    static {
        MONTH_MAP.put("january", 1);
        MONTH_MAP.put("february", 2);
        MONTH_MAP.put("march", 3);
        MONTH_MAP.put("april", 4);
        MONTH_MAP.put("may", 5);
        MONTH_MAP.put("june", 6);
        MONTH_MAP.put("july", 7);
        MONTH_MAP.put("august", 8);
        MONTH_MAP.put("september", 9);
        MONTH_MAP.put("october", 10);
        MONTH_MAP.put("november", 11);
        MONTH_MAP.put("december", 12);
    }
    
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new IllegalArgumentException("Date string cannot be null or empty");
        }
        
        dateString = dateString.trim();
        
        try {
            String[] parts = dateString.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid date format: " + dateString);
            }
            
            String monthName = parts[0].toLowerCase();
            String yearString = parts[1];
            
            Integer month = MONTH_MAP.get(monthName);
            if (month == null) {
                throw new IllegalArgumentException("Invalid month: " + parts[0]);
            }
            
            int year = Integer.parseInt(yearString);
            
            return LocalDate.of(year, month, 1);
            
        } catch (NumberFormatException e) {
            logger.error("Error parsing year from date string: {}", dateString, e);
            throw new IllegalArgumentException("Invalid year in date: " + dateString, e);
        } catch (Exception e) {
            logger.error("Error parsing date string: {}", dateString, e);
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }
}

