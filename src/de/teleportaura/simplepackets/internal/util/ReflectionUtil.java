package de.teleportaura.simplepackets.internal.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, Class<?> objectClazz, String field, Class<T> valueClazz){
        if(objectClazz.isInstance(object)){
            try {
                Field f = objectClazz.getDeclaredField(field);
                if(!f.isAccessible()) f.setAccessible(true);
                if(f.get(object)!=null && valueClazz.isInstance(f.get(object))) return (T) f.get(object);
            }catch(Throwable t){
                t.printStackTrace();
            }
            return null;
        }
        throw new IllegalArgumentException("The argument \"object\" has to be an instance of the argument \"objectClazz\"!");
    }

    public static void setFieldValue(Object object, Class<?> objectClazz, String field, Object value){
        if(objectClazz.isInstance(object)){
            try {
                Field f = objectClazz.getDeclaredField(field);
                if(!f.isAccessible()) f.setAccessible(true);
                if(f.getType().isInstance(value)) {
                    f.set(object, value);
                    return;
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
            return;
        }
        throw new IllegalArgumentException("The argument \"object\" has to be an instance of the argument \"objectClazz\"!");
    }

}
