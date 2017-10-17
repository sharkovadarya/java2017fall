package ru.spbau.group202.sharkova.test1110;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedHashMapTest {

    @Test
    public void testInsertion() {
        LinkedHashMap<String, Integer> ht = new LinkedHashMap<>();

        assertEquals(null, ht.put("test1", 30));
        assertEquals(true, ht.containsKey("test1"));
        assertEquals(true, ht.containsValue(30));

    }

    @Test
    public void testDeletion() {
        LinkedHashMap<String, Integer> ht = new LinkedHashMap<>();

        assertEquals(null, ht.put("test1", 30));
        assertEquals(null, ht.put("test2", 30));
        assertEquals(30, ht.remove("test1"));
        assertEquals(true, ht.containsValue(30));
        assertEquals(false, ht.containsKey("test1"));
    }

    @Test
    public void testContains() {
        LinkedHashMap<String, Integer> ht = new LinkedHashMap<>();

        assertEquals(null, ht.put("test1", 30));
        assertEquals(true, ht.containsKey("test1"));
        assertEquals(false, ht.containsKey("Test1"));
        assertEquals(false, ht.containsKey("test"));
        assertEquals(true, ht.containsValue(30));
        assertEquals(false, ht.containsValue(29));
    }

    @Test
    public void testReplace() {
        LinkedHashMap<String, Integer> ht = new LinkedHashMap<>();

        assertEquals(null, ht.put("test1", 30));
        assertEquals(30, ht.put("test1", 59));
    }

}
