package com.example.domain.member.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberIdConstration {
    String message() default "Invalid member id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
