package com.github.lokic.javaplus.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T entity);

    Specification<T> and(Specification<T> specification);

    Specification<T> or(Specification<T> specification);

    Specification<T> not(Specification<T> specification);
}
