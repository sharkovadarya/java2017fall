package ru.spbau.group202.sharkova.hw6fp;

import org.jetbrains.annotations.NotNull;

/**
 * This interface implements a function of two arguments
 * with generic types. It supports application to a given argument,
 * composition with a Function1 object, argument binding
 * and currying.
 * @param <T> function first argument type parameter
 * @param <U> function second argument type parameter
 * @param <K> function range type parameter
 */
public interface Function2<T, U, K> {

    /**
     * This method is a composition of two functions:
     * it acts as g(f(x, y))) where f is the Function2 class object
     * and g is a Function1 class object.
     * @param g one-argument function,
     *          the outer function of the composition
     * @param <V> outer function range type parameter
     * @return function of one argument that represents g(f(x, y))
     */
    default <V> Function2<T, U, V> compose(@NotNull final Function1<? super K, ? extends V> g) {
        return (t, u) -> g.apply(Function2.this.apply(t, u));
    }

    /**
     * This method binds the first argument with a provided value.
     * @param newFirstArgument argument to be bound
     * @return new function object with a bound first argument
     */
    default Function1<U, K> bind1(T newFirstArgument) {
        return u -> Function2.this.apply(newFirstArgument, u);
    }

    /**
     * This method binds the second argument with a provided value.
     * @param newSecondArgument argument to be bound
     * @return new function object with a bound second argument
     */
    default Function1<T, K> bind2(U newSecondArgument) {
        return t -> Function2.this.apply(t, newSecondArgument);
    }

    /**
     * This method implements currying:
     * instead of a two-argument function we get
     * two one-argument functions.
     * @return function g such that g(x)(y) == f.apply(x, y)
     */
    default Function1<T, Function1<U, K>> curry() {
        return this::bind1;
    }

    /**
     * This method applies the function to provided arguments.
     * @param arg1 first argument
     * @param arg2 second argument
     * @return result of function application
     */
    K apply(T arg1, U arg2);
}
