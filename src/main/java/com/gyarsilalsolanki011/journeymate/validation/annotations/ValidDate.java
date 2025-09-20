package com.gyarsilalsolanki011.journeymate.validation.annotations;

import com.gyarsilalsolanki011.journeymate.validation.validators.DatesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatesValidator.class)
public @interface ValidDate {
    String message() default "Invalid date format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
