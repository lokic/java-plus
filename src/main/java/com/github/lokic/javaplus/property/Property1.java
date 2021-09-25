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
 * @param <T> 泛型类
 * @param <K> 转换之后的key
 */
public class Property1<T extends Enum<T>, K> {

    private final Class<T> clazz;

    private final Map<K, T> map;

    public Property1(@NonNull Class<T> clazz, @NonNull Function<T, K> function) {
        this.clazz = clazz;
        this.map = Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(function, Function.identity()));
    }

    public T of(K k) {
        return map.get(k);
    }

    public Optional<T> optOf(K k) {
        return Optional.ofNullable(of(k));
    }

    public T requireOf(K k) {
        T t = of(k);
        if (t == null) {
            throw new IllegalStateException(clazz.getTypeName() + " not found [" + k + "]");
        }
        return t;
    }

}