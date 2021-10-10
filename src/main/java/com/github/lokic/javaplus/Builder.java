package com.github.lokic.javaplus;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.consumer.Consumer3;
import com.github.lokic.javaplus.functional.consumer.Consumer4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 对POJO提供的通用builder，也可以作为对POJO提供fluent风格的setter
 */
public class Builder<T> {

    private final Supplier<T> instanceSupplier;

    private final List<Consumer<T>> modifiers;

    private Builder(Supplier<T> instanceSupplier) {
        this.instanceSupplier = instanceSupplier;
        this.modifiers = new ArrayList<>();
    }

    public static <T> Builder<T> of(Supplier<T> instanceSupplier) {
        return new Builder<>(instanceSupplier);
    }

    public <P1> Builder<T> with(Consumer2<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(Consumer3<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3> Builder<T> with(Consumer4<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T instance = instanceSupplier.get();
        modifiers.forEach(modifier -> modifier.accept(instance));
        return instance;
    }

}
