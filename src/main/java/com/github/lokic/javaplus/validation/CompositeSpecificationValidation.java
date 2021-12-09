package com.github.lokic.javaplus.validation;

import com.github.lokic.javaplus.specification.CompositeSpecification;

public abstract class CompositeSpecificationValidation<E, T> extends
    CompositeSpecification<T> implements Validation<E, T> {

    @Override
    public boolean isSatisfiedBy(T entity) {
        return validatedBy(entity).isRight();
    }
}
