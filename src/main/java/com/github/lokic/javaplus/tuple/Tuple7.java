package com.github.lokic.javaplus.tuple;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.MODULE)
@ToString
@EqualsAndHashCode
public class Tuple7<T1, T2, T3, T4, T5, T6, T7> implements Tuple, Serializable {

    private static final long serialVersionUID = -2946455357836959234L;

    private final T1 t1;
    private final T2 t2;
    private final T3 t3;
    private final T4 t4;
    private final T5 t5;
    private final T6 t6;
    private final T7 t7;

}