package com.github.lokic.javaext;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {

    /**
     * 基于 {@code keyExtractor}, 来进行去重，只保存第一个值，之后的都忽略。
     * <p>
     * 建议在串行环境中执行该函数，并行环境中保存下的数据没法保证。
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return object -> seen.add(keyExtractor.apply(object));
    }

}
