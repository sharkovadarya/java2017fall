package ru.spbau.group202.sharkova.hw6fp;

import org.jetbrains.annotations.NotNull;

/**
 * This interface implements a special case
 * of one-argument function - a predicate.
 * It provides two constant predicates (ALWAYS_TRUE, ALWAYS_FALSE)
 * and logical conjunction, disjunction and inversion.
 */
public interface Predicate<T> extends Function1<T, Boolean>  {

    /**
     * The two following fields correspond to constant predicates
     * that always return (true) and (false) values respectively.
     */
    Predicate ALWAYS_TRUE = t -> true;
    Predicate ALWAYS_FALSE = t -> false;

    /**
     * This method returns logical conjunction of given predicates.
     * The evaluations are performed lazily.
     * @param g the second argument of logical conjunction
     * @param <U> the predicate argument type parameter
     * @return two-argument function serving as logical conjunction
     */
    default <U> Function2<T, U, Boolean> and(@NotNull final Predicate<U> g) {
        return (t, u) -> Predicate.this.apply(t) && g.apply(u);
    }

    /**
     * This method returns logical disjunction of given predicates.
     * The evaluations are performed lazily.
     * @param g the second argument of logical disjunction
     * @param <U> the predicate argument type parameter
     * @return two-argument function serving as logical disjunction
     */
    default <U> Function2<T, U, Boolean> or(@NotNull final Predicate<U> g) {
        return (t, u) -> Predicate.this.apply(t) || g.apply(u);
    }

    /**
     * This method serves as logical inversion.
     * @return predicate returning values that are opposite
     * to values of the original predicate on every argument.
     */
    default Predicate<T> not() {
        return t -> !Predicate.this.apply(t);
    }
}
