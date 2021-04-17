package com.github.lokic.javaplus;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.github.lokic.javaplus.Consumers.toRunnable;


public class OptionalsTest {

    @Test
    public void accept_should_executeConsumer_when_optionalHasValue() {
        SpyMethod spyMethod = Mockito.mock(SpyMethod.class);
        Optionals.ifPresentOrElse(spyMethod::consumer, spyMethod::run)
                .accept(Optional.of("123"));

        Mockito.verify(spyMethod, Mockito.only()).consumer(Mockito.eq("123"));
        Mockito.verify(spyMethod, Mockito.never()).run();

    }

    @Test
    public void accept_should_runnable_when_optionalHasValue() {
        SpyMethod spyMethod = Mockito.mock(SpyMethod.class);
        Optional.of("123")
                .map(toRunnable(spyMethod::consumer))
                .orElse(spyMethod::run)
                .run();

        Mockito.verify(spyMethod, Mockito.only()).consumer(Mockito.eq("123"));
        Mockito.verify(spyMethod, Mockito.never()).run();
    }

    @Test
    public void accept_should_executeRun_when_optionalIsEmpty() {
        SpyMethod spyMethod = Mockito.mock(SpyMethod.class);
        Optionals.ifPresentOrElse(spyMethod::consumer, spyMethod::run)
                .accept(Optional.empty());

        Mockito.verify(spyMethod, Mockito.never()).consumer(Mockito.anyString());
        Mockito.verify(spyMethod, Mockito.only()).run();

    }

    @Test
    public void accept_should_runnable_when_optionalIsEmpty() {
        SpyMethod spyMethod = Mockito.mock(SpyMethod.class);
        Optional.<String>empty()
                .map(toRunnable(spyMethod::consumer))
                .orElse(spyMethod::run)
                .run();

        Mockito.verify(spyMethod, Mockito.never()).consumer(Mockito.anyString());
        Mockito.verify(spyMethod, Mockito.only()).run();
    }

    public static class SpyMethod {

        public void consumer(String x) {

        }

        public void run() {

        }
    }
}