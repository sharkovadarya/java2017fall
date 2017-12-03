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
    public void testJustGetValue() throws NullValueException {
        Maybe<Integer> maybe1 = Maybe.just(4);
        assertEquals(Integer.valueOf(4), maybe1.get());

        Maybe<Double> maybe2 = Maybe.just(3.14);
        assertEquals(Double.valueOf(3.14), maybe2.get());

        Maybe<String> maybe3 = Maybe.just("string");
        assertEquals("string", maybe3.get());

        Maybe<Maybe<Integer>> maybe4 = Maybe.just(maybe1);
        assertEquals(maybe1, maybe4.get());
    }

    /**
     * This test checks correctness of the get() method
     * for Maybe objects that contain no value.
     * In this case the get() method should throw an exception.
     */
    @Test(expected = NullValueException.class)
    public void testNothingGetValueMaybeMaybeString() throws NullValueException {
        Maybe<Maybe<String>> maybe = Maybe.nothing();
        maybe.get();
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
    public void testJustMap() throws NullValueException {
        Maybe<Integer> maybe1 = Maybe.just(4);
        Maybe<Double> maybe2 = Maybe.just(Double.valueOf(3.14));
        Maybe<String> maybe3 = Maybe.just("string");

        Function<Integer, Integer> func1 = (Integer i) -> i * 2;
        int newValue1 = maybe1.map(func1).get();
        assertEquals(8, newValue1);

        Function<Double, Double> func2 = (Double d) -> d * d;
        Double newValue2 = maybe2.map(func2).get();
        assertEquals(Double.valueOf(9.8596), newValue2);

        Function<String, String> func3 = (String s) -> s.substring(2, 4);
        String newValue3 = maybe3.map(func3).get();
        assertEquals("ri", newValue3);
    }

    /**
     * This method checks correctness of the map() method
     * on Maybe objects that contain a value
     * with a function that maps all elements to nothing.
     */
    @Test(expected = NullValueException.class) 
    public void testJustMapFunctionWithNothing() throws NullValueException {        
        Maybe<Integer> maybe1 = Maybe.just(4);
        Maybe<Maybe<Integer>> maybe2 = Maybe.just(maybe1);
        Function<Maybe<Integer>, Maybe> func = (Maybe<Integer> maybe) -> Maybe.nothing();
        Maybe newValue = maybe2.map(func).get();
        newValue.get();
    }

    /**
     * This method checks correctness of the map() method
     * on Maybe objects that contain no value.
     */
    @Test(expected = NullValueException.class)
    public void testNothingMap() throws NullValueException {
        Maybe<Maybe<Integer>> maybe1 = Maybe.nothing();

        Function<Maybe<Integer>, Maybe> func = (Maybe<Integer> maybe) -> Maybe.just(maybe.isPresent());
        Maybe maybe = maybe1.map(func);
        maybe.get();
    }
}
