package com.gyarsilalsolanki011.journeymate.validation.annotations;

import com.gyarsilalsolanki011.journeymate.validation.validators.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ValidDate {
    String message() default "Invalid date format. Expected yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
