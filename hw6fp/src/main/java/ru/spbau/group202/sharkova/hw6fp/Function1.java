package ru.spbau.group202.sharkova.hw6fp;

import org.jetbrains.annotations.NotNull;

/**
 * This interface implements a one-argument function.
 * Supported: composition of two one-argument functions,
 * application to a provided argument.
 * @param <T> function domain type parameter
 * @param <U> function range type parameter
 */
public interface Function1<T, U> {

    /**
     * This method is a function composition
     * with the provided g function serving as
     * an outer function.
     * @param g outer function of the composition
     * @param <K> outer function range type parameter
     * @return function h such that h(x) = g(f(x))
     */

    default <K> Function1<T, K> compose(@NotNull final Function1<? super U, ? extends K> g) {
        return t -> g.apply(Function1.this.apply(t));
    }

    /**
     * This method applies the function
     * to the provided argument.
     * @param argument function argument
     */
    U apply(T argument);
}
