package com.github.lokic.javaplus;

import com.github.lokic.javaplus.func.function.Function2;
import com.github.lokic.javaplus.func.function.Function3;
import com.github.lokic.javaplus.func.function.Function4;
import com.github.lokic.javaplus.func.function.Function5;
import com.github.lokic.javaplus.func.tuple.*;
import com.github.lokic.javaplus.tuple.*;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutures {

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(java.util.stream.Collectors.<T>toList()));
    }

    public static <K, V> CompletableFuture<Map<K, V>> sequence(Map<K, CompletableFuture<V>> futures) {
        return CompletableFuture.allOf(futures.values().toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, e -> e.getValue().join())));
    }

    public static <T> CompletableFuture<T> supply(Supplier<T> supplier) {
        CompletableFuture<T> future = new CompletableFuture<>();
        try {
            T data = supplier.get();
            future.complete(data);
        } catch (Throwable ex) {
            future.completeExceptionally(ex);
        }
        return future;
    }

    public static <T> CompletableFuture<T> call(Callable<T> callable) {
        CompletableFuture<T> future = new CompletableFuture<>();
        try {
            T data = callable.call();
            future.complete(data);
        } catch (Throwable ex) {
            future.completeExceptionally(ex);
        }
        return future;
    }


    @SneakyThrows
    public static <T> T getOrElseSneakyThrow(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    public static <T> T getOrElseThrow(CompletableFuture<T> future) throws Throwable {
        return getOrElseThrow(future, Function.identity());
    }

    public static <T, X extends Throwable> T getOrElseThrow(CompletableFuture<T> future, Function<? super Throwable, X> exceptionProvider) throws X {
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw exceptionProvider.apply(e.getCause());
        } catch (Throwable e) {
            throw exceptionProvider.apply(e);
        }
    }

    public static class Fors {

        private Fors() {
            throw new UnsupportedOperationException("The class cannot be instantiated");
        }

        public static <T1, T2> Function<T1, CompletableFuture<Tuple2<T1, T2>>> For(Function<T1, CompletableFuture<T2>> f) {
            return t1 -> f.apply(t1).thenApply(t2 -> Tuple.of(t1, t2));
        }

        public static <T1, T2, T3> Function<Tuple2<T1, T2>, CompletableFuture<Tuple3<T1, T2, T3>>> For(Function2<T1, T2, CompletableFuture<T3>> f) {
            return t -> f.apply(t.getT1(), t.getT2()).thenApply(t3 -> Tuple.of(t.getT1(), t.getT2(), t3));
        }

        public static <T1, T2, T3, T4> Function<Tuple3<T1, T2, T3>, CompletableFuture<Tuple4<T1, T2, T3, T4>>> For(Function3<T1, T2, T3, CompletableFuture<T4>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3()).thenApply(t4 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t4));
        }

        public static <T1, T2, T3, T4, T5> Function<Tuple4<T1, T2, T3, T4>, CompletableFuture<Tuple5<T1, T2, T3, T4, T5>>> For(Function4<T1, T2, T3, T4, CompletableFuture<T5>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4()).thenApply(t5 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t5));
        }

        public static <T1, T2, T3, T4, T5, T6> Function<Tuple5<T1, T2, T3, T4, T5>, CompletableFuture<Tuple6<T1, T2, T3, T4, T5, T6>>> For(Function5<T1, T2, T3, T4, T5, CompletableFuture<T6>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5()).thenApply(t6 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t6));
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
