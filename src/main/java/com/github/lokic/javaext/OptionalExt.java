package com.github.lokic.javaext;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OptionalExt {

    /**
     * 在java8中，{@link Optional} 只有 {@link Optional#ifPresent(Consumer)} 和 {@link Optional#orElseGet(Supplier)}，
     * 其中 {@link Optional#orElseGet(Supplier)} 必须要有返回值，必要的时候只能返回  {@link Void} 类型，不够优雅且冗余。
     * <p>
     * java8中在不需要返回值的情况下，缺少同时对{@link Optional} 中有值和没值2种情况的处理的方法，所以定义了这个方法，
     * 如果2种情况都需要处理，同时定义更加符合函数式编程的风格。
     *
     * <p> <p> 不建议使用了，可以用如下代码实现相同功能
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
     * See also: 可以使用 {@link ConsumerExt#toRunnable(Consumer)} 转换Consumer到Runnable
     * <p>
     *
     * @param c
     * @param r
     * @param <T>
     * @return
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
}
