package com.github.lokic.javaplus.join;

import java.util.stream.Stream;

public class Join {

    public static <T> JoinStream<T> stream(Stream<T> stream) {
        return new JoinStream<>(stream);
    }

}
