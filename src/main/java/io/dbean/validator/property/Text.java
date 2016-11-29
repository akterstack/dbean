package io.dbean.validator.property;

import io.dbean.PropertyRule;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PropertyRule
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {
    int minLength() default 2;
    int maxLength() default 4096; //TODO: use as max as possible in java
}
