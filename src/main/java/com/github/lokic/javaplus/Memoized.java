package com.github.lokic.javaplus;

import com.github.lokic.javaplus.functional.function.Function2;
import com.github.lokic.javaplus.functional.function.Function3;
import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import com.github.lokic.javaplus.tuple.Tuple3;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.StreamSupport.stream;

/**
 * 对执行的函数提供记忆化的功能，减少相同参数的重复调用
 */
public class Memoized {

    public static <T, R> Function<T, R> of(Function<T, R> function) {
        final ConcurrentMap<T, R> cache = new ConcurrentHashMap<>(8);
        return t -> cache.computeIfAbsent(t, function);
    }

    public static <T1, T2, R> Function2<T1, T2, R> of(Function2<T1, T2, R> function2) {
        final ConcurrentMap<Tuple2<T1, T2>, R> cache = new ConcurrentHashMap<>(8);
        return (t1, t2) -> cache.computeIfAbsent(Tuple.of(t1, t2), t -> function2.apply(t.getT1(), t.getT2()));
    }

    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> of(Function3<T1, T2, T3, R> function3) {
        final ConcurrentMap<Tuple3<T1, T2, T3>, R> cache = new ConcurrentHashMap<>(8);
        return (t1, t2, t3) -> cache.computeIfAbsent(Tuple.of(t1, t2, t3), t -> function3.apply(t.getT1(), t.getT2(), t.getT3()));
    }


    /**
     * 带缓存功能，可多次重放Stream
     *
     * @param streamSupplier
     * @param <T>
     * @return
     */
    public static <T> Supplier<Stream<T>> of(Supplier<Stream<T>> streamSupplier) {
        final Spliterator<T> spliterator = streamSupplier.get().spliterator();
        final List<T> cache = new ArrayList<>();
        return () -> {
            Spliterator<T> split = new Spliterators.AbstractSpliterator<T>(
                    spliterator.estimateSize(), spliterator.characteristics()) {

                private int index = 0;
                private boolean hasNext = true;

                @Override
                public boolean tryAdvance(Consumer<? super T> action) {
                    int currentIndex = index++;
                    if (currentIndex < cache.size()) {
                        action.accept(cache.get(currentIndex));
                        return true;
                    } else if (hasNext) {
                        hasNext = spliterator.tryAdvance(item -> {
                            cache.add(item);
                            action.accept(item);
                        });
                    }
                    return hasNext;
                }
            };
            return stream(split, false);
        };
    }

}
