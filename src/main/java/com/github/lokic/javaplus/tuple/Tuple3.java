package com.github.lokic.javaplus.tuple;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.MODULE)
@ToString
@EqualsAndHashCode
public class Tuple3<T1, T2, T3> implements Tuple, Serializable {

    private static final long serialVersionUID = -7153679497399532428L;

    private final T1 t1;
    private final T2 t2;
    private final T3 t3;

}
