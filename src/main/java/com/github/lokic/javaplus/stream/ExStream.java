package com.github.lokic.javaplus.stream;

import com.github.lokic.javaplus.Consumers;
import com.github.lokic.javaplus.Functions;
import com.github.lokic.javaplus.Memoized;
import com.github.lokic.javaplus.Predicates;
import com.github.lokic.javaplus.join.Join;
import com.github.lokic.javaplus.join.JoinOn;
import com.github.lokic.javaplus.join.JoinStream;

import java.util.Collection;
import java.util.function.*;
import java.util.stream.Stream;

public class ExStream<T> {

    private final Supplier<Stream<T>> streamProvider;

    private ExStream(Stream<T> stream) {
        this.streamProvider = () -> stream;
    }

    private ExStream(Supplier<Stream<T>> streamProvider) {
        this.streamProvider = streamProvider;
    }

    public <R> ExStream<R> func(Function<Stream<T>, Stream<R>> function) {
        return new ExStream<>(function.apply(stream()));
    }

//    public ExStream<T> filter(Predicate<? super T> predicate) {
//        return new ExStream<>(stream().filter(predicate));
//    }
//
//    public <R> ExStream<R> map(Function<? super T, ? extends R> mapper) {
//        return new ExStream<>(stream().map(mapper));
//    }
//
//    public <R> ExStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
//        return new ExStream<>(stream().flatMap(mapper));
//    }

    public ExStream<T> takeWhile(Predicate<? super T> predicate) {
        return new ExStream<>(Predicates.takeWhile(stream(), predicate));
    }

    public ExStream<T> dropWhile(Predicate<? super T> predicate) {
        return new ExStream<>(Predicates.dropWhile(stream(), predicate));
    }

    public ExStream<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        return new ExStream<>(stream().filter(Predicates.distinctByKey(keyExtractor)));
    }

    public <R> ExStream<R> mapWithIndex(BiFunction<Integer, ? super T, ? extends R> biFunction) {
        return new ExStream<>(stream().map(Functions.mapWithIndex(biFunction)));
    }

    public void forEachWithIndex(BiConsumer<Integer, ? super T> biConsumer) {
        stream().forEach(Consumers.mapWithIndex(biConsumer));
    }

    public <R> ExStream<R> memoized(Function<? super T, ? extends R> function) {
        return new ExStream<>(stream().map(Memoized.of(function)));
    }

    public <U, K, R> ExStream<R> innerJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        return of(Join.stream(stream()).innerJoin(right, on));
    }

    public <U, K, R> ExStream<R> leftOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        return of(Join.stream(stream()).leftOuterJoin(right, on));
    }

    public <U, K, R> ExStream<R> rightOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        return of(Join.stream(stream()).rightOuterJoin(right, on));
    }

    public <U, K, R> ExStream<R> fullOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        return of(Join.stream(stream()).fullOuterJoin(right, on));
    }

    public ExStream<T> cached() {
        return of(Memoized.of(streamProvider));
    }

    public Stream<T> stream() {
        return streamProvider.get();
    }

    public static <T> ExStream<T> of(JoinStream<T> joinStream) {
        return new ExStream<>(joinStream.stream());
    }

    public static <T> ExStream<T> of(Stream<T> stream) {
        return new ExStream<>(stream);
    }

    public static <T> ExStream<T> of(Supplier<Stream<T>> streamProvider) {
        return new ExStream<>(streamProvider);
    }

    public static <T> ExStream<T> of(Collection<T> collection) {
        return new ExStream<>(collection.stream());
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> ExStream<T> of(T... values) {
        return new ExStream<>(Stream.of(values));
    }


    public static class Func {
        public static <T> Function<Stream<T>, Stream<T>> filter(Predicate<? super T> predicate) {
            return s -> s.filter(predicate);
        }

        public static <T, R> Function<Stream<T>, Stream<R>> map(Function<? super T, ? extends R> mapper) {
            return s -> s.map(mapper);
        }

        public static <T, R> Function<Stream<T>, Stream<R>> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return s -> s.flatMap(mapper);
        }
    }

}
