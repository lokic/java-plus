package com.github.lokic.javaplus.specification;

import com.github.lokic.javaplus.Either;
import com.github.lokic.javaplus.validation.Validation;

import java.util.function.Function;

public class ValidationAdapter<E, T> implements Validation<E, T> {

    private final Specification<T> specification;

    private final Function<T, E> leftMapper;

    public ValidationAdapter(Specification<T> specification, Function<T, E> leftMapper) {
        this.specification = specification;
        this.leftMapper = leftMapper;
    }

    @Override
    public Either<E, T> validatedBy(T entity) {
        if (specification.isSatisfiedBy(entity)) {
            return Either.right(entity);
        } else {
            return Either.left(leftMapper.apply(entity));
        }
    }
}
