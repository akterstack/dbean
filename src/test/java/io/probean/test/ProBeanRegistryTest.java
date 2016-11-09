package io.probean.test;

import io.probean.ProbeanRegistry;
import org.junit.Assert;
import org.junit.Test;

import static io.probean.test.PropertyNamespace.*;

public class ProBeanRegistryTest {

    @Test
    public void testRegisterPropertyNamespace() {
        ProbeanRegistry.registerPropertyNamespace(PropertyNamespace.class);

        Assert.assertEquals(name.value().equals("name"), true);
        Assert.assertEquals(zid.value().equals("ZoneId"), true);
    }

}
