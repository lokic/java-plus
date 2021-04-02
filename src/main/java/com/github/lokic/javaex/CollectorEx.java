package com.github.lokic.javaex;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorEx {

    public static class Reversed {

        /**
         * 倒序
         *
         * @param <T>
         * @return
         */
        public static <T> Collector<T, ?, List<T>> reversed() {
            return reversed(Function.identity());
        }

        /**
         * 倒序，并对list进行 {@code finisher} 转换
         *
         * @param finisher
         * @param <T>
         * @param <R>
         * @return
         */
        public static <T, R> Collector<T, ?, R> reversed(Function<List<T>, R> finisher) {
            return Collectors.collectingAndThen(Collectors.toList(), list -> {
                Collections.reverse(list);
                return finisher.apply(list);
            });
        }
    }

    public static class Distinct {

        /**
         * 去重复，如果数据存在相同的rule，则保留最新配置的rule
         * <p>
         * 如，A B A C  => B A C
         *
         * @param <T>
         * @return
         */
        public static <T> Collector<T, ?, List<T>> distinctLastPut() {
            return distinctByKey(Function.identity(), Order.LAST_PUT, last());
        }

        public static <T> Collector<T, ?, List<T>> distinctLastPutByKey(Function<? super T, ?> keyExtractor) {
            return distinctByKey(keyExtractor, Order.LAST_PUT, last());
        }

        /**
         * 去重复，如果数据存在相同的rule，则保留最早配置的rule
         * <p>
         * 如，A B A C  =>  A B C
         *
         * @param <T>
         * @return
         */
        public static <T> Collector<T, ?, List<T>> distinctFirstPut() {
            return distinctByKey(Function.identity(), Order.FIRST_PUT, first());
        }

        /**
         * 基于 {@code keyExtractor} 进行去重，只保存第一个值，之后的都忽略。
         * <p>
         * Note：
         * 也可以使用 {@link PredicateEx#distinctByKey(Function)}，以获得更好的性能
         *
         * @param keyExtractor
         * @param <T>
         * @return
         */
        public static <T> Collector<T, ?, List<T>> distinctFirstPutByKey(Function<? super T, ?> keyExtractor) {
            return distinctByKey(keyExtractor, Order.FIRST_PUT, first());
        }

        private static <T> Collector<T, ?, List<T>> distinctByKey(
                Function<? super T, ?> keyExtractor, Order order, Function<List<T>, T> finisher) {

            return Collectors.collectingAndThen(
                    Collectors.groupingBy(
                            keyExtractor,
                            () -> buildMap(order),
                            Collectors.toList()
                    ),
                    res -> res.values()
                            .stream()
                            .map(finisher)
                            .collect(Collectors.toList()));
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

        private static <T> Function<List<T>, T> last() {
            return li -> li.get(li.size() - 1);
        }

        private static <T> Function<List<T>, T> first() {
            return li -> li.get(0);
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

}
