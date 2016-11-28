package io.dbean;

import java.util.HashMap;
import java.util.Map;

public abstract class DBeanGS<T> extends DBean<T> {

    private Map<Namespace, Object> propertyValueMap = new HashMap<>();

    public <V> V get(Namespace namespace) {
        return (V)propertyValueMap.get(namespace);
    }

    public  <V> T set(Namespace namespace, V value) {
        propertyValueMap.put(namespace, value);
        return (T)this;
    }
}
