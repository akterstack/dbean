package io.dbean.test.mock;

import io.dbean.Namespace;

public class PropertyNamespace extends Namespace {

    public static PropertyNamespace
            createdDate,
            description,
            id,
            age,
            name,
            bio,
            firstName,
            lastName,
            updatedDate,
            username,
            zid = new PropertyNamespace("ZoneId");

    public PropertyNamespace(String namespace) {
        super(namespace);
    }
}
