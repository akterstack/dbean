package io.dbean.test.registry

import io.dbean.DBean
import io.dbean.DBeanRegistry
import io.dbean.validator.BasicPropertyValidator
import org.junit.Test


class DBeanRegistryTests {

    @Test
    void scanAndRegisterPropertyValidator() {
        DBeanRegistry.scanAndRegisterPropertyValidators(new BasicPropertyValidator())
    }

    @Test
    void scanAndRegisterDBeanComponents() {
        List<DBean> dBeanList = DBeanRegistry.registerDBeans("io.dbean.test.dbeans")
        dBeanList.each { println(it) }
    }

}
