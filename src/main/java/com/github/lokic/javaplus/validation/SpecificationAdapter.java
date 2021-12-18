package com.github.lokic.javaplus.validation;

import com.github.lokic.javaplus.specification.CompositeSpecification;
import com.github.lokic.javaplus.specification.Specification;

public class SpecificationAdapter<E, T> extends CompositeSpecification<T> implements
        Specification<T> {

    private final Validation<E, T> validation;

    public SpecificationAdapter(Validation<E, T> validation) {
        this.validation = validation;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return validation.validatedBy(entity).isRight();
    }

}
