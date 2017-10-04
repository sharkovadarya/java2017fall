package ru.spbau.group202.sharkova.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void testGetKey() {
        Pair pair = new Pair("a", "b");

        assertEquals("a", pair.getKey());
    }

    @Test
    public void testGetValue() {
        Pair pair = new Pair("a", "b");

        assertEquals("b", pair.getValue());
    }

    @Test
    public void testSetValue() {
        Pair pair = new Pair("a", "b");

        pair.setValue("c");
        assertEquals("c", pair.getValue());
    }

    /*
     * This method is used for testing purposes only,
     * but it still has to be tested.
     */
    @Test
    public void testEquals() {
        Pair pair1 = new Pair("a", "b");
        Pair pair2 = new Pair("a", "b");
        Pair pair3 = new Pair("A", "b");
        Pair pair4 = new Pair("a", "B");

        assertEquals(true, pair1.equals(pair2));
        assertEquals(false, pair1.equals(pair3));
        assertEquals(false, pair1.equals(pair4));
        assertEquals(false, pair3.equals(pair4));
        assertEquals(false, pair2.equals(3));
    }

}