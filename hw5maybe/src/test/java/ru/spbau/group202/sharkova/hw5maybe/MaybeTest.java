package ru.spbau.group202.sharkova.hw5maybe;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * This class tests correctness of Maybe class methods
 * both for objects that contain a value
 * and for objects that contain none.
 * Tested methods: get(), isPresent(), map().
 */
public class MaybeTest {

    /**
     * This test checks correctness of the get() method
     * for Maybe object that contains values.
     * In this case the get() method should not throw any exceptions.
     */
    @Test
    public void testJustGetValue() {
        Maybe<Integer> maybe1 = Maybe.just(4);
        try {
            assertEquals(Integer.valueOf(4), maybe1.get());
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Maybe<Double> maybe2 = Maybe.just(Double.valueOf(3.14));
        try {
            assertEquals(Double.valueOf(3.14), maybe2.get());
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Maybe<String> maybe3 = Maybe.just("string");
        try {
            assertEquals("string", maybe3.get());
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Maybe<Maybe<Integer>> maybe4 = Maybe.just(maybe1);
        try {
            assertEquals(maybe1, maybe4.get());
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }
    }

    /**
     * This test checks correctness of the get() method
     * for Maybe objects that contain no value.
     * In this case the get() method should throw an exception.
     */
    @Test
    public void testNothingGetValue() {
        Maybe maybe1 = Maybe.nothing();
        Maybe<Integer> maybe2 = Maybe.nothing();
        Maybe<Maybe<String>> maybe3 = Maybe.nothing();

        try {
            maybe1.get();
            fail("NullValueException not thrown when it should've been");
        } catch (NullValueException e) {
            // not necessary
            assertTrue(true);
        }

        try {
            maybe2.get();
            fail("NullValueException not thrown when it should've been");
        } catch (NullValueException e) {
            assertTrue(true);
        }

        try {
            maybe3.get();
            fail("NullValueException not thrown when it should've been");
        } catch (NullValueException e) {
            assertTrue(true);
        }
    }

    /**
     * This method checks correctness of the isPresent() method
     * for Maybe objects that contain values.
     * Expected result: always true
     */
    @Test
    public void testJustIsPresent() {
        Maybe<Integer> maybe1 = Maybe.just(4);
        Maybe<Double> maybe2 = Maybe.just(Double.valueOf(3.14));
        Maybe<String> maybe3 = Maybe.just("string");
        Maybe<Maybe<Integer>> maybe4 = Maybe.just(maybe1);
        Maybe<Maybe<String>> maybe5 = Maybe.just(Maybe.nothing());

        assertEquals(true, maybe1.isPresent());
        assertEquals(true, maybe2.isPresent());
        assertEquals(true, maybe3.isPresent());
        assertEquals(true, maybe4.isPresent());
        assertEquals(true, maybe5.isPresent());
    }

    /**
     * This method checks correctness of the isPresent() method
     * for Maybe objects that contain no values.
     * Expected result: always false.
     */
    @Test
    public void testNothingIsPresent() {
        Maybe<Integer> maybe1 = Maybe.nothing();
        Maybe maybe2 = Maybe.nothing();
        Maybe<Maybe<Integer>> maybe3 = Maybe.nothing();

        assertEquals(false, maybe1.isPresent());
        assertEquals(false, maybe2.isPresent());
        assertEquals(false, maybe3.isPresent());
    }

    /**
     * This method checks correctness of the map() method
     * on Maybe objects that contain a value.
     */
    @Test
    public void testJustMap() {
        Maybe<Integer> maybe1 = Maybe.just(4);
        Maybe<Double> maybe2 = Maybe.just(Double.valueOf(3.14));
        Maybe<String> maybe3 = Maybe.just("string");
        Maybe<Maybe<Integer>> maybe4 = Maybe.just(maybe1);

        Function<Integer, Integer> func1 = (Integer i) -> i * 2;
        try {
            int newValue = maybe1.map(func1).get();
            assertEquals(8, newValue);
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Function<Double, Double> func2 = (Double d) -> d * d;
        try {
            Double newValue = maybe2.map(func2).get();
            assertEquals(Double.valueOf(9.8596), newValue);
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Function<String, String> func3 = (String s) -> s.substring(2, 4);
        try {
            String newValue = maybe3.map(func3).get();
            assertEquals("ri", newValue);
        } catch (NullValueException e) {
            fail("Unexpected NullValueException");
        }

        Function<Maybe<Integer>, Maybe> func4 = (Maybe<Integer> maybe) -> Maybe.nothing();
        try {
            Maybe newValue = maybe4.map(func4).get();
            newValue.get();
            fail("There should've been an exception.");
        } catch (NullValueException e) {
            assertTrue(true);
        }

        Function<Integer, Integer> func5 = null;
        try {
            Maybe<Integer> maybe = maybe1.map(func5);
            fail("There should've been an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * This method checks correctness of the map() method
     * on Maybe objects that contain no value.
     */
    @Test
    public void testNothingMap() {
        Maybe<Integer> maybe1 = Maybe.nothing();
        Maybe<String> maybe2 = Maybe.nothing();
        Maybe<Maybe<Integer>> maybe3 = Maybe.nothing();

        Function<Integer, Integer> func1 = (i) -> i * 2;
        try {
            int newValue = maybe1.map(func1).get();
            fail("There should've been an exception");
        } catch (NullValueException e) {
            assertTrue(true);
        }

        try {
            Function<String, String> func2 = (String str) -> str + "1 2";
            Maybe<String> maybe = maybe2.map(func2);
            maybe.get();
            fail("There should've been an exception");
        } catch (NullValueException e) {
            assertTrue(true);
        }

        Function<Maybe<Integer>, Maybe> func3 = (Maybe<Integer> maybe) -> Maybe.just(maybe.isPresent());
        try {
            Maybe maybe = maybe3.map(func3);
            maybe.get();
            fail("There should've been an exception");
        } catch (NullValueException e) {
            assertTrue(true);
        }

        Function<Integer, Integer> func4 = null;
        try {
            Maybe<Integer> maybe = maybe1.map(func4);
            maybe.get();
            fail("There should've been an exception");
        } catch (NullValueException e) {
            fail("Wrong exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }


}
