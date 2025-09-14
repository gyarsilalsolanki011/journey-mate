package com.gyarsilalsolanki011.journeymate.validation.annotations;

import com.gyarsilalsolanki011.journeymate.validation.validators.TripStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TripStatusValidator.class)
public @interface ValidTripStatus {
    String message() default "Invalid trip status value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

