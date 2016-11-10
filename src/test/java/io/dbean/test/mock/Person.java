package io.dbean.test.mock;

import io.dbean.DBean;

import java.util.Date;

public class Person extends DBean<Person> {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDay;

}
