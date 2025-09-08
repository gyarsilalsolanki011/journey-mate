package com.gyarsilalsolanki011.journeymate.validation;

import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.validation.validator.ValidTripStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TripStatusValidator implements ConstraintValidator<ValidTripStatus, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // let @NotBlank handle empties if needed
        }

        try {
            TripStatus.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid trip status: " + value)
                    .addConstraintViolation();
            return false;
        }
    }
}

