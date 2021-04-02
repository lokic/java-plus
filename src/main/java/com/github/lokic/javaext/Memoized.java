package com.github.lokic.javaext;

import com.github.lokic.javaext.func.function.Function2;
import com.github.lokic.javaext.func.function.Function3;
import com.github.lokic.javaext.tuple.Tuple;
import com.github.lokic.javaext.tuple.Tuple2;
import com.github.lokic.javaext.tuple.Tuple3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * 对执行的函数提供记忆化的功能，减少相同参数的重复调用
 */
public class Memoized {

    public static <T, R> Function<T, R> of(Function<T, R> function) {
        final ConcurrentMap<T, R> cache = new ConcurrentHashMap<>(8);
        return t -> cache.computeIfAbsent(t, function);
    }

    public static <T1, T2, R> Function2<T1, T2, R> of(Function2<T1, T2, R> function2) {
        final ConcurrentMap<Tuple2<T1, T2>, R> cache = new ConcurrentHashMap<>(8);
        return (t1, t2) -> cache.computeIfAbsent(Tuple.of(t1, t2), t -> function2.apply(t.getT1(), t.getT2()));
    }

    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> of(Function3<T1, T2, T3, R> function3) {
        final ConcurrentMap<Tuple3<T1, T2, T3>, R> cache = new ConcurrentHashMap<>(8);
        return (t1, t2, t3) -> cache.computeIfAbsent(Tuple.of(t1, t2, t3), t -> function3.apply(t.getT1(), t.getT2(), t.getT3()));
    }

}
