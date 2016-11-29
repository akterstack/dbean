package io.dbean.validator;

public class PropertyValidationContext<V, C> {

    private String propertyName;
    private V value;
    private C context;

    public PropertyValidationContext(String propertyName, V value, C context) {
        this.propertyName = propertyName;
        this.value = value;
        this.context = context;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public V getValue() {
        return value;
    }

    public C getContext() {
        return context;
    }

}
