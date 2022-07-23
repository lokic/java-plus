package com.github.lokic.javaplus.property;

import com.github.lokic.javaplus.TypeSafeMap;
import com.github.lokic.javaplus.functional.sneakythrows.SneakyThrowsFunctional;

import java.util.concurrent.ConcurrentHashMap;

public interface KeyEnum<E extends Enum<E> & KeyEnum<E>> {

    TypeSafeMap CACHE = new TypeSafeMap(new ConcurrentHashMap<>());

    int getKey();

    static <T extends Enum<T> & KeyEnum<T>> T keyOf(Class<T> enumType, Integer key) {
        return getProperty(enumType).of(key);
    }

    static <T extends Enum<T> & KeyEnum<T>> Property1<T, Integer> getProperty(Class<T> enumType) {
        return CACHE.<Class<T>, Property1<T, Integer>>getMap()
                .computeIfAbsent(enumType, SneakyThrowsFunctional.function(t -> {
                    //Note: JDK-8141508 bug，必须写成 ke -> ke.getKey()，不要使用方法引用
                    return new Property1<>(enumType, ke -> ke.getKey());
                }));
    }

}
