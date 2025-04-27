package _base;

import core.Authorization;

import java.util.HashMap;
import java.util.Map;

public class ApiBaseStep {
    protected Map<Class<?>, Object> objectHierarchy;
    protected Map<String, Object> dataSaver;

    protected Authorization auth;

    protected ApiBaseStep() {
        objectHierarchy = new HashMap<Class<?>, Object>();
        dataSaver = new HashMap<>();
        auth = new Authorization();
    }

    protected <T> void save(Class<T> className, T instance) {
        dataSaver.put(className.getCanonicalName(), instance);
    }

    protected <T> T load(Class<T> className) {
        if (dataSaver.get(className.getCanonicalName()) == null) {
            throw new RuntimeException(String.format("Data of %s class has NOT saved yet", className.getCanonicalName()));
        }
        return (T) dataSaver.get(className.getCanonicalName());
    }

    protected <T extends ApiAction> T use(Class<T> action) {
        T objectInstance = null;
        try {
            if (objectHierarchy.get(action) == null) {
                objectInstance = action.getDeclaredConstructor(Authorization.class).newInstance(auth);
                objectHierarchy.put(action, objectInstance);
            } else {
                objectInstance = action.cast(objectHierarchy.get(action));
            }
        } catch (Exception e) {
            throw new RuntimeException("The action is NOT able to open");
        }
        return objectInstance;
    }
}