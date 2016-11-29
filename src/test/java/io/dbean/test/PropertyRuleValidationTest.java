package io.dbean.test;

import io.dbean.NamespaceRegistry;
import io.dbean.test.mock.PropertyNamespace;
import org.junit.Test;

import java.io.IOException;

public class PropertyRuleValidationTest {

    @Test
    public void testPropertyValidation() throws NoSuchFieldException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {

        NamespaceRegistry.registerProperties("io.dbean.test.property");
        NamespaceRegistry.registerPropertyNamespace(PropertyNamespace.class);
        /*DBean.setRules(ValidationRules.class);

        Person person = new Person();

        person.set(id, 1);
        person.set(username, "jRoadie");
        person.set(firstName, "Akter");
        person.set(age, 30);
        Integer _id = person.get(id);
        String uname = person.get(username);
        System.out.println(_id + 10 + ":" + person.get(firstName));
        System.out.println("Person obj is valid: " + person.validate());*/
    }

}
