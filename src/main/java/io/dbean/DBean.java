package io.dbean;

import io.hackable.Hackable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class DBean<T> implements Serializable, Hackable {

    private Map<Namespace, Object> propertyValuesMap = new HashMap<>();

    public <V> V get(Namespace namespace) {
        return (V)propertyValuesMap.get(namespace);
    }

    public T set(Namespace namespace, Object value) {
        propertyValuesMap.put(namespace, value);
        return (T)this;
    }

}
