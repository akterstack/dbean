package io.dbean.test.registry

import io.dbean.DBean
import io.dbean.DBeanRegistry
import io.dbean.validator.BasicPropertyValidator
import org.junit.Test

import static org.junit.Assert.*;


class DBeanRegistryTests {

    @Test
    void scanAndRegisterPropertyValidator() {
        DBeanRegistry.scanAndRegisterPropertyValidators(new BasicPropertyValidator())
    }

    @Test
    void scanAndRegisterDBeanComponents() {
        List<Class<? extends DBean>> dBeanClassList = DBeanRegistry.registerDBeanClasses("io.dbean.test.dbeans")
        assertEquals("all dbeans loaded", 1, dBeanClassList.size())
    }

}
