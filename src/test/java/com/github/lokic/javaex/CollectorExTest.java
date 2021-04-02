package com.github.lokic.javaex;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Stream;

public class CollectorExTest {

    @Test
    public void test_distinctFirstPut() {
        Assertions.assertThat(
                Stream.of("A", "B", "A", "C")
                        .collect(CollectorEx.Distinct.distinctFirstPut()))
                .containsExactly("A", "B", "C");
    }

    @Test
    public void test_distinctLastPut() {
        Assertions.assertThat(
                Stream.of("A", "B", "A", "C")
                        .collect(CollectorEx.Distinct.distinctLastPut()))
                .containsExactly("B", "A", "C");
    }

    @Test
    public void test_distinctFirstPutByKey() {
        Assertions.assertThat(
                Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                        .collect(CollectorEx.Distinct.distinctFirstPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("C", "C1"));
    }

    @Test
    public void test_distinctLastPutByKey() {
        Assertions.assertThat(
                Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                        .collect(CollectorEx.Distinct.distinctLastPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"));
    }

    @Data
    @AllArgsConstructor
    static class DataInfo {
        private String key;
        private String value;
    }

}