package com.example.domain.member.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class MemberIdValidator implements ConstraintValidator<MemberIdConstration, String> {

    private final String ALPHABET_PATTERN = "^[a-zA-Z]*$";

    /**
     * 알파벳만 존재해야 함
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if ( !Pattern.matches(ALPHABET_PATTERN, value)) {
            log.warn("value contains non alphabet. value : {}", value);
            return false;
        }
        return true;
    }
}
