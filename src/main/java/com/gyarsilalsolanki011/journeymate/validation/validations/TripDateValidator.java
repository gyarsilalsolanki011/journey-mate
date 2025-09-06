package com.gyarsilalsolanki011.journeymate.validation.validations;

import com.gyarsilalsolanki011.journeymate.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.util.TripDateParser;
import com.gyarsilalsolanki011.journeymate.validation.validator.ValidTripDates;
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

        if (endDate.isBefore(startDate)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End date can not before start date")
                    .addPropertyNode("endDate")   // 👈 attach to the field
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}


