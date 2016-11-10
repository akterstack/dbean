package io.dbean.test.mock;

import io.dbean.DBean;
import io.dbean.Property;
import io.dbean.validator.property.Text;
import io.dbean.validator.property.Username;

import java.util.Date;

public class Person extends DBean<Person> {

    @Property
    private Integer id;
    @Username(maxLength = 10)
    private String username;
    private String firstName;
    private String lastName;
    @Text
    private String bio;
    private Date birthDay;

    @Override
    public String toString() {
        return "id: " + id +
                ", username: " + username;
    }

}
