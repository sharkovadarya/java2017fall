package ru.spbau.group202.sharkova.hw6fp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class checks correctness of Function1 methods,
 * compose() method in particular.
 */
public class Function1Test {

    /**
     * This test composes two Integer -> Integer functions
     * and checks the correctness of the result.
     */
    @Test
    public void testComposeIntegers() {
        Function1<Integer, Integer> f = x -> x * 2;
        Function1<Integer, Integer> g = x -> x + 13;
        assertEquals(new Integer(21), f.compose(g).apply(4));
        assertEquals(new Integer(34), g.compose(f).apply(4));
        assertEquals(new Integer(16), f.compose(f).apply(4));
        assertEquals(new Integer(30), g.compose(g).apply(4));
    }

    /**
     * This test composes two functions of different but matching types
     * (result: Integer -> String -> Integer)
     * and checks the correctness of the result.
     */
    @Test
    public void testComposeIntegersAndStrings() {
        Function1<Integer, String> f = x -> Integer.toString(x);
        Function1<String, Integer> g = Integer::parseInt;
        assertEquals(new Integer(4), f.compose(g).apply(4));
    }

}