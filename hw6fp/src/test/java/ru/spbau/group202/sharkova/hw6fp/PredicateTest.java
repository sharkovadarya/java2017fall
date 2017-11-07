package ru.spbau.group202.sharkova.hw6fp;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testNot() {
        Predicate<Integer> isEven = x -> (x & 1) == 0;

        assertEquals(false, isEven.not().apply(4));
        assertEquals(true, isEven.not().apply(3));


        assertEquals(false, Predicate.ALWAYS_TRUE.not().apply(4));
        assertEquals(false, Predicate.ALWAYS_TRUE.not().apply(2.87));
        assertEquals(false, Predicate.ALWAYS_TRUE.not().apply("a"));

        assertEquals(true, Predicate.ALWAYS_FALSE.not().apply(4));
        assertEquals(true, Predicate.ALWAYS_FALSE.not().apply(2.87));
        assertEquals(true, Predicate.ALWAYS_FALSE.not().apply("a"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAnd() {
        Predicate<Integer> isEven = x -> (x & 1) == 0;
        Predicate<Integer> isDivisibleByFour = x -> (x % 4) == 0;

        assertEquals(true, isEven.and(isDivisibleByFour).apply(4, 4));
        assertEquals(false, isEven.and(isDivisibleByFour).apply(6, 6));
        assertEquals(false, isEven.and(isDivisibleByFour).apply(3, 4));
        assertEquals(false, isEven.and(isDivisibleByFour).apply(3, 6));

        assertEquals(true, isDivisibleByFour.and(isEven).apply(4, 2));
        assertEquals(true, isDivisibleByFour.and(isEven).apply(4, 4));
        assertEquals(false, isDivisibleByFour.and(isEven).apply(6, 2));
        assertEquals(false, isDivisibleByFour.and(isEven).apply(4, 3));

        assertEquals(true, isEven.and(Predicate.ALWAYS_TRUE).apply(2, 2));
        assertEquals(true, isEven.and(Predicate.ALWAYS_TRUE).apply(2, 3));
        assertEquals(false, isEven.and(Predicate.ALWAYS_TRUE).apply(3, 2));
        assertEquals(false, isEven.and(Predicate.ALWAYS_TRUE).apply(3, 3));

        assertEquals(false, isEven.and(Predicate.ALWAYS_FALSE).apply(2, 2));
        assertEquals(false, isEven.and(Predicate.ALWAYS_FALSE).apply(2, 3));
        assertEquals(false, isEven.and(Predicate.ALWAYS_FALSE).apply(3, 2));
        assertEquals(false, isEven.and(Predicate.ALWAYS_FALSE).apply(3, 3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testOr() {
        Predicate<Integer> isEven = x -> (x & 1) == 0;
        Predicate<Integer> isDivisibleByFour = x -> (x % 4) == 0;

        assertEquals(true, isEven.or(isDivisibleByFour).apply(4, 4));
        assertEquals(true, isEven.or(isDivisibleByFour).apply(6, 6));
        assertEquals(true, isEven.or(isDivisibleByFour).apply(3, 4));
        assertEquals(false, isEven.or(isDivisibleByFour).apply(3, 6));

        assertEquals(true, isDivisibleByFour.or(isEven).apply(4, 2));
        assertEquals(true, isDivisibleByFour.or(isEven).apply(4, 4));
        assertEquals(true, isDivisibleByFour.or(isEven).apply(6, 2));
        assertEquals(true, isDivisibleByFour.or(isEven).apply(4, 3));

        assertEquals(true, isEven.or(Predicate.ALWAYS_TRUE).apply(2, 2));
        assertEquals(true, isEven.or(Predicate.ALWAYS_TRUE).apply(2, 3));
        assertEquals(true, isEven.or(Predicate.ALWAYS_TRUE).apply(3, 2));
        assertEquals(true, isEven.or(Predicate.ALWAYS_TRUE).apply(3, 3));

        assertEquals(true, isEven.or(Predicate.ALWAYS_FALSE).apply(2, 2));
        assertEquals(true, isEven.or(Predicate.ALWAYS_FALSE).apply(2, 3));
        assertEquals(false, isEven.or(Predicate.ALWAYS_FALSE).apply(3, 2));
        assertEquals(false, isEven.or(Predicate.ALWAYS_FALSE).apply(3, 3));

    }

}