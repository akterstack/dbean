package io.dbean.validator;

public class PropertyValidationException extends Exception {

    private Integer code;
    private String message;
    private PropertyValidationContext propertyValidationContext;

    public PropertyValidationException(
            Integer code, PropertyValidationContext propertyValidationContext) {
        this.code = code;
        this.propertyValidationContext = propertyValidationContext;
    }

    public PropertyValidationException(
            Integer code, String message, PropertyValidationContext propertyValidationContext) {
        this(code, propertyValidationContext);
        this.message = message;
    }

}
