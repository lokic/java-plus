package com.github.lokic.javaplus.functional.sneakythrows;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SneakyThrowFunctionalTest {

    @Test
    public void cast() {
        List<String> re = Stream.of(1, 2)
                .map(SneakyThrowFunctional.cast(this::toStr))
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList("1", "2"), re);
    }


    public String toStr(Integer x) throws IOException {
        return String.valueOf(x);
    }
}