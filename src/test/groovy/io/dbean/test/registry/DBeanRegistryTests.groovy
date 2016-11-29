package io.dbean.test.registry

import io.dbean.DBeanRegistry
import io.dbean.validator.BasicPropertyValidator
import org.junit.Test


class DBeanRegistryTests {

    @Test
    void scanAndRegisterPropertyValidator() {
        DBeanRegistry.scanAndRegisterPropertyValidator(new BasicPropertyValidator())
    }

}
