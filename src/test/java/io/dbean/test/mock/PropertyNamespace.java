package io.dbean.test.mock;

import io.dbean.Namespace;

public class PropertyNamespace extends Namespace {

    public static PropertyNamespace
            name,
            description,
            createdDate,
            updatedDate,
            zid = new PropertyNamespace("ZoneId");

    public PropertyNamespace(String namespace) {
        super(namespace);
    }
}
