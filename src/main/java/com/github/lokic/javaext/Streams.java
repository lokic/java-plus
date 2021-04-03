package com.github.lokic.javaext;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 对 {@link Stream} 进行的一些方法扩展
 */
public class Streams {

    /**
     * 返回一个不包含null的Stream
     */
    public static <T> Stream<T> ofNullable(T... values) {
        return Stream.of(values)
                .flatMap(Streams::ofNullable);
    }

    /**
     * 返回一个不包含 {@link Optional#empty()} 的Stream
     * <p>
     * Note:
     * {@code optValues} 中每一个{@code Optional<T> } 元素都不能为null
     */
    public static <T> Stream<T> ofNullable(Optional<T>... optValues) {
        return Stream.of(optValues)
                .map(Objects::requireNonNull)
                .flatMap(Streams::ofNullable);
    }

    /**
     * 返回一个不包含null的Stream，如果为null，则返回 {@link Stream#empty()}
     * <p>
     * 在 {@code Stream<T>} 的流中，{@link Stream#flatMap(Function)} 的方法上，
     * 通过该方法 {@link #ofNullable(Object)} )} 把 {@code value} 扁平化。
     */
    public static <T> Stream<T> ofNullable(T value) {
        return value == null ? Stream.empty() : Stream.of(value);
    }

    /**
     * 把{@link Optional} 对象 {@code option} 转成流 {@link Stream}，
     * 如果为 {@link Optional#empty()}，则转换成 {@link Stream#empty()}
     * <p>
     * 在 {@code Stream<Optional<T>>} 的流中，{@link Stream#flatMap(Function)} 的方法上，
     * 通过该方法 {@link #ofNullable(Optional)} 把 {@code option} 扁平化。
     */
    public static <T> Stream<T> ofNullable(Optional<T> option) {
        Objects.requireNonNull(option);
        return option.map(Stream::of).orElseGet(Stream::empty);
    }

    /**
     * 把 {@link Collection} 转换成 {@link Stream}，
     * 如果 {@link Collection} 为 null， 则返回 {@link Stream#empty()}
     */
    public static <T> Stream<T> ofNullable(Collection<T> values) {
        return values == null ? Stream.empty() : values.stream();
    }

    /**
     * 能在Stream中更加方便得使用方法引用，  {@code Function<T, Collection<R>> ==> Function<T,Stream<R>> }
     */
    public static <T, R> Function<T, Stream<R>> ofCollectionNullable(Function<T, Collection<R>> function) {
        return t -> ofNullable(function.apply(t));
    }

    /**
     * 能在Stream中更加方便得使用方法引用，  {@code Function<T, R> ==> Function<T,Stream<R>> }
     */
    public static <T, R> Function<T, Stream<R>> ofObjectNullable(Function<T, R> function) {
        return t -> ofNullable(function.apply(t));
    }

}
