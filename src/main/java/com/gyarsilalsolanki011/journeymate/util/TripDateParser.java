package com.gyarsilalsolanki011.journeymate.util;

import com.gyarsilalsolanki011.journeymate.exception.TripServiceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TripDateParser {
    public static LocalDate parseDate(String dateStr, String fieldName) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new TripServiceException("Invalid date format for " + fieldName + ". Expected yyyy-MM-dd", e);
        }
    }
}
