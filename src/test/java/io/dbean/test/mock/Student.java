package io.dbean.test.mock;

import io.dbean.DBean;
import io.dbean.PropertyRule;

public class Student extends DBean<Student> {

    @PropertyRule
    private String firstName;

}
