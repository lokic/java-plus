package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.sneakythrows.SneakyThrowFunctional;
import com.github.lokic.javaplus.tuple.Tuple;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class TupleFunctionalTest {

    @Test
    public void cast() {
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(TupleFunctional.cast(this::toStr));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    @Test
    public void cast_use_SneakyThrowFunction2() {
        // SneakyThrowFunction2 extends TupleFunction2
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(SneakyThrowFunctional.cast(this::toStrThrow));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    @Test
    public void cast_use_SneakyThrowFunction1() {
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(SneakyThrowFunctional.cast(x -> x.getT1() + x.getT2()));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    public String toStr(String a, Integer b) {
        return a + b;
    }

    public String toStrThrow(String a, Integer b) throws Exception{
        return a + b;
    }
}