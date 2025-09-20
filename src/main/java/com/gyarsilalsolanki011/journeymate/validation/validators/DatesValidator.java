package com.gyarsilalsolanki011.journeymate.validation.validators;

import com.gyarsilalsolanki011.journeymate.exception.TripServiceException;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatesValidator implements ConstraintValidator<ValidDate, String> {
    @Override
    public boolean isValid(String localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null){
            return true;
        }
        try {
            LocalDate date = LocalDate.parse(localDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            throw new TripServiceException("Invalid date format "+localDate+"!, Expected yyyy-MM-dd", e);
        }
    }
}
