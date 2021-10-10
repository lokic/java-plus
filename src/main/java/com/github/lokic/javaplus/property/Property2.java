package com.github.lokic.javaplus.property;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;

/**
 * 用于K1和K2二个值作为key来查询枚举的场景
 *
 * @param <E>  枚举类
 * @param <K1> 转换之后的key1
 * @param <K2> 转换之后的key2
 */
public class Property2<E extends Enum<E>, K1, K2> {
    private final Property1<E, Tuple2<K1, K2>> property;

    public Property2(@NonNull Class<E> clazz, @NonNull Function<E, Tuple2<K1, K2>> function) {
        this.property = new Property1<>(clazz, function);
    }

    public E of(K1 k1, K2 k2) {
        return property.of(Tuple.of(k1, k2));
    }

    public Optional<E> optOf(K1 k1, K2 k2) {
        return property.optOf(Tuple.of(k1, k2));
    }

    public E requireOf(K1 k1, K2 k2) {
        return property.requireOf(Tuple.of(k1, k2));
    }
}
