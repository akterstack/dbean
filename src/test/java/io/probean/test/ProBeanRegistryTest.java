package io.probean.test;

import io.probean.ProbeanRegistry;
import io.probean.test.mock.PropertyNamespace;
import org.junit.Assert;
import org.junit.Test;

import static io.probean.test.mock.PropertyNamespace.*;

public class ProBeanRegistryTest {

    @Test
    public void testRegisterPropertyNamespace() {
        ProbeanRegistry.registerPropertyNamespace(PropertyNamespace.class);

        Assert.assertEquals(name.toString().equals("name"), true);
        Assert.assertEquals(zid.toString().equals("ZoneId"), true);
    }

}
