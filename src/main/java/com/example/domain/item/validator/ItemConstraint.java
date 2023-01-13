package com.example.domain.item.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@Constraint(validatedBy = ItemConstraintValidator.class)
public @interface ItemConstraint {
    String message() default "Invalid...";

    String compareValue();

    String field();

    String condition();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
