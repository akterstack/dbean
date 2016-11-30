package io.dbean;

import io.dbean.validator.BasicPropertyValidator;
import io.dbean.validator.PropertyValidator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DBeanRegistry {

    private static List<Class<? extends DBean>> registeredDBeanClasses = new ArrayList<>();
    private static Map<String, PropertyValidator> registeredMapOfValidators = new HashMap<>();

    private DBeanRegistry() {
    }

    public static void initialize()
            throws DBeanException, IOException, ClassNotFoundException {
        scanAndRegisterPropertyValidators(new BasicPropertyValidator());
    }

    public static void scanAndRegisterPropertyValidators(Object... validators) throws DBeanException {
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
    }

    public static void registerPropertyValidator(
            String name, PropertyValidator propertyValidator) throws DBeanException {
        registeredMapOfValidators.put(name, propertyValidator);
    }

    public static List<Class<? extends DBean>> registerDBeanClasses(String packageName)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Class<? extends DBean>> dbeanClasses = new ArrayList<>();
        for(Class<?> dc : DBeanClassLoader.scan(packageName).loadAll()) {
            if(DBean.class.isAssignableFrom(dc))
                dbeanClasses.add((Class<DBean>)dc);
        }
        return registerDBeanClasses(dbeanClasses);
    }

    public static List<Class<? extends DBean>> registerDBeanClasses(Class<? extends DBean>... dBeanClasses)
            throws InstantiationException, IllegalAccessException {
        return registerDBeanClasses(Arrays.asList(dBeanClasses));
    }

    public static List<Class<? extends DBean>> registerDBeanClasses(List<Class<? extends DBean>> dBeanClasses)
            throws IllegalAccessException, InstantiationException {
        List<Class<? extends DBean>> loaded = new LinkedList<>();
        for(Class<? extends DBean> dBeanClass : dBeanClasses) {
            DBean.register(dBeanClass);
            loaded.add(dBeanClass);
        }
        registeredDBeanClasses.addAll(loaded);
        return loaded;
    }

    public static List<Class<? extends DBean>> getRegisteredDBeanClasses() {
        return Collections.unmodifiableList(registeredDBeanClasses);
    }

    public static Map<String, PropertyValidator> getRegisteredMapOfValidators() {
        return Collections.unmodifiableMap(registeredMapOfValidators);
    }

}
