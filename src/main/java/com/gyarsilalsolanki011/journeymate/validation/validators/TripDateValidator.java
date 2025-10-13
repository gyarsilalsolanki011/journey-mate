package com.gyarsilalsolanki011.journeymate.validation.validators;

import com.gyarsilalsolanki011.journeymate.model.dto.TripDTO;
import com.gyarsilalsolanki011.journeymate.validation.annotations.ValidTripDates;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TripDateValidator implements ConstraintValidator<ValidTripDates, TripDTO> {

    @Override
    public boolean isValid(TripDTO tripDto, ConstraintValidatorContext context) {
        if (tripDto.getStartDate() == null || tripDto.getEndDate() == null) {
            return true; // @NotNull handles null checks separately
        }

        LocalDate startDate = LocalDate.parse(tripDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(tripDto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE);

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


