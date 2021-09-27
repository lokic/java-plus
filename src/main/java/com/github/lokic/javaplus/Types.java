package com.github.lokic.javaplus;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

public class Types {

    /**
     * 更加优雅得进行类型转换。
     * <p>
     * 一些泛型类，如果需要进行转换，
     * 使用 {@link Class#cast(Object)} 将无能为力，
     * 使用 {@code Types#cast(Object)} 可以很方便地支持泛型类的转换。
     * <p>
     * <p>
     * 如 {@code Optional<String>} 的转换，
     * 通过 {@code Optional.class#cast(Object)} 可以转换成Optional，但是泛型的String无法体现；
     * 通过 {@code Types.<Optional<String>>cast(Object)} 可以很方便地支持 {@code Optional<String>} 的转换。
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
     *       Optional<String> opt = Types.cast(o);
     * }</pre>
     * 可以看到，新的写法规避了 {@code Optional<String>} 的2次出现，其可以通过需要赋值的类型进行类型推导
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T t) {
        return (Class<T>) t.getClass();
    }

    /**
     * 获取object对应clazz的泛型。如果要使用lambda的传入，则clazz必须实现Serializable
     */
    public static <E> Class<E> getGeneric(Object object, Class<?> clazz) {
        if (isLambda(object)) {
            return Types.cast(getGenericForLambda(object));
        } else {
            return Types.cast(getGenericForObject(object, clazz));
        }
    }

    private static boolean isLambda(Object object) {
        String functionClassName = object.getClass().getName();
        int lambdaMarkerIndex = functionClassName.indexOf("$$Lambda$");
        return lambdaMarkerIndex != -1;
    }

    private static Class<?> getGenericForObject(Object object, Class<?> clazz) {
        Type[] types = object.getClass().getGenericInterfaces();
        for (Type type : types) {
            if(type instanceof ParameterizedType) {
                if (((ParameterizedType) type).getRawType() == clazz) {
                    return (Class<?>) ((ParameterizedType)type).getActualTypeArguments()[0];
                }
            }
        }
        throw new IllegalStateException("object not a instance of " + clazz.getTypeName());
    }

    private static Class<?> getGenericForLambda(Object lambda) {
        return (Class<?>) method(lambda).getParameters()[0].getParameterizedType();
    }

    private static Method method(Object lambda) {
        SerializedLambda serialized = serialized(lambda);
        Class<?> containingClass = getContainingClass(serialized);
        return Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), serialized.getImplMethodName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("clazz not implement Serializable"));
    }

    @SneakyThrows
    private static SerializedLambda serialized(Object lambda) {
        Method writeMethod = lambda.getClass().getDeclaredMethod("writeReplace");
        writeMethod.setAccessible(true);
        return (SerializedLambda) writeMethod.invoke(lambda);
    }

    @SneakyThrows
    private static Class<?> getContainingClass(SerializedLambda lambda) {
        String className = lambda.getImplClass().replaceAll("/", ".");
        return Class.forName(className);
    }

}
