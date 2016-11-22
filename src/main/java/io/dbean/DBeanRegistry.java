package io.dbean;

import java.util.*;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import io.dbean.validator.PropertyValidator;
import java.lang.reflect.InvocationTargetException;

public class DBeanRegistry {

    private static final int PUBLIC_STATIC_MODIFIER_VALUE = 9;
    private static Map<Class, String> propertyMap =  new HashMap<Class, String>();

    static {
        try {
            registerDefaultProps();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBeanRegistry() {}

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

    public static boolean registerProperties(String propertyPackageName) throws ClassNotFoundException, IOException {
        return loadClass(propertyPackageName);
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
        loadClass("io.dbean.validator.property");
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

    private static boolean loadClass(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<String> classNames = new ArrayList<>();
        for (File directory : dirs) {
            classNames.addAll(findClasses(directory, packageName));
        }
        for(String className: classNames) {
            registerProperty(classLoader.loadClass(className));
        }
        return true;
    }

    private static List<String> findClasses(File directory, String packageName) {
        List<String> classNames = new ArrayList<>();
        if (!directory.exists()) {
            return classNames;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classNames.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classNames.add(packageName + '.' + file.getName().replace(".class", ""));
            }
        }
        return classNames;
    }

    public static PropertyValidator propertyValidator() {
        return new PropertyValidator(); //TODO: register it on initialize
    }
}
