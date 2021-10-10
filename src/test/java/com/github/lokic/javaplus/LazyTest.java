package com.github.lokic.javaplus;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

public class LazyTest {

    @Test
    public void test() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "ABC";
            }
        };
        Supplier<String> spy = Mockito.spy(supplier);

        Lazy<String> lazy = Lazy.of(spy);

        String res1 = lazy.get();
        String res2 = lazy.get();

        Assert.assertEquals("ABC", res1);
        Assert.assertEquals("ABC", res2);

        Mockito.verify(spy, Mockito.timeout(1)).get();

    }

}