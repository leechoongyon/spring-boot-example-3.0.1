package com.example.domain.item.validator;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ItemConstraintValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "ItemId, ItemDesc, true",   // pass condition --> valid compareValue
            "ItemId, ItemDesc22, false" // pass condition --> invalid compareValue

    })
    @DisplayName("ItemCommandRequest 로 테스트")
    void testItemConstraintValidatorTest01(String id, String desc, boolean expected)
            throws NoSuchFieldException, IllegalAccessException {
        var request = ItemCommandRequest.builder()
                .id(id)
                .desc(desc)
                .build();

        var validator = new ItemConstraintValidator();

        var itemConstraint = request.getClass().getAnnotation(ItemConstraint.class);

        validator.initialize(itemConstraint);

        Assertions.assertEquals(expected, validator.isValid(request, getConstraintValidatorContext()));
    }

    @ParameterizedTest
    @CsvSource({
            "ItemId, Item123, true, false",   // pass condition --> invalid compareValue
            "ItemId, Item234, false, true"  // not pass condition

    })
    @DisplayName("ItemCommandRequest2 로 테스트")
    void testItemConstraintValidatorTest02(String id, String desc, boolean next, boolean expected)
            throws NoSuchFieldException, IllegalAccessException {
        var request = ItemCommandRequest2.builder()
                .id(id)
                .desc(desc)
                .next(next)
                .build();

        var validator = new ItemConstraintValidator();

        var itemConstraint = request.getClass().getAnnotation(ItemConstraint.class);

        validator.initialize(itemConstraint);

        Assertions.assertEquals(expected, validator.isValid(request, getConstraintValidatorContext()));
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ItemConstraint(compareValue = "ItemDesc", field = "desc", condition = "id == 'ItemId'")
    public static class ItemCommandRequest {
        private String id;
        private String desc;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ItemConstraint(compareValue = "ItemDesc", field = "desc", condition = "id == 'ItemId' && next")
    public static class ItemCommandRequest2 {
        private String id;
        private String desc;
        private boolean next;
    }


    private ConstraintValidatorContext getConstraintValidatorContext() {
        return new ConstraintValidatorContext() {
            @Override
            public void disableDefaultConstraintViolation() {

            }

            @Override
            public String getDefaultConstraintMessageTemplate() {
                return null;
            }

            @Override
            public ClockProvider getClockProvider() {
                return null;
            }

            @Override
            public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> type) {
                return null;
            }
        };
    }
}
