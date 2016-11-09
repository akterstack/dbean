package io.dbean.test;

import io.dbean.DBeanRegistry;
import io.dbean.test.mock.PropertyNamespace;
import org.junit.Assert;
import org.junit.Test;

public class ProBeanRegistryTest {

    @Test
    public void testRegisterPropertyNamespace() {
        DBeanRegistry.registerPropertyNamespace(PropertyNamespace.class);

        Assert.assertEquals(name.toString().equals("name"), true);
        Assert.assertEquals(zid.toString().equals("ZoneId"), true);
    }

}
