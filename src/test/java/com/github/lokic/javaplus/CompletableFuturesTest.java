package com.github.lokic.javaplus;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static com.github.lokic.javaplus.CompletableFutures.Fors.For;
import static com.github.lokic.javaplus.CompletableFutures.Fors.Yield;

public class CompletableFuturesTest {

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

}