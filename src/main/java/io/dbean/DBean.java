package io.dbean;

import io.dbean.validator.PropertyValidator;
import io.hackable.Hackable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class DBean<T> implements Serializable, Hackable {

    //private Map<Namespace, Object> propertyValuesMap = new HashMap<>();

    public <V> V get(Namespace namespace) throws NoSuchFieldException, IllegalAccessException {
        return applyFilter("get", this.getClass(), (V)getField(namespace).get(namespace));
    }

    public <V> T set(Namespace namespace, V value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(namespace);
        field.setAccessible(true);
        field.set(this, applyFilter("set", this.getClass(), value));
        return (T)this;
    }

    protected Field getField(Namespace namespace) throws NoSuchFieldException {
        return this.getClass().getDeclaredField(namespace.toString());
    }

    public boolean validate() {
        PropertyValidator validator = DBeanRegistry.propertyValidator();

        return false;
    }

    public boolean isEmpty() {
        return false;
    }
}
