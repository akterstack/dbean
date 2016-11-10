package io.dbean;

import io.hackable.Hackable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class DBean<T> implements Serializable, Hackable {

    private Map<Namespace, Object> propertyValuesMap = new HashMap<>();

    public <V> V get(Namespace namespace) {
        return applyFilter("get", this.getClass(), (V)propertyValuesMap.get(namespace));
    }

    public <V> T set(Namespace namespace, V value) {
        propertyValuesMap.put(namespace, applyFilter("set", this.getClass(), value));
        return (T)this;
    }

}
