package com.divio.flavours.registryjava.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>Simple utility class to enable railway-oriented programming and values signalling either success or failure.
 * Some ideas are borrowed from <a href="https://github.com/vavr-io/vavr">https://github.com/vavr-io/vavr</a>.</p>
 *
 * <p>This enables code like:</p>
 * <pre>{@code
 * public Result<IOException, String> readInput();
 * public Result<String, PhoneNumber> parsePhoneNumber(String input);
 *
 * Result<String, Contact> = readInput()
 *          .leftMap(exception -> exception.getMessage())
 *          .flatMap(input -> parsePhoneNumber(input))
 *          .map(phoneNumber -> new Contact("John Doe", phoneNumber));
 *          }</pre>
 *
 * @param <E> The failure type.
 * @param <T> The success type.
 */
public abstract class Result<E, T> {
    private Result() {
    }

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

    public static <T> Result<Throwable, T> ofTry(final Supplier<T> supplier) {
        try {
            return Result.success(supplier.get());
        } catch (Throwable t) {
            return Result.failure(t);
        }
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

    public final <U> Result<E, U> map(final Function<T, U> mapper) {
        return handle(
                Result::failure,
                value -> Result.success(mapper.apply(value))
        );
    }

    @SuppressWarnings("unchecked")
    public final <U> Result<E, U> flatMap(final Function<? super T, ? extends Result<E, ? extends U>> mapper) {
        if (isSuccess()) {
            var cast = (Success<E, T>) this;
            return (Result<E, U>) mapper.apply(cast.getValue());
        } else {
            return (Result<E, U>) this;
        }
    }

    @SuppressWarnings("unchecked")
    public final <F> Result<F, T> mapFailure(final Function<? super E, ? extends F> mapper) {
        if (isSuccess()) {
            return (Result<F, T>) this;
        } else {
            var failure = (Failure<E, T>) this;
            return Result.failure(mapper.apply(failure.getError()));
        }
    }

    public final Optional<T> toOptional() {
        return handle(error -> Optional.empty(), Optional::ofNullable);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Success<?, ?> success = (Success<?, ?>) o;
            return Objects.equals(value, success.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), value);
        }

        @Override
        public String toString() {
            return "Success{" +
                    "value=" + value +
                    '}';
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Failure<?, ?> failure = (Failure<?, ?>) o;
            return Objects.equals(error, failure.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), error);
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "error=" + error +
                    '}';
        }
    }
}
