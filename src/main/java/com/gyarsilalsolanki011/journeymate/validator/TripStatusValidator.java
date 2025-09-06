package com.gyarsilalsolanki011.journeymate.validator;

import com.gyarsilalsolanki011.journeymate.util.TripStatusParser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TripStatusValidator implements ConstraintValidator<ValidTripStatus, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            TripStatusParser.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }
}

