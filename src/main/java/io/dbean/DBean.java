package io.dbean;

import io.dbean.validator.BasicPropertyValidationRules;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static io.hackable.Hackable.applyFilter;

public abstract class DBean<T> implements Serializable {

    //private Map<Namespace, Object> propertyValuesMap = new HashMap<>();
    private static BasicPropertyValidationRules rules = null;

    public <V> V get(Namespace namespace) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(namespace);
        field.setAccessible(true);
        return applyFilter("get", this.getClass(), (V)field.get(this));
    }

    public <V> T set(Namespace namespace, V value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(namespace);
        field.setAccessible(true);
        field.set(this, applyFilter("set", this.getClass(), value));
        return (T)this;
    }

    protected Field getField(Namespace namespace) throws NoSuchFieldException {
        return this.getClass().getDeclaredField(namespace.toString());
    }

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
