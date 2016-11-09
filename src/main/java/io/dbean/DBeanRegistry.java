package io.dbean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DBeanRegistry {

    private static final int PUBLIC_STATIC_MODIFIER_VALUE = 9;

    public static <T extends Namespace> void registerPropertyNamespace(Class<T> namespaceClass) {
        Field[] fields = namespaceClass.getDeclaredFields();
        for(Field field : fields) {
            try {
                if(isNullValued(field)
                        && isPublicStatic(field)
                        && isNamespaceType(field, namespaceClass)) {

                    Constructor<T> constructor = namespaceClass.getConstructor(String.class);
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

    private static boolean isNullValued(Field field) throws IllegalAccessException {
        return field.get(null) == null;
    }

    private static boolean isPublicStatic(Field field) {
        return field.getModifiers() == PUBLIC_STATIC_MODIFIER_VALUE;
    }

    private static boolean isNamespaceType(Field field, Class<?> assignableClass) {
        return assignableClass.isAssignableFrom(field.getType());
    }

}