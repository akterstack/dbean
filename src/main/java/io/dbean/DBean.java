package io.dbean;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class DBean implements Serializable {

    private static Map<Class<? extends DBean>, List<Annotation>> usedPropertyRules = new HashMap<>();
    private static Map<Class<? extends DBean>, List<Annotation>> usedSingletonPropertyRules = new HashMap<>();

    public static void register(Class<? extends DBean> dBeanClass) {
        scanUsageOfAnyPropertyRules(dBeanClass);
    }

    private static void scanUsageOfAnyPropertyRules(Class<? extends DBean> dBeanClass) {
        for(Method getter : getters(dBeanClass)) {
            /* scan getters' property rules */
            scanPropertyRules(getter.getDeclaredAnnotations(), dBeanClass);
            try {
                /* scan fields' property rules */
                scanPropertyRules(getterToProperty(getter, dBeanClass).getDeclaredAnnotations(), dBeanClass);
            } catch(NoSuchFieldException e) {
                //todo: update with logger.debug
                //"No field for getter " + getter.getName() + " found.
                // Skipping scanning property rules for this getter's field."
            }
        }
    }

    private static void scanPropertyRules(Annotation[] annotations, Class<? extends DBean> dBeanClass) {
        List<Annotation> propertyRules = initState(usedPropertyRules, dBeanClass);
        List<Annotation> singletonPropertyRules = initState(usedSingletonPropertyRules, dBeanClass);
        for(Annotation annotation : annotations) {
            if(annotation.annotationType().isAnnotationPresent(PropertyRule.class)) {
                propertyRules.add(annotation);
            } else if(annotation.annotationType().isAnnotationPresent(SingletonPropertyRule.class)) {
                singletonPropertyRules.add(annotation);
            }
        }

    }

    private static List<Annotation> initState(
            Map<Class<? extends DBean>, List<Annotation>> usedRules,
            Class<? extends DBean> dBeanClass) {
        usedRules.computeIfAbsent(dBeanClass, k -> new ArrayList<>());
        return usedRules.get(dBeanClass);
    }

    public boolean validate() {
        for(Method getter : getters(getClass())) {
            validate(getter);
            validate();
        }
        return true;
    }

    private void validate(Method method) {
        try {
            method.getAnnotations();
            validate(getterToProperty(method, getClass()));
        } catch(NoSuchFieldException e) {
            System.out.println(
                    "No matching filed found for getter " + method.getName() +
                            ". So omitting validation for getter field");
        }
    }

    private void validate(Field field) {

    }

    private static Field getterToProperty(Method getter, Class clazz) throws NoSuchFieldException {
        String fieldName = Character.toLowerCase(getter.getName().charAt(3)) + getter.getName().substring(4);
        return clazz.getDeclaredField(fieldName);
    }

    private void doValidate(List<Class<? extends Annotation>> annotations, Object propertyValue) {

    }

    private static List<Method> getters(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods())
                .filter(m ->
                        m.getName().startsWith("get") &&
                                m.getName().length() > "get".length())
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return true;
    }
}
