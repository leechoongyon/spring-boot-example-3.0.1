package com.example.domain.item.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

@Slf4j
public class ItemConstraintValidator implements ConstraintValidator<ItemConstraint, Object> {

    private Expression conditionExpression;
    private String compareValue;
    private String fieldName;

    @Override
    public void initialize(ItemConstraint itemConstraint) {
        String condition = itemConstraint.condition();
        ExpressionParser parser = new SpelExpressionParser();
        if (StringUtils.hasText(condition)) {
            conditionExpression = parser.parseExpression(condition);
        }

        this.compareValue = StringUtils.hasText(itemConstraint.compareValue()) ?
                itemConstraint.compareValue() : "";
        this.fieldName = StringUtils.hasText(itemConstraint.field()) ? itemConstraint.field() : "";
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        EvaluationContext evaluationContext = createEvaluationContext(object);
        if (ObjectUtils.isEmpty(object) || !isConditionPassing(evaluationContext)) {
            return true;
        }

        log.info("pass condition... start validate constraint...");

        return getFieldValue(object).equals(compareValue);
    }

    private Object getFieldValue(Object value) {
        try {
            Field field = ReflectionUtils.findField(value.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


    private boolean isConditionPassing(EvaluationContext evaluationContext) {
        if (ObjectUtils.isEmpty(conditionExpression)) {
            return true;
        }

        Boolean isCOnditionPassing = conditionExpression.getValue(evaluationContext, Boolean.class);
        return ObjectUtils.isEmpty(isCOnditionPassing) ? false : isCOnditionPassing;
    }


    private StandardEvaluationContext createEvaluationContext(Object value) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setRootObject(value);
        return context;
    }
}
