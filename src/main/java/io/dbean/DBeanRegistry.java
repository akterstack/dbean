package io.dbean;

import io.dbean.validator.BasicPropertyValidator;
import io.dbean.validator.PropertyValidator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DBeanRegistry {

    private static List<Class> validatorClasses = new LinkedList<>();
    private static Map<String, PropertyValidator> validatorMap = new HashMap<>();

    private DBeanRegistry() {}

    public static void initialize() throws DBeanException {
        scanAndRegisterPropertyValidator(new BasicPropertyValidator());
    }

    public static void scanAndRegisterPropertyRules(String packageName) {

    }

    public static void registerPropertyRule(Class... annotationClasses) {

    }

    public static void scanAndRegisterPropertyValidator(Object... validators) throws DBeanException {
        for(Object validator : validators) {
            /* scan fields and register validators */
            Field[] fields = validator.getClass().getDeclaredFields();
            for(Field field : fields) {
                if(field.getType().isAssignableFrom(PropertyValidator.class)) {
                    try {
                        registerPropertyValidator(field.getName(), (PropertyValidator)field.get(validator));
                    } catch(IllegalAccessException e) {
                        throw new DBeanException(e);
                    }
                }
            }
            /* scan methods and register validators */
            Method[] methods = validator.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(method.getReturnType().isAssignableFrom(PropertyValidator.class)) {
                    try {
                        registerPropertyValidator(method.getName(), (PropertyValidator)method.invoke(validator));
                    } catch(IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        throw new DBeanException(e);
                    }
                }
            }
        }
        validatorMap.forEach((key, val) -> {
            System.out.println(key);
            System.out.println(val);
        });
    }

    public static void registerPropertyValidator(String name, PropertyValidator propertyValidator) throws DBeanException {
        validatorMap.put(name, propertyValidator);
    }

}
