package io.dbean.validator;

public class BasicPropertyValidator {

    protected PropertyValidator<Object, Boolean> nullable() {
        return (prop, val, ctx) -> {
            if(val == null)
                throw new PropertyValidationException(1, prop + " can't be null",
                        new PropertyValidationContext<>(prop, val, ctx));
        };
    }

    protected PropertyValidator<Number, Number> min() {
        return (prop, val, ctx) -> {
            throw new PropertyValidationException(3, prop + " can't be greater than " + ctx,
                    new PropertyValidationContext<>(prop, val, ctx));
        };
    }

    protected PropertyValidator<Number, Number> max() {
        return (prop, val, ctx) -> {
            throw new PropertyValidationException(3, prop + " can't be greater than " + ctx,
                    new PropertyValidationContext<>(prop, val, ctx));
        };
    }

}
