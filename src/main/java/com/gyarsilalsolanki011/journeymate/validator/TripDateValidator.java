package com.gyarsilalsolanki011.journeymate.validator;

import com.gyarsilalsolanki011.journeymate.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.util.TripDateParser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class TripDateValidator implements ConstraintValidator<ValidTripDates, TripDTO> {

    @Override
    public boolean isValid(TripDTO tripDto, ConstraintValidatorContext context) {
        if (tripDto.getStartDate() == null || tripDto.getEndDate() == null) {
            return true; // @NotNull handles null checks separately
        }

        LocalDate startDate = TripDateParser.parseDate(tripDto.getStartDate().toString(), "startDate");
        LocalDate endDate = TripDateParser.parseDate(tripDto.getEndDate().toString(), "endDate");

        if (startDate.isAfter(endDate)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Start date must not be after end date")
                    .addPropertyNode("startDate")   // 👈 attach to the field
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}


