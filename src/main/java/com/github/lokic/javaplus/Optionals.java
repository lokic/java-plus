package com.github.lokic.javaplus;

import com.github.lokic.javaplus.functional.function.Function2;
import com.github.lokic.javaplus.functional.function.Function3;
import com.github.lokic.javaplus.functional.function.Function4;
import com.github.lokic.javaplus.functional.function.Function5;
import com.github.lokic.javaplus.functional.tuple.*;
import com.github.lokic.javaplus.tuple.*;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Optionals {

    /**
     * 在java8中，{@link Optional} 只有 {@link Optional#ifPresent(Consumer)} 和 {@link Optional#orElseGet(Supplier)}，
     * 其中 {@link Optional#orElseGet(Supplier)} 必须要有返回值，必要的时候只能返回  {@link Void} 类型，不够优雅且冗余。
     * <p>
     * java8中在不需要返回值的情况下，缺少同时对{@link Optional} 中有值和没值2种情况的处理的方法，所以定义了这个方法，
     * 如果2种情况都需要处理，同时定义更加符合函数式编程的风格。
     * <p>
     * 不建议使用了。可以用如下代码实现相同功能
     * <pre>
     * {@code Optional.of("")
     *          .<Runnable>map(x -> ()  -> {
     *              // exist value do something
     *          })
     *          .orElse(() -> {
     *              // no value do something
     *          })
     *          .run();}
     * </pre>
     * <p>
     * See also: 可以使用 {@link com.github.lokic.javaplus.functional.function.Functional#runnable(Consumer)} 转换Consumer到Runnable
     * <p>
     */
    public static <T> OptionalConsumer<T> ifPresentOrElse(Consumer<T> c, Runnable r) {
        return new OptionalConsumer<>(c, r);
    }

    public static class OptionalConsumer<T> implements Consumer<Optional<T>> {

        private final Consumer<T> c;
        private final Runnable r;

        OptionalConsumer(Consumer<T> c, Runnable r) {
            this.c = c;
            this.r = r;
        }

        @Override
        public void accept(Optional<T> t) {
            if (t.isPresent()) {
                c.accept(t.get());
            } else {
                r.run();
            }
        }
    }


    public static class Fors {

        private Fors() {
            throw new UnsupportedOperationException("The class cannot be instantiated");
        }

        public static <T1, T2> Function<T1, Optional<Tuple2<T1, T2>>> For(Function<T1, Optional<T2>> f) {
            return t1 -> f.apply(t1).map(t2 -> Tuple.of(t1, t2));
        }


        public static <T1, T2, T3> Function<Tuple2<T1, T2>, Optional<Tuple3<T1, T2, T3>>> For(Function2<T1, T2, Optional<T3>> f) {
            return t -> f.apply(t.getT1(), t.getT2()).map(t3 -> Tuple.of(t.getT1(), t.getT2(), t3));
        }

        public static <T1, T2, T3, T4> Function<Tuple3<T1, T2, T3>, Optional<Tuple4<T1, T2, T3, T4>>> For(Function3<T1, T2, T3, Optional<T4>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3()).map(t4 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t4));
        }

        public static <T1, T2, T3, T4, T5> Function<Tuple4<T1, T2, T3, T4>, Optional<Tuple5<T1, T2, T3, T4, T5>>> For(Function4<T1, T2, T3, T4, Optional<T5>> f) {
            return t -> f.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4()).map(t5 -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t5));
        }

        public static <T1, T2, T3, T4, T5, T6> Function<Tuple5<T1, T2, T3, T4, T5>, Optional<Tuple6<T1, T2, T3, T4, T5, T6>>> For(Function5<T1, T2, T3, T4, T5, Optional<T6>> f) {
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
