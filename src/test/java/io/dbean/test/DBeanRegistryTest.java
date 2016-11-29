package io.dbean.test;

import io.dbean.NamespaceRegistry;
import io.dbean.test.mock.PropertyNamespace;
import org.junit.Assert;
import org.junit.Test;

import static io.dbean.test.mock.PropertyNamespace.*;

public class DBeanRegistryTest {

    @Test
    public void testRegisterPropertyNamespace() {
        NamespaceRegistry.registerPropertyNamespace(PropertyNamespace.class);

        Assert.assertEquals(name.toString().equals("name"), true);
        Assert.assertEquals(zid.toString().equals("ZoneId"), true);
    }

}
