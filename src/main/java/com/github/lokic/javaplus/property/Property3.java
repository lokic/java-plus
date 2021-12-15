package com.github.lokic.javaplus.property;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple3;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;

/**
 * 用于K1、K2、K3三个值作为key来查询枚举的场景
 *
 * @param <E>  枚举类
 * @param <K1> 转换之后的key1
 * @param <K2> 转换之后的key2
 * @param <K3> 转换之后的key3
 */
public class Property3<E extends Enum<E>, K1, K2, K3> {
    private final Property1<E, Tuple3<K1, K2, K3>> property;

    public Property3(@NonNull Class<E> clazz, @NonNull Function<E, Tuple3<K1, K2, K3>> function) {
        this.property = new Property1<>(clazz, function);
    }

    public E of(K1 k1, K2 k2, K3 k3) {
        return property.of(Tuple.of(k1, k2, k3));
    }

    public Optional<E> optOf(K1 k1, K2 k2, K3 k3) {
        return property.optOf(Tuple.of(k1, k2, k3));
    }

    public E requireOf(K1 k1, K2 k2, K3 k3) {
        return property.requireOf(Tuple.of(k1, k2, k3));
    }
}
