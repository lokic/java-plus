package com.github.lokic.javaplus;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {

    /**
     * 在某些遍历的场景，可以通过该方法，在 {@code biFunction} 中拿到遍历的下标
     *
     * <pre>{@code
     *     List(...)
     *      .stream()
     *      .map(Functions.mapWithIndex((index, item) -> {
     *          ...
     *      })
     * }</pre>
     */
    public static <T, R> Function<T, R> mapWithIndex(BiFunction<Integer, T, R> biFunction) {
        AtomicInteger i = new AtomicInteger();
        return t -> biFunction.apply(i.getAndIncrement(), t);
    }

    @SuppressWarnings("unchecked")
    public static <T, V> BiConsumer<T, V> toBiConsumer(Method method, Class<T> clazz, Class<V> vClass) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.unreflect(method);
        final CallSite site = LambdaMetafactory.metafactory(lookup, "accept",
                MethodType.methodType(BiConsumer.class),
                MethodType.methodType(void.class, Object.class, Object.class),
                handle,
                MethodType.methodType(void.class, clazz, vClass));
        return (BiConsumer<T, V>) site.getTarget().invokeExact();
    }

    @SuppressWarnings("unchecked")
    public static <T, R> Function<T, R> toFunction(Method method, Class<T> clazz, Class<R> retClass) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.unreflect(method);
        final CallSite site = LambdaMetafactory.metafactory(lookup, "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                handle,
                MethodType.methodType(retClass, clazz));
        return (Function<T, R>) site.getTarget().invokeExact();
    }

}
