package com.github.lokic.javaplus.property;

import lombok.NonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 在定义枚举类之后，经常需要基于枚举类的某个字段作为key来查询枚举，抽象出这个模块来实现key到枚举的映射和转换逻辑。
 *
 * <pre>{@code
 *      public enum AbEnum {
 *         A(1),
 *         B(2);
 *
 *         final int code;
 *
 *         AbEnum(int code) {
 *             this.code = code;
 *         }
 *
 *         public int getCode() {
 *             return code;
 *         }
 *
 *         public static final Property1<AbEnum, Integer> OF_CODE =  new Property1<>(AbEnum.class, AbEnum::getCode);
 *     }
 * }</pre>
 *
 * @param <E> 枚举类
 * @param <K> 转换之后的key
 */
public class Property1<E extends Enum<E>, K> {

    private final Class<E> clazz;

    private final Map<K, E> map;

    public Property1(@NonNull Class<E> clazz, @NonNull Function<E, K> function) {
        this.clazz = clazz;
        this.map = Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(function, Function.identity()));
    }

    public E of(K k) {
        return map.get(k);
    }

    public Optional<E> optOf(K k) {
        return Optional.ofNullable(of(k));
    }

    public E requireOf(K k) {
        E t = of(k);
        if (t == null) {
            throw new IllegalStateException(clazz.getTypeName() + " not found [" + k + "]");
        }
        return t;
    }

}