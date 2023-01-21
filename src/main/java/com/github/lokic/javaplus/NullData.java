package com.github.lokic.javaplus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class NullData {
    private static final List<?> NULL_LIST = Collections.unmodifiableList(createNullList());

    private static <T> List<T> createNullList() {
        List<T> li = new ArrayList<>();
        li.add(null);
        return li;
    }

    public static <T> Stream<T> nullStream() {
        return NullData.<T>nullList().stream();
    }

    public static <T> List<T> nullList() {
        @SuppressWarnings("unchecked")
        List<T> l = (List<T>) NULL_LIST;
        return l;
    }
}
