package ru.spbau.group202.sharkova.hw6fp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class checks correctness of Function2 class methods.
 */
public class Function2Test {

    /**
     * This test composes functions that operate on integers.
     */
    @Test
    public void testComposeIntegers() {
        Function2<Integer, Integer, Integer> f = (x, y) -> (x + y);
        Function1<Integer, Integer> g = x -> x * x;
        assertEquals(new Integer(36), f.compose(g).apply(4, 2));
    }

    /**
     * This test composes functions of different (but matching) types.
     */
    @Test
    public void testComposeIntegersDoublesAndStrings() {
        Function2<Integer, Double, String> f = (x, y) ->
                                      ("Integer: " + Integer.toString(x)
                                       + " and Double: " + Double.toString(y));
        Function1<String, String> g = String::toLowerCase;
        assertEquals("integer: 4 and double: 3.14", f.compose(g).apply(4, 3.14));

        Function1<String, Integer> h = String::length;
        assertEquals(new Integer(27), f.compose(h).apply(4, 3.14));
    }

    /**
     * This test binds the first argument of functions
     * with arguments of same (f) and different (g) type.
     */
    @Test
    public void testBind1() {
        Function2<Integer, Integer, Integer> f = (x, y) -> x + y;
        assertEquals(new Integer(7), f.bind1(3).apply(4));
        assertEquals(new Integer(-2), f.bind1(3).apply(-5));

        Function2<Integer, Double, String> g = (x, y) ->
                             ("Integer: " + Integer.toString(x)
                             + " and Double: " + Double.toString(y));
        assertEquals("Integer: 4 and Double: 2.71", g.bind1(4).apply(2.71));
        assertEquals("Integer: 4 and Double: 1.44", g.bind1(4).apply(1.44));
        assertEquals("Integer: 5 and Double: 1.44", g.bind1(5).apply(1.44));
    }

    /**
     * This test binds the second argument of functions
     * with arguments of same (f) and different (g) type.
     */
    @Test
    public void testBind2() {
        Function2<Integer, Integer, Integer> f = (x, y) -> x + y;
        assertEquals(new Integer(7), f.bind2(4).apply(3));
        assertEquals(new Integer(-2), f.bind2(-5).apply(3));

        Function2<Integer, Double, String> g = (x, y) ->
                        ("Integer: " + Integer.toString(x)
                        + " and Double: " + Double.toString(y));
        assertEquals("Integer: 4 and Double: 2.71", g.bind2(2.71).apply(4));
        assertEquals("Integer: 4 and Double: 1.44", g.bind2(1.44).apply(4));
        assertEquals("Integer: 5 and Double: 1.44", g.bind2(1.44).apply(5));
    }

    /**
     * This test checks correctness of currying by using double application.
     */
    @Test
    public void testCurry() {
        Function2<Integer, Integer, Integer> f = (x, y) -> x + y;
        assertEquals(new Integer(7), f.curry().apply(4).apply(3));

        Function2<Integer, Double, String> g = (x, y) ->
                        ("Integer: " + Integer.toString(x)
                        + " and Double: " + Double.toString(y));
        assertEquals("Integer: 4 and Double: 3.14", g.curry().apply(4).apply(3.14));
    }

}