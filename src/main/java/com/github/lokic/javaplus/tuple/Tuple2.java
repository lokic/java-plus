package com.github.lokic.javaplus.tuple;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor(access = AccessLevel.MODULE)
@ToString
@EqualsAndHashCode
public class Tuple2<T1, T2> implements Tuple, Serializable {

    private static final long serialVersionUID = -1655443004987467988L;

    private final T1 t1;
    private final T2 t2;

}
