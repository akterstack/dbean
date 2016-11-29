package io.dbean.validator;

import io.dbean.DBeanException;

public class PropertyValidationException extends DBeanException {

    private Integer code;
    private String message;
    private PropertyValidationContext propertyValidationContext;

    public PropertyValidationException(Throwable throwable) {
        super(throwable);
    }

    public PropertyValidationException(Integer code,
                                       PropertyValidationContext propertyValidationContext) {
        super(null);
        this.code = code;
        this.propertyValidationContext = propertyValidationContext;
    }

    public PropertyValidationException(Integer code, String message,
                                       PropertyValidationContext propertyValidationContext) {
        this(code, propertyValidationContext);
        this.message = message;
    }

    public PropertyValidationException(Integer code, Throwable throwable,
                                       PropertyValidationContext propertyValidationContext) {
        super(throwable);
        this.code = code;
        this.propertyValidationContext = propertyValidationContext;
    }

    public PropertyValidationException(
            Integer code, String message, Throwable throwable,
            PropertyValidationContext propertyValidationContext) {
        this(code, throwable, propertyValidationContext);
        this.message = message;
    }

}
