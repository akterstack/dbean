package io.dbean.validator.property;

import io.dbean.Property;

@Property
public @interface Username {
    int minLength() default 2;
    int maxLength() default 64;
}
