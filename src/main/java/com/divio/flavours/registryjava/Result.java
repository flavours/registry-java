package com.divio.flavours.registryjava;

import java.util.function.Function;

public abstract class Result<E, T> {
    private Result() {}

    public abstract boolean isSuccess();

    public final boolean isFailure() {
        return !isSuccess();
    }

    public static <E, T> Result<E, T> success(final T value) {
        return new Success<>(value);
    }

    public static <E, T> Result<E, T> failure(final E error) {
        return new Failure<>(error);
    }

    public final <U> U handle(
            final Function<? super E, ? extends U> onFailure,
            final Function<? super T, ? extends U> onSuccess
    ) {
        if (isSuccess()) {
            return onSuccess.apply(((Success<E, T>) this).getValue());
        } else {
            return onFailure.apply(((Failure<E, T>) this).getError());
        }
    }

    private static final class Success<E, T> extends Result<E, T> {
        private final T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        public T getValue() {
            return this.value;
        }
    }

    private static final class Failure<E, T> extends Result<E, T> {
        private final E error;

        private Failure(E error) {
            this.error = error;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        public E getError() {
            return this.error;
        }
    }
}
