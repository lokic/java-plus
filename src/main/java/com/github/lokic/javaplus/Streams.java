package com.github.lokic.javaplus;

import com.github.lokic.javaplus.func.function.Function2;
import com.github.lokic.javaplus.func.function.Function3;
import com.github.lokic.javaplus.func.function.Function4;
import com.github.lokic.javaplus.func.function.Function5;
import com.github.lokic.javaplus.func.tuple.*;
import com.github.lokic.javaplus.tuple.*;

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
    @SafeVarargs
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
    @SafeVarargs
    public static <T> Stream<T> ofNullable(Optional<T>... optValues) {
        return Stream.of(optValues)
                .map(Objects::requireNonNull)
                .flatMap(Streams::ofNullable);
    }

    /**
     * 返回一个不包含null的Stream，如果为null，则返回 {@link Stream#empty()}
     * <p>
     * 在 {@code Stream<T>} 的流中，{@link Stream#flatMap(Function)} 的方法上，
     * 通过该方法把 {@code value} 扁平化。
     */
    public static <T> Stream<T> ofNullable(T value) {
        return value == null ? Stream.empty() : Stream.of(value);
    }

    /**
     * 把{@link Optional} 对象 {@code option} 转成流 {@link Stream}，
     * 如果为 {@link Optional#empty()}，则转换成 {@link Stream#empty()}
     * <p>
     * 在 {@code Stream<Optional<T>>} 的流中，{@link Stream#flatMap(Function)} 的方法上，
     * 通过该方法把 {@code option} 扁平化。
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

    public static class Fors {

        private Fors() {
            throw new UnsupportedOperationException("The class cannot be instantiated");
        }

        public static <T1, T2> Function<T1, Stream<Tuple2<T1, T2>>> For(Function<T1, Stream<T2>> f) {
            return t1 -> f.apply(t1).map(t2 -> Tuple.of(t1, t2));
        }


        public static <T1, T2, T3> Function<Tuple2<T1, T2>, Stream<Tuple3<T1, T2, T3>>> For(Function2<T1, T2, Stream<T3>> f) {
            return t -> f.apply(t.getT1(), t.getT2()).map(t3 -> Tuple.of(t.getT1(), t.getT2(), t3));
        }

        public static <T1, T2, T3, T4> Function<Tuple3<T1, T2, T3>, Stream<Tuple4<T1, T2, T3, T4>>> For(Function3<T1, T2, T3, Stream<T4>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3()).map(t4 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t4));
        }

        public static <T1, T2, T3, T4, T5> Function<Tuple4<T1, T2, T3, T4>, Stream<Tuple5<T1, T2, T3, T4, T5>>> For(Function4<T1, T2, T3, T4, Stream<T5>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4()).map(t5 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t5));
        }

        public static <T1, T2, T3, T4, T5, T6> Function<Tuple5<T1, T2, T3, T4, T5>, Stream<Tuple6<T1, T2, T3, T4, T5, T6>>> For(Function5<T1, T2, T3, T4, T5, Stream<T6>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5()).map(t6 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t6));
        }


        public static <T1, R> Function<T1, R> Yield(Function<T1, R> f) {
            return f;
        }

        public static <T1, T2, R> Function<Tuple2<T1, T2>, R> Yield(TupleFunction2<T1, T2, R> f) {
            return f;
        }

        public static <T1, T2, T3, R> Function<Tuple3<T1, T2, T3>, R> Yield(TupleFunction3<T1, T2, T3, R> f) {
            return f;
        }

        public static <T1, T2, T3, T4, R> Function<Tuple4<T1, T2, T3, T4>, R> Yield(TupleFunction4<T1, T2, T3, T4, R> f) {
            return f;
        }

        public static <T1, T2, T3, T4, T5, R> Function<Tuple5<T1, T2, T3, T4, T5>, R> Yield(TupleFunction5<T1, T2, T3, T4, T5, R> f) {
            return f;
        }

        public static <T1, T2, T3, T4, T5, T6, R> Function<Tuple6<T1, T2, T3, T4, T5, T6>, R> Yield(TupleFunction6<T1, T2, T3, T4, T5, T6, R> f) {
            return f;
        }
    }

}
