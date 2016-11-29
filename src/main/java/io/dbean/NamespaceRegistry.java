package io.dbean;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import io.dbean.validator.PropertyValidator;
import java.lang.reflect.InvocationTargetException;

public final class NamespaceRegistry {

    private static final int PUBLIC_STATIC_MODIFIER_VALUE = 9;
    private static Map<Class, String> propertyMap =  new HashMap<>();

    private NamespaceRegistry() {}



    static {
        try {
            registerDefaultProps();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void registerPropertyNamespace(Class<? extends Namespace> namespaceClass) {
        Field[] fields = namespaceClass.getDeclaredFields();
        for(Field field : fields) {
            try {
                if(isNullValued(field)
                        && isPublicStatic(field)
                        && isNamespaceType(field, namespaceClass)) {

                    Constructor<?> constructor = namespaceClass.getConstructor(String.class);
                    field.set(null, constructor.newInstance(field.getName()));
                }
            } catch(NoSuchMethodException
                    | IllegalAccessException
                    | InstantiationException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerProperties(String propertyPackageName) throws ClassNotFoundException, IOException {
        DBeanClassLoader.findAllClassNames(propertyPackageName);
    }

    public static boolean registerProperties(Class... classes) {
        for(Class aClass : classes) {
            registerProperty(aClass);
        }
        return true;
    }

    public static Map getPropertyMap() {
        return propertyMap;
    }

    private static boolean registerProperty(Class aClass) {
        propertyMap.put(aClass, aClass.getName());
        return true;
    }

    private static void registerDefaultProps() throws ClassNotFoundException, IOException {
        DBeanClassLoader.loadAllClasses("io.dbean.validator.property");
    }

    private static boolean isNullValued(Field field) throws IllegalAccessException {
        return field.get(null) == null;
    }

    private static boolean isPublicStatic(Field field) {
        return field.getModifiers() == PUBLIC_STATIC_MODIFIER_VALUE;
    }

    private static boolean isNamespaceType(Field field, Class<?> assignableClass) {
        return assignableClass.isAssignableFrom(field.getType());
    }

    public static PropertyValidator propertyValidator() {
        return new PropertyValidator(); //TODO: register it on initialize
    }
}
