package com.gyarsilalsolanki011.journeymate.validation.validators;

import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private static final DateTimeFormatter FLEXIBLE_FORMATTER =
            new DateTimeFormatterBuilder()
                    .parseStrict()
                    .appendPattern("yyyy-MM-dd")
                    .toFormatter();

    @Override
    public boolean isValid(String localDate, ConstraintValidatorContext context) {
        if (localDate == null) {
            return true; // let @NotNull handle null check
        }
        try {
            LocalDate.parse(localDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            // ✅ Customize the error message for the response
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Invalid date format: " + localDate + ". Expected format: yyyy-MM-dd"
            ).addConstraintViolation();
            return false;
        }
    }

}
