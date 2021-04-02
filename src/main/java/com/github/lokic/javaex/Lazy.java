package com.github.lokic.javaex;

import java.util.function.Supplier;

/**
 * 带缓存功能的 {@link Supplier}
 * <p>
 * {@link Supplier} 每一次执行 {@link Supplier#get()} 都会执行一次 {@link Supplier#get()} 内的业务逻辑。
 * 如果在一些{@link Supplier#get()} 比较耗时的场景，且多次执行 {@link Supplier#get()} 结果相同的情况下，
 * 我们没有必要多次执行，只需要把执行第一次的结果保存下来，之后多次调用直接返回即可，{@link Lazy} 就是这个目的设计出来的。
 *
 * @param <T>
 */
public class Lazy<T> implements Supplier<T> {

    private volatile Supplier<T> valueSupplier;

    private T value;

    private Lazy(Supplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    @Override
    public T get() {
        if (valueSupplier != null) {
            synchronized (this) {
                if (valueSupplier != null) {
                    value = valueSupplier.get();
                    valueSupplier = null;
                }
            }
        }
        return value;
    }

    public static <T> Lazy<T> of(Supplier<T> valueSupplier) {
        return new Lazy<>(valueSupplier);
    }

    public static <T> Lazy<T> of(T value) {
        return new Lazy<>(() -> value);
    }

}
