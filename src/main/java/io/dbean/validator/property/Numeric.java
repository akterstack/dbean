package io.dbean.validator.property;

import io.dbean.PropertyRule;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PropertyRule
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Numeric {
    int minValue() default 10;
    int maxValue() default 4096; //TODO: use as max as possible in java
    int[] inBetween() default {10, 4096};
}