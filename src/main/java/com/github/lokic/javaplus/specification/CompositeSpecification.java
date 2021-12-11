package com.github.lokic.javaplus.specification;

public abstract class CompositeSpecification<T> implements Specification<T> {

    @Override
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    @Override
    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }
}
