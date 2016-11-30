package io.dbean.test

import io.dbean.DBean
import io.dbean.DBeanRegistry
import io.dbean.test.dbeans.MockDBean
import org.junit.Before
import org.junit.Test


class DBeanTests {

    private List<DBean> dBeanList

    @Before
    void setUp() throws Exception {
        dBeanList = DBeanRegistry.registerDBeanClasses("io.dbean.test.dbeans")
    }

    @Test
    void register() throws Exception {
        DBean.register(MockDBean)
    }

    @Test
    void validate() throws Exception {

    }
}
