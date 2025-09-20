package com.gyarsilalsolanki011.journeymate.validation.annotations;

import com.gyarsilalsolanki011.journeymate.validation.validators.TripDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})  // Class-level annotation
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TripDateValidator.class)
public @interface ValidTripDates {
    String message() default "End date must be after start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
