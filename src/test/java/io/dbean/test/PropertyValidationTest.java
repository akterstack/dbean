package io.dbean.test;

import io.dbean.DBean;
import io.dbean.DBeanRegistry;
import io.dbean.test.mock.Person;
import io.dbean.test.mock.PropertyNamespace;
import org.junit.Test;

import java.io.IOException;

import static io.dbean.test.mock.PropertyNamespace.*;

public class PropertyValidationTest {

    @Test
    public void testPropertyValidation() throws NoSuchFieldException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {

        DBeanRegistry.registerProperties("io.dbean.test.property");
        DBeanRegistry.registerPropertyNamespace(PropertyNamespace.class);
        DBean.setRules(ValidationRules.class);

        Person person = new Person();

        person.set(id, 1);
        person.set(username, "jRoadie");
        person.set(firstName, "Akter");
        person.set(age, 30);
        Integer _id = person.get(id);
        String uname = person.get(username);
        System.out.println(_id + 10 + ":" + person.get(firstName));
        System.out.println("Person obj is valid: " + person.validate());
    }

}
