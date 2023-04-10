package com.wang.jk.common.util;

import com.wang.jk.common.enhance.Stop;

import java.lang.reflect.Field;

public class Classes {
    public static Class<?> notObject(Class<?>... sources) {
        if (sources == null) return null;
        for (Class<?> source : sources) {
            if (!source.equals(Object.class)) return source;
        }
        return null;
    }

    public static Field getField(Class<?> cls, String fieldName) throws Exception {
        return enumerateFields(cls, (field, curCls) -> {
            if (field.getName().equals(fieldName)) return Stop.create(field);
            return null;
        });
    }

    public static <T> T enumerateFields(Class<?> cls,
                                        FieldConsumer<T> fieldConsumer) throws Exception {
        if (fieldConsumer == null || cls == null) return null;
        Class<?> curCls = cls;
        while (!curCls.equals(Object.class)) {
            for (Field field : curCls.getDeclaredFields()) {
                Stop<T> stop = fieldConsumer.accept(field, curCls);
                if (stop != null) return stop.getData();
            }
            curCls = curCls.getSuperclass();
        }
        return null;
    }

    public interface FieldConsumer<T> {
        Stop<T> accept(Field field, Class<?> ownerCls) throws Exception;
    }
}
