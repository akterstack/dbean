package io.dbean.validator;

public interface PropertyValidationRule<P, C> {

    boolean validate(P property, C context) throws PropertyValidationException;

}
