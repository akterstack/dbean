package io.dbean.test.property;

import io.dbean.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Property
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstName {
    int minLen() default 2;
    int maxLen() default 50; //TODO: use as max as possible in java
}
