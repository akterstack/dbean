package io.dbean.test.dbeans

import io.dbean.DBean
import io.dbean.validator.rule.Min


class MockDBean extends DBean {

    @Min(0)
    Long id

    String name
    String username
    String password
    String description
    Date birthday

}
