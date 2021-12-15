package com.github.lokic.javaplus.validation;


import com.github.lokic.javaplus.Either;

import java.util.List;
import java.util.stream.Collectors;

public interface Validation<E, T> {

    Either<E, T> validatedBy(T entity);

    static <E, T> Either<E, T> failFast(List<Validation<E, T>> validations, T entity) {
        return validations.stream()
                .reduce(Either.right(entity),
                        (a, b) -> a.flatMap(b::validatedBy),
                        (a, b) -> {
                            throw new IllegalStateException("not support combiner");
                        });
    }

    static <E, T> Either<List<E>, T> failOver(List<Validation<E, T>> validations, T entity) {
        List<E> lefts = validations.stream()
                .map(validation -> validation.validatedBy(entity))
                .filter(Either::isLeft)
                .map(Either::getLeft)
                .collect(Collectors.toList());
        if (!lefts.isEmpty()) {
            return Either.left(lefts);
        }
        return Either.right(entity);
    }


}
