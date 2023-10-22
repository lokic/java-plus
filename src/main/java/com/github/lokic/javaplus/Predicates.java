package com.github.lokic.javaplus;

import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Predicates {

    /**
     * 基于 {@code keyExtractor}, 来进行去重，只保存第一个值，之后的都忽略。
     * <p>
     * 建议在串行环境中执行该函数，并行环境中保存下的数据没法保证。
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return object -> seen.add(keyExtractor.apply(object));
    }

    /**
     * 获取数据，直到{@code predicate}为false
     * <p>
     * stream : "1", "2", "3", "", "5", "6"
     * predicate: isNotEmpty
     * result:  "1", "2", "3"
     *
     * @param stream
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<? super T> predicate) {
        Spliterator<T> spliterator = stream.spliterator();
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(spliterator.estimateSize(), 0) {
            private boolean stillGoing = true;

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                boolean hasNext = spliterator.tryAdvance(item -> {
                    if (predicate.test(item)) {
                        action.accept(item);
                    } else {
                        stillGoing = false;
                    }
                });
                return hasNext && stillGoing;
            }
        }, false);
    }

    /**
     * 抛弃数据，直到{@code predicate}为false
     * <p>
     * stream : "1", "2", "3", "", "5", "6"
     * predicate: isNotEmpty
     * result:  "", "5", "6"
     *
     * @param stream
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> Stream<T> dropWhile(Stream<T> stream, Predicate<? super T> predicate) {
        Spliterator<T> spliterator = stream.spliterator();
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(spliterator.estimateSize(), 0) {
            private boolean canGo = false;

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return spliterator.tryAdvance(item -> {
                    if (!canGo) {
                        if (!predicate.test(item)) {
                            action.accept(item);
                            canGo = true;
                        }
                    } else {
                        action.accept(item);
                    }
                });
            }
        }, false);
    }
}
