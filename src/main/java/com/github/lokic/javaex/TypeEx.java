package com.github.lokic.javaex;

public class TypeEx {

    /**
     * 更加优雅得进行类型转换。
     * <p>
     * 一些泛型类，如果需要进行转换，
     * 使用 {@link Class#cast(Object)} 将无能为力，
     * 使用 {@code TypeEx#cast(Object)} 可以很方便地支持泛型类的转换。
     * <p>
     * <p>
     * 如 {@code Optional<String>} 的转换，
     * 通过 {@code Optional.class#cast(Object)} 可以转换成Optional，但是泛型的String无法体现；
     * 通过 {@code TypeEx.<Optional<String>>cast(Object)} 可以很方便地支持 {@code Optional<String>} 的转换。
     * <p>
     * Note:
     * 在赋值给变量的时候，由于有上下文的类型推导，可以很方便的写成如下：
     * <pre>{@code
     *       Object o = ...;
     *
     *       // 之前的写法
     *       Optional<String> opt = (Optional<String>) o;
     *
     *       // 现在的写法
     *       Optional<String> opt = TypeEx.cast(o);
     * }</pre>
     * 可以看到，新的写法规避了 {@code Optional<String>} 的2次出现，其可以通过需要赋值的类型进行类型推导
     *
     * @param o
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T t) {
        return (Class<T>) t.getClass();
    }
}
