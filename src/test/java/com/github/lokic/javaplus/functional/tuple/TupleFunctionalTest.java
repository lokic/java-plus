package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.sneakythrows.SneakyThrowsFunctional;
import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TupleFunctionalTest {

    @Test
    public void cast() {
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(TupleFunctional.function(this::toStr));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    @Test
    public void cast_use_SneakyThrowFunction2() {
        // SneakyThrowFunction2 extends TupleFunction2
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(SneakyThrowsFunctional.function(this::toStrThrow));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    @Test
    public void cast_use_SneakyThrowFunction1() {
        Optional<String> res = Optional.of(Tuple.of("A", 1))
                .map(SneakyThrowsFunctional.function(x -> x.getT1() + x.getT2()));
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals("A1", res.get());
    }

    @Test
    public void cast_use_TuplePredicate2() {
        Tuple2<String, Integer> t1 = Tuple.of("A", 1);
        Tuple2<String, Integer> t2 = Tuple.of("B", 2);
        Tuple2<String, Integer> t3 = Tuple.of("C", 3);

        Assert.assertEquals(Lists.newArrayList(t2, t3),
                Stream.of(t1, t2, t3)
                        .filter(TupleFunctional.predicate((a, b) -> b >= 2))
                        .collect(Collectors.toList()));

    }

    public String toStr(String a, Integer b) {
        return a + b;
    }

    public String toStrThrow(String a, Integer b) throws Exception {
        return a + b;
    }
}