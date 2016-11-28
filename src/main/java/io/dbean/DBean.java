package io.dbean;

import io.dbean.validator.PropertyValidator;
import io.dbean.validator.property.Username;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class DBean<T> implements Serializable {

    public static boolean setRules(Class<? extends BasicPropertyValidationRules> classRules) throws InstantiationException, IllegalAccessException {
        if(rules == null) {
            rules = classRules.newInstance();
        }
        return true;
    }

    public boolean validate() {
        DBean context = this;
        Annotation[] annotations;
        Map annotationMap = DBeanRegistry.getPropertyMap();
        Field[] fields = context.getClass().getDeclaredFields();
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                annotations = field.getAnnotations();
                for(Annotation annotation : annotations) {
                    if(annotationMap.containsKey(annotation.annotationType())) {
                        Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
                        for(Method method : declaredMethods) {
                            if(!(boolean)
                                    rules
                                    .getClass()
                                    .getMethod(method.getName(), field.getType(), method.getReturnType())
                                    .invoke(rules, field.get(context), method.invoke(annotation)))
                                return false;
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return false;
    }
}
