package com.github.caay2000.metropolis;

import java.lang.reflect.Field;

public class ReflectionTestUtils {

    public static <T> T getField(Object target, String fieldName, Class<T> clazz) throws NoSuchFieldException, IllegalAccessException {
        Field data = target.getClass().getDeclaredField(fieldName);
        data.setAccessible(true);
        return (T) data.get(target);
    }
}
