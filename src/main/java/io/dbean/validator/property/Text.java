package io.dbean.validator.property;

import io.dbean.Property;

@Property
public @interface Text {
    int minLength() default 2;
    int maxLength() default 4096; //TODO: use as max as possible in java
}
