package com.github.lokic.javaplus.tuple;

import lombok.*;

import java.io.Serializable;


@Getter
@AllArgsConstructor(access = AccessLevel.MODULE)
@ToString
@EqualsAndHashCode
public class Tuple4<T1, T2, T3, T4> implements Tuple, Serializable {

    private static final long serialVersionUID = 1263728287960991776L;
    private final T1 t1;
    private final T2 t2;
    private final T3 t3;
    private final T4 t4;

}

