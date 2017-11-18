package ru.spbau.group202.sharkova.hw6fp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This test class checks behavior of Collections class methods.
 */
public class CollectionsTest {

    /**
     * This method creates a list, takes a function,
     * maps it over the list and checks if the result matches the expected one.
     */
    @Test
    public void testMap() {
        Function1<String, Integer> f = String::length;
        ArrayList<String> l = new ArrayList<>();
        l.add(0, "a");
        l.add(1, "ab");
        l.add(2, "b");
        l.add(3, "abc");

        List<Integer> result = Collections.map(f, l);
        assertEquals(new Integer(1), result.get(0));
        assertEquals(new Integer(2), result.get(1));
        assertEquals(new Integer(1), result.get(2));
        assertEquals(new Integer(3), result.get(3));
    }

    /**
     * This method creates a list, takes a predicate,
     * filters all elements satisfying the predicate
     * and checks that the resulting list contains all expected element.
     */
    @Test
    public void testFilter() {
        Predicate<Integer> p = x -> (x & 1) == 0;
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0, 0);
        l.add(1, 1);
        l.add(2, -1);
        l.add(3, 2);
        l.add(4, -2);
        l.add(5, 5);

        List<Integer> result = Collections.filter(p, l);
        assertEquals(3, result.size());
        assertEquals(new Integer(0), result.get(0));
        assertEquals(new Integer(2), result.get(1));
        assertEquals(new Integer(-2), result.get(2));
    }

    /**
     * This method creates a list, takes a predicate
     * and takes all elements satisfying the predicate
     * up to the first one that does not.
     * It checks that the resulting list elements match
     * the expected ones.
     */
    @Test
    public void testTakeWhile() {
        Predicate<Integer> p = x -> (x & 1) == 0;
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0, 0);
        l.add(1, 2);
        l.add(2, -2);
        l.add(3, 1);
        l.add(4, -4);
        l.add(5, 6);

        List<Integer> result = Collections.takeWhile(p, l);
        assertEquals(3, result.size());
        assertEquals(new Integer(0), result.get(0));
        assertEquals(new Integer(2), result.get(1));
        assertEquals(new Integer(-2), result.get(2));
    }

    /**
     * This method creates a list, takes a predicate
     * and takes all elements not satisfying the predicate
     * up to the first one that does.
     * It checks that the resulting list elements match
     * the expected ones.
     */
    @Test
    public void testTakeUnless() {
        Predicate<Integer> p = x -> (x & 1) == 0;
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0, 0);
        l.add(1, 2);
        l.add(2, -2);
        l.add(3, 1);
        l.add(4, -4);
        l.add(5, 6);

        List<Integer> result = Collections.takeUnless(p.not(), l);
        assertEquals(3, result.size());
        assertEquals(new Integer(0), result.get(0));
        assertEquals(new Integer(2), result.get(1));
        assertEquals(new Integer(-2), result.get(2));
    }

    /**
     * This method checks correctness of left-associative folding.
     * It takes a list, applies a function to list elements
     * and the initial value, checks the correctness of the result.
     */
    @Test
    public void testFoldl() {
        Function2<Integer, String, Integer> f = (x, s) -> x + s.length();
        ArrayList<String> l = new ArrayList<>();
        l.add(0, "a");
        l.add(1, "ab");
        l.add(2, "b");
        l.add(3, "abc");

        assertEquals(new Integer(7), Collections.foldl(f, 0, l));
    }

    /**
     * This method checks correctness of right-associative folding.
     * It takes a list, applies a function to list elements
     * and the initial value, checks the correctness of the result.
     */
    @Test
    public void testFoldr() {
        Function2<String, Integer, Integer> f = (s, x) -> x + s.length();
        ArrayList<String> l = new ArrayList<>();
        l.add(0, "a");
        l.add(1, "ab");
        l.add(2, "b");
        l.add(3, "abc");

        assertEquals(new Integer(7), Collections.foldr(f, 0, l));
    }

}