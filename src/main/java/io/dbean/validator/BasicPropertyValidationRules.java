package io.dbean.validator;

public interface BasicPropertyValidationRules {

    default boolean nullable(Object value, Boolean context) {
        if(!context)
            return value != null;
        return true;
    }

    default boolean length(String value, Integer context) {
        return value.length() == context;
    }

    default boolean minLength(String value, Integer context) {
        return value.length() >= context;
    }

    default boolean maxLength(String value, Integer context) {
        return value.length() <= context;
    }

    default boolean lengthBetween(String value , Integer[] context) throws PropertyValidationException {
        if(context.length != 2)
            throw new PropertyValidationException();
        return minLength(value, context[0]) && maxLength(value, context[1]);
    }
}
