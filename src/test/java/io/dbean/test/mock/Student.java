package io.dbean.test.mock;

import io.dbean.DBean;
import io.dbean.Property;

public class Student extends DBean<Student> {

    @Property
    private String firstName;

}
