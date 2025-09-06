package com.gyarsilalsolanki011.journeymate.validator;

import com.gyarsilalsolanki011.journeymate.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.util.TripDateParser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class TripDateValidator implements ConstraintValidator<ValidTripDates, TripDTO> {

    @Override
    public boolean isValid(TripDTO trip, ConstraintValidatorContext context) {
        if (trip.getStartDate() == null || trip.getEndDate() == null) {
            return true; // @NotNull handles null checks separately
        }

        try {
            LocalDate start = TripDateParser.parseDate(trip.getStartDate().toString(), "startDate");
            LocalDate end = TripDateParser.parseDate(trip.getEndDate().toString(), "endDate");

            return end.isAfter(start);
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }
}


