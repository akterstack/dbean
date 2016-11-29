package io.dbean.test.property;

import io.dbean.PropertyRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PropertyRule
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstName {
    int minLen() default 2;
    int maxLen() default 50; //TODO: use as max as possible in java
}
