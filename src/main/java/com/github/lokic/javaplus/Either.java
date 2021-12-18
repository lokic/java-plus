package com.github.lokic.javaplus;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Function;

public interface Either<L, R> {

    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    L getLeft();

    R getRight();

    boolean isLeft();

    boolean isRight();

    @SuppressWarnings("unchecked")
    default <U> Either<L, U> flatMap(Function<? super R, ? extends Either<L, ? extends U>> mapper) {
        if (isRight()) {
            return (Either<L, U>) mapper.apply(getRight());
        } else {
            return (Either<L, U>) this;
        }
    }

    @ToString
    @EqualsAndHashCode
    final class Left<L, R> implements Either<L, R>, Serializable {

        private static final long serialVersionUID = 1L;

        private final L value;

        private Left(L value) {
            this.value = value;
        }

        @Override
        public L getLeft() {
            return value;
        }

        @Override
        public R getRight() {
            throw new NoSuchElementException("getRight() at Left");
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }
    }


    @ToString
    @EqualsAndHashCode
    final class Right<L, R> implements Either<L, R>, Serializable {

        private static final long serialVersionUID = 1L;

        private final R value;

        private Right(R value) {
            this.value = value;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException("getLeft() at Right");
        }

        @Override
        public R getRight() {
            return value;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }
    }

}
