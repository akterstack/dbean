package io.dbean.test.mock;

import io.dbean.DBean;
import io.dbean.Property;
import io.dbean.test.property.FirstName;
import io.dbean.validator.property.Numeric;
import io.dbean.validator.property.Text;
import io.dbean.validator.property.Username;

import java.util.Date;

public class Person extends DBean<Person> {

    @Property
    private Integer id;
    @Username
    private String username;
    @FirstName
    private String firstName;
    private String lastName;
    @Text(minLength = 20)
    private String bio = "No biography found on this person.";
    @Numeric
    private Integer age;
    private Date birthDay;

    @Override
    public String toString() {
        return "id: " + id +
                ", username: " + username;
    }

}
