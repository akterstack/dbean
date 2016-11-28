package io.dbean;

import io.dbean.validator.PropertyValidator;
import io.dbean.validator.property.Username;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class DBean<T> implements Serializable {

    public boolean validate() {
        PropertyValidator validator = DBeanRegistry.propertyValidator();
        Class<? extends DBean> dbeanClass = this.getClass();
        Field[] fields = dbeanClass.getDeclaredFields();
        for(Field field : fields) {
            Username annotation = field.getAnnotation(Username.class);
            if(annotation != null)
                System.out.println(annotation.maxLength());
        }

        return false;
    }

    public boolean isEmpty() {
        return true;
    }
}
