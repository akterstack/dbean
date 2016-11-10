package io.dbean.test;

import io.dbean.DBeanRegistry;
import io.dbean.test.mock.Person;
import io.dbean.test.mock.PropertyNamespace;
import org.junit.Test;

import static io.dbean.test.mock.PropertyNamespace.*;

public class PropertyValidationTest {

    @Test
    public void testPropertyValidation() throws NoSuchFieldException, IllegalAccessException {
        DBeanRegistry.registerPropertyNamespace(PropertyNamespace.class);

        Person person = new Person();
        person.set(id, 1);
        person.set(username, "jRoadie");
        System.out.println(person.toString());
        person.validate();
    }

}
