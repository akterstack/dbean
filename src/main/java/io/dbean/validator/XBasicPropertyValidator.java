package io.dbean.validator;

public interface XBasicPropertyValidator {

    default boolean nullable(Object value, Boolean context) {
        if(!context)
            return value != null;
        return true;
    }

    default boolean length(String instanceVal, int defaultVal) {
        return instanceVal.length() == defaultVal;
    }

    default boolean minLength(String instanceVal, int defaultVal) {
        return instanceVal.length() >= defaultVal;
    }

    default boolean maxLength(String instanceVal, int defaultVal) {
        return instanceVal.length() <= defaultVal;
    }

    default boolean minValue(Integer instanceVal, int defaultVal) {
        return instanceVal >= defaultVal;
    }

    default boolean maxValue(Integer instanceVal, int defaultVal) {
        return instanceVal <= defaultVal;
    }

    default boolean lengthBetween(String instanceVal , int[] context) throws PropertyValidationException {
        if(context.length != 2)
            return true;
            //throw new PropertyValidationException();
        return minLength(instanceVal, context[0]) && maxLength(instanceVal, context[1]);
    }

    default boolean inBetween(Integer instanceVal, int[] defaultVal) throws PropertyValidationException {
        if(defaultVal.length != 2 || defaultVal[0] > defaultVal[1])
            return true;
            //throw new PropertyValidationException();
        return minValue(instanceVal, defaultVal[0]) && maxValue(instanceVal, defaultVal[1]);
    }
}
