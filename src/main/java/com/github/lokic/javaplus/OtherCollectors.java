package com.github.lokic.javaplus;

import com.github.lokic.javaplus.functional.function.Function2;
import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class OtherCollectors {


    public static final Set<Collector.Characteristics> CH_CONCURRENT_ID
            = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT,
            Collector.Characteristics.UNORDERED,
            Collector.Characteristics.IDENTITY_FINISH));
    public static final Set<Collector.Characteristics> CH_CONCURRENT_NOID
            = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT,
            Collector.Characteristics.UNORDERED));
    public static final Set<Collector.Characteristics> CH_ID
            = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    public static final Set<Collector.Characteristics> CH_UNORDERED_ID
            = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED,
            Collector.Characteristics.IDENTITY_FINISH));
    public static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
    public static final Set<Collector.Characteristics> CH_UNORDERED_NOID
            = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));


    public static class Reversed {

        /**
         * 倒序
         *
         * @param <T> Stream里的类型
         */
        public static <T> Collector<T, ?, List<T>> reversed() {
            return reversed(Function.identity());
        }

        /**
         * 倒序，并对list进行 {@code finisher} 转换
         *
         * @param <T> Stream里的类型
         */
        public static <T, R> Collector<T, ?, R> reversed(Function<List<T>, R> finisher) {
            return java.util.stream.Collectors.collectingAndThen(java.util.stream.Collectors.toList(), list -> {
                Collections.reverse(list);
                return finisher.apply(list);
            });
        }
    }

    public static class Distinct {

        /**
         * 去重复，如果数据存在相同的值，则保留最新配置的值
         * <p>
         * 如，before: A B A C   after: B A C
         *
         * @param <T> Stream里的类型
         */
        public static <T> Collector<T, ?, List<T>> distinctLastPut() {
            return distinctByKey(Function.identity(), Order.LAST_PUT);
        }

        public static <T> Collector<T, ?, List<T>> distinctLastPutByKey(Function<? super T, ?> keyExtractor) {
            return distinctByKey(keyExtractor, Order.LAST_PUT);
        }

        /**
         * 去重复，如果数据存在相同的值，则保留最早配置的值
         * <p>
         * 如，before: A B A C  after: A B C
         *
         * @param <T> Stream里的类型
         */
        public static <T> Collector<T, ?, List<T>> distinctFirstPut() {
            return distinctByKey(Function.identity(), Order.FIRST_PUT);
        }

        /**
         * 基于 {@code keyExtractor} 进行去重，只保存第一个值，之后的都忽略。
         * <p>
         * Note：
         * 也可以使用 {@link Predicates#distinctByKey(Function)}，以获得更好的性能
         *
         * @param <T> Stream里的类型
         */
        public static <T> Collector<T, ?, List<T>> distinctFirstPutByKey(Function<? super T, ?> keyExtractor) {
            return distinctByKey(keyExtractor, Order.FIRST_PUT);
        }

        private static <T> Collector<T, ?, List<T>> distinctByKey(
                Function<? super T, ?> keyExtractor, Order order) {
            return java.util.stream.Collectors.collectingAndThen(
                    java.util.stream.Collectors.toMap(
                            keyExtractor,
                            Function.identity(),
                            (a, b) -> merge(order, a, b),
                            () -> buildMap(order)
                    ),
                    res -> new ArrayList<>(res.values()));
        }

        private static <T> T merge(Order order, T a, T b) {
            switch (order) {
                case FIRST_PUT:
                    return a;
                case LAST_PUT:
                    return b;
                default:
                    throw new IllegalStateException("not support order = " + order);
            }
        }

        private static <K, V> Map<K, V> buildMap(Order order) {
            switch (order) {
                case FIRST_PUT:
                    return new LinkedHashMap<>();
                case LAST_PUT:
                    return new LinkedHashMap<>(16, 0.75f, true);
                default:
                    throw new IllegalStateException("not support order = " + order);
            }
        }

        private enum Order {
            /**
             * key相同的第一个值
             */
            FIRST_PUT,

            /**
             * key相同的最后一个值
             */
            LAST_PUT
        }
    }

    public static <K, U, M extends Map<K, U>>
    Collector<Map.Entry<K, U>, ?, M> toMap(Supplier<M> mapSupplier) {
        return toMap(Map.Entry::getValue, mapSupplier);
    }

    public static <T, K, U, M extends Map<K, T>>
    Collector<Map.Entry<K, U>, ?, M> toMap(
            Function<? super Map.Entry<K, U>, ? extends T> valueMapper, Supplier<M> mapSupplier) {
        return java.util.stream.Collectors.toMap(
                Map.Entry::getKey, valueMapper,
                (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                mapSupplier);
    }

    public static <T1, T2, K, U, M extends Map<K, U>>
    Collector<Tuple2<T1, T2>, ?, M> toMap(
            Function2<? super T1, ? super T2, ? extends K> keyMapper,
            Function2<? super T1, ? super T2, ? extends U> valueMapper,
            BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return java.util.stream.Collectors.toMap(
                t -> keyMapper.apply(t.getT1(), t.getT2()),
                t -> valueMapper.apply(t.getT1(), t.getT2()),
                mergeFunction, mapSupplier);
    }

    public static <T1, T2, K, U, M extends Map<K, U>>
    Collector<Tuple2<T1, T2>, ?, Stream<Map.Entry<K, U>>> toMapEntryStream(
            Function2<? super T1, ? super T2, ? extends K> keyMapper,
            Function2<? super T1, ? super T2, ? extends U> valueMapper,
            BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return toMapEntryStream(t -> keyMapper.apply(t.getT1(), t.getT2()), t -> valueMapper.apply(t.getT1(), t.getT2()),
                mergeFunction, mapSupplier);
    }

    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Stream<Map.Entry<K, U>>> toMapEntryStream(
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper,
            BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return java.util.stream.Collectors.collectingAndThen(
                java.util.stream.Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier),
                m -> m.entrySet().stream());
    }

    public static <T1, T2, K, U, M extends Map<K, U>>
    Collector<Tuple2<T1, T2>, ?, Stream<Tuple2<K, U>>> toMapTupleStream(
            Function2<? super T1, ? super T2, ? extends K> keyMapper,
            Function2<? super T1, ? super T2, ? extends U> valueMapper,
            BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return toMapTupleStream(t -> keyMapper.apply(t.getT1(), t.getT2()), t -> valueMapper.apply(t.getT1(), t.getT2()),
                mergeFunction, mapSupplier);
    }

    public static <T, K, U, M extends Map<K, U>>
    Collector<T, ?, Stream<Tuple2<K, U>>> toMapTupleStream(
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper,
            BinaryOperator<U> mergeFunction,
            Supplier<M> mapSupplier) {
        return java.util.stream.Collectors.collectingAndThen(
                java.util.stream.Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier),
                m -> m.entrySet().stream().map(e -> Tuple.of(e.getKey(), e.getValue())));
    }

    @SuppressWarnings("unchecked")
    private static <I, R> Function<I, R> castingIdentity() {
        return i -> (R) i;
    }

    /**
     * Simple implementation class for {@code Collector}.
     *
     * @param <T> the type of elements to be collected
     * @param <R> the type of the result
     */
    public static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A, R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        public CollectorImpl(Supplier<A> supplier,
                             BiConsumer<A, T> accumulator,
                             BinaryOperator<A> combiner,
                             Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }


    public static <T, A, R>
    Collector<T, ?, R> filtering(Predicate<? super T> predicate,
                                 Collector<? super T, A, R> downstream) {
        BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
        return Collector.of(downstream.supplier(),
                (r, t) -> {
                    if (predicate.test(t)) {
                        downstreamAccumulator.accept(r, t);
                    }
                },
                downstream.combiner(), downstream.finisher(),
                downstream.characteristics().toArray(new Collector.Characteristics[0]));
    }

    public static <T, U, A, R>
    Collector<T, ?, R> flatMapping(Function<? super T, ? extends Stream<? extends U>> mapper,
                                   Collector<? super U, A, R> downstream) {
        BiConsumer<A, ? super U> downstreamAccumulator = downstream.accumulator();
        return Collector.of(downstream.supplier(),
                (r, t) -> {
                    try (Stream<? extends U> result = mapper.apply(t)) {
                        if (result != null)
                            result.sequential().forEach(u -> downstreamAccumulator.accept(r, u));
                    }
                },
                downstream.combiner(), downstream.finisher(),
                downstream.characteristics().toArray(new Collector.Characteristics[0]));
    }

    public static <T> Collector<T, ?, BigDecimal> summingBigDecimal(Function<? super T, BigDecimal> mapper) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{BigDecimal.ZERO},
                (a, t) -> a[0] = a[0].add(mapper.apply(t)),
                (a, b) -> {
                    a[0] = a[0].add(b[0]);
                    return a;
                },
                a -> a[0], CH_NOID);
    }


    public static <T> Collector<T, ?, BigDecimal> averagingBigDecimal(Function<? super T, BigDecimal> mapper, int scale, RoundingMode roundingMode) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO},
                (a, t) -> {
                    a[0] = a[0].add(mapper.apply(t));
                    a[1] = a[1].add(BigDecimal.ONE);
                },
                (a, b) -> {
                    a[0] = a[0].add(b[0]);
                    a[1] = a[1].add(b[1]);
                    return a;
                },
                a -> (a[1].compareTo(BigDecimal.ZERO) == 0) ? BigDecimal.ZERO.setScale(scale, roundingMode) : a[0].divide(a[1], scale, roundingMode),
                CH_NOID);
    }

    public static <T, K, U>
    Collector<T, ?, Map<K, U>> toMapNullValue(Function<? super T, ? extends K> keyMapper,
                                              Function<? super T, ? extends U> valueMapper) {
        return new CollectorImpl<>(
                LinkedHashMap::new,
                (map, element) -> {
                    K k = keyMapper.apply(element);
                    if (map.containsValue(k)) {
                        throw new IllegalStateException("Duplicate key");
                    }
                    map.put(keyMapper.apply(element), valueMapper.apply(element));
                },
                (m1, m2) -> {
                    throw new UnsupportedOperationException("Not support merge");
                },
                CH_ID);
    }


}
