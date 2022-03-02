package com.github.lokic.javaplus;

import java.util.Map;

public class TypeSafeMap {

    private final Map<?, ?> map;

    public TypeSafeMap(Map<?, ?> map) {
        this.map = map;
    }

    public <K, V> Map<K, V> getMap() {
        return Types.cast(map);
    }
}
