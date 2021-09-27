package com.github.lokic.javaplus;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.github.lokic.javaplus.CompletableFutures.Fors.For;
import static com.github.lokic.javaplus.CompletableFutures.Fors.Yield;

public class CompletableFuturesTest {

    @Test
    public void test_list_sequence() {
        List<CompletableFuture<String>> li = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            li.add(CompletableFuture.completedFuture(String.valueOf(i)));
        }
        Assert.assertEquals(Lists.newArrayList("0", "1", "2", "3", "4"), CompletableFutures.sequence(li).join());
    }


    @Test
    public void test_map_sequence() {
        Map<Integer, CompletableFuture<String>> map = new HashMap<>();
        Map<Integer, String> expect = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, CompletableFuture.completedFuture(String.valueOf(i)));
            expect.put(i, String.valueOf(i));
        }
        Assert.assertEquals(expect, CompletableFutures.sequence(map).join());
    }

    @Test
    public void test_For_success() {
        CompletableFuture<String> r = CompletableFuture.completedFuture("1")
                .thenCompose(For(t1 -> CompletableFuture.completedFuture("2")))
                .thenCompose(For((t1, t2) -> CompletableFuture.completedFuture("3")))
                .thenCompose(For((t1, t2, t3) -> CompletableFuture.completedFuture("4")))
                .thenApply(Yield((t1, t2, t3, t4) -> t1 + t2 + t3 + t4));
        Assert.assertEquals("1234", r.join());
    }

    @Test
    public void test_For_exception() {
        CompletableFuture<String> r = CompletableFuture.completedFuture("1")
                .thenCompose(For(t1 -> CompletableFuture.completedFuture("2")))
                .thenCompose(For((t1, t2) -> {
                    CompletableFuture<String> f = new CompletableFuture<>();
                    f.completeExceptionally(new RuntimeException("test"));
                    return f;
                }))
                .thenCompose(For((t1, t2, t3) -> CompletableFuture.completedFuture("4")))
                .thenApply(Yield((t1, t2, t3, t4) -> t1 + t2 + t3 + t4));

        Assert.assertTrue(r.isCompletedExceptionally());
    }

    @Test(expected = Exception.class)
    public void test_getOrElseSneakyThrow_exception() {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.completeExceptionally(new Exception());
        CompletableFutures.getOrElseSneakyThrow(f);
    }

    @Test
    public void test_getOrElseSneakyThrow() {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.complete("123");
        Assert.assertEquals("123", CompletableFutures.getOrElseSneakyThrow(f));
    }


    @Test(expected = IOException.class)
    public void test_getOrElseThrow_exception() throws Throwable {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.completeExceptionally(new IOException());
        CompletableFutures.getOrElseThrow(f);
    }

    @Test
    public void test_getOrElseThrow() throws Throwable {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.complete("123");
        Assert.assertEquals("123", CompletableFutures.getOrElseThrow(f));
    }


    @Test(expected = IllegalStateException.class)
    public void test_getOrElseThrow_exceptionProvider_exception() {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.completeExceptionally(new IOException());
        CompletableFutures.getOrElseThrow(f, IllegalStateException::new);
    }

    @Test
    public void test_supply() {
        Assert.assertEquals("123", CompletableFutures.supply(() -> "123").join());
        Assert.assertTrue(CompletableFutures.supply(() -> {
            throw new IllegalStateException();
        }).isCompletedExceptionally());
    }

    @Test
    public void test_call() {
        Assert.assertEquals("123", CompletableFutures.call(() -> "123").join());
        Assert.assertTrue(CompletableFutures.call(() -> {
            throw new IOException();
        }).isCompletedExceptionally());
    }

}