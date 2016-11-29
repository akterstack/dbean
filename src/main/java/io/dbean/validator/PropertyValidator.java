package io.dbean.validator;

public interface PropertyValidator<V, C> {

    void validate(String propertyName, V value, C context)
            throws PropertyValidationException;

}
