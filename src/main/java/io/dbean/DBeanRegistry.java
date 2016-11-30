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

    private static List<DBean> registeredDBeans = new LinkedList<>();
    private static Map<String, PropertyValidator> registeredMapOfValidators = new HashMap<>();

    private DBeanRegistry() {
    }

    public static void initialize() throws DBeanException {
        scanAndRegisterPropertyValidators(new BasicPropertyValidator());
    }

    public static void scanAndRegisterPropertyRules(String packageName) {

    }

    public static void registerPropertyRules(Class... annotationClasses) {

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

    public static List<DBean> registerDBeans(String packageName)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Class<DBean>> dbeanClasses = new ArrayList<>();
        for(Class<?> dc : DBeanClassLoader.scan(packageName).loadAll()) {
            if(DBean.class.isAssignableFrom(dc))
                dbeanClasses.add((Class<DBean>)dc);
        }
        return registerDBeans(dbeanClasses);
    }

    public static List<DBean> registerDBeans(Class<DBean>... dBeanClasses)
            throws InstantiationException, IllegalAccessException {
        return registerDBeans(Arrays.asList(dBeanClasses));
    }

    public static List<DBean> registerDBeans(List<Class<DBean>> dBeanClasses)
            throws IllegalAccessException, InstantiationException {
        List<DBean> dBeans = new LinkedList<>();
        for(Class<? extends DBean> dBeanClass : dBeanClasses) {
            DBean dBean = dBeanClass.newInstance();
            dBean.initialize();
            dBeans.add(dBean);
        }
        registeredDBeans.addAll(dBeans);
        return dBeans;
    }

    public static List<DBean> getRegisteredDBeans() {
        return Collections.unmodifiableList(registeredDBeans);
    }

    public static Map<String, PropertyValidator> getRegisteredMapOfValidators() {
        return Collections.unmodifiableMap(registeredMapOfValidators);
    }

}
