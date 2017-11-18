package ru.spbau.group202.sharkova.hw6fp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class implements various static methods
 * to apply functions to collections,
 * such as map(), filter(), takeWhile(), takeUnless(), foldl(), foldr().
 */
public class Collections {

    /**
     * This method applies the given function to each element
     * of the provided iterable structure
     * and returns a list containing results of those applications.
     * @param f function to be applied
     * @param a the function will be applied to this iterable structure elements
     * @param <T> iterable elements type parameter
     * @param <U> resulting list type parameter
     * @return a list of applications result values
     */
    public static <T, U> List<U> map(@NotNull final Function1<? super T, ? extends U> f, @NotNull final Iterable<T> a) {
        ArrayList<U> result = new ArrayList<>();
        a.forEach(elem -> result.add(f.apply(elem)));
        return result;
    }

    /**
     * This method filters the given iterable structure by the predicate
     * and return a list of elements which satisfy the predicate.
     * @param p predicate to evaluate each element
     * @param a iterable structure; contains elements to be filtered
     * @param <T> iterable elements type parameter
     * @return list of elements which satisfy the predicate
     */
    public static <T> List<T> filter(@NotNull final Predicate<? super T> p, @NotNull final Iterable<T> a) {
        ArrayList<T> result = new ArrayList<>();
        a.forEach(elem -> {
            if (p.apply(elem))
                result.add(elem);
        });
        return result;
    }

    /**
     * This method return a list of values constructed by taking all elements
     * up to the first one that does not satisfy the predicate.
     * @param p predicate to evaluate each element
     * @param a iterable structure;
     *          contains elements to be evaluated by the predicate
     * @param <T> iterable elements type parameter
     * @return a list of all values satisfying the predicate
     *         up to the first value that does not (not inclusively)
     */
    public static <T> List<T> takeWhile(@NotNull final Predicate<? super T> p, @NotNull final Iterable<T> a) {
        ArrayList<T> result = new ArrayList<>();
        for (T element : a) {
            if (!p.apply(element))
                return result;
            result.add(element);
        }

        return result;
    }

    /**
     * This method return a list of values constructed by taking all elements
     * up to the first one that satisfies the predicate.
     * @param p predicate to evaluate each element
     * @param a iterable structure;
     *          contains elements to be evaluated by the predicate
     * @param <T> iterable elements type parameter
     * @return a list of all values not satisfying the predicate
     *         up to the first value that does(not inclusively)
     */
    public static <T> List<T> takeUnless(@NotNull final Predicate<? super T> p, @NotNull final Iterable<T> a) {
        return takeWhile(p.not(), a);
    }

    /**
     * This method performs left-associative folding of a collection.
     * @param f two-argument function for folding
     * @param initialValue initial value for folding
     * @param values collection of elements
     * @param <T> iterable elements type parameter
     * @param <K> initial and resulting values type parameter
     * @return result of the folding (same type as initial value)
     */
    public static <T, K> K foldl(@NotNull final Function2<? super K, ? super T, ? extends K> f,
                                 final K initialValue,
                                 @NotNull final Collection<T> values) {
        K result = initialValue;

        for (T value : values) {
            result = f.apply(result, value);
        }

        return result;
    }

    /**
     * This method performs right-associative folding of a collection.
     * @param f two-argument function for folding
     * @param initialValue initial value for folding
     * @param values collection of elements
     * @param <T> iterable elements type parameter
     * @param <K> initial and resulting values type parameter
     * @return result of the folding (same type as initial value)
     */
    public static <T, K> K foldr(@NotNull final Function2<? super T, ? super K, ? extends K> f,
                                 final K initialValue,
                                 @NotNull final Collection<T> values) {
        K result = initialValue;
        List<T> reversedValues = new ArrayList<>(values);
        // "java.util." might be a bit ugly but it's to resolve name conflicts
        java.util.Collections.reverse(reversedValues);

        for (T value : reversedValues) {
            result = f.apply(value, result);
        }

        return result;
    }

}
