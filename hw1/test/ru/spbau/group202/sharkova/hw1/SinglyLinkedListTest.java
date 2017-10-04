package ru.spbau.group202.sharkova.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

// javadoc comments are not provided since the names are self-explanatory
public class SinglyLinkedListTest {

    /**
     * This method checks that an empty list
     * contains nothing and has a 0 size.
     */
    @Test
    public void testEmptyListConstructor() {
        SinglyLinkedList list = new SinglyLinkedList();

        assertEquals(null, list.get(""));
        assertEquals(null, list.getByIndex(0));
        assertEquals(0, list.getSize());
        assertEquals(null, list.remove(""));
    }

    @Test
    public void testGetElement() {
        SinglyLinkedList list = new SinglyLinkedList();

        Pair pair = new Pair("", " ");
        list.add(pair);
        assertEquals(pair, list.get(""));

        pair = new Pair("a", "b");
        list.add(pair);
        assertEquals(pair, list.get("a"));
    }

    @Test
    public void testGetByIndex() {
        SinglyLinkedList list = new SinglyLinkedList();

        Pair pair = new Pair("", " ");
        list.add(pair);
        assertEquals(pair, list.getByIndex(0));

        pair = new Pair("a", "b");
        list.add(pair);
        assertEquals(pair, list.getByIndex(1));

        pair = new Pair("c", "d");
        list.add(pair);
        assertEquals(pair, list.getByIndex(2));

        pair = new Pair("a", "b");
        assertEquals(pair, list.getByIndex(1));

    }

    @Test
    public void testGetSize() {
        SinglyLinkedList list = new SinglyLinkedList();

        assertEquals(0, list.getSize());

        list.add(new Pair("a", "b"));
        list.add(new Pair("", " "));
        list.add(new Pair("c", "d"));
        list.add(new Pair("1", "23"));
        assertEquals(4, list.getSize());
    }

    @Test
    public void testClear() {
        SinglyLinkedList list = new SinglyLinkedList();

        list.add(new Pair("a", "b"));
        list.add(new Pair("", " "));
        list.add(new Pair("c", "d"));
        list.add(new Pair("1", "23"));

        list.clear();
        assertEquals(0, list.getSize());
        assertEquals(null, list.get("a"));
        assertEquals(null, list.get(""));
        assertEquals(null, list.get("c"));
        assertEquals(null, list.get("1"));
        assertEquals(null, list.getByIndex(0));
        assertEquals(null, list.getByIndex(1));
        assertEquals(null, list.getByIndex(2));
        assertEquals(null, list.getByIndex(3));
    }

    @Test
    public void testRemove() {
        SinglyLinkedList list = new SinglyLinkedList();

        list.add(new Pair("a", "b"));
        list.add(new Pair("", " "));
        list.add(new Pair("c", "d"));
        list.add(new Pair("1", "23"));

        assertEquals("b", list.remove("a"));
        assertEquals(null, list.get("a"));
        assertNotEquals(new Pair("a", "b"), list.getByIndex(0));

        assertEquals("d", list.remove("c"));
        assertEquals(null, list.get("c"));
        assertNotEquals(new Pair("c", "d"), list.getByIndex(1));

        assertEquals("23", list.remove("1"));
        assertEquals(null, list.get("1"));
        assertNotEquals(new Pair("1", "23"), list.getByIndex(1));

        assertEquals(" ", list.remove(""));
        assertEquals(null, list.get(""));
        assertNotEquals(new Pair("", " "), list.getByIndex(0));
    }

    @Test
    public void testRemoveNonexistentElement() {
        SinglyLinkedList list = new SinglyLinkedList();

        assertEquals(null, list.remove(""));
        assertEquals(null, list.remove("asdfghjkl;"));
    }

    @Test
    public void testGetNonexistentElementByKey() {
        SinglyLinkedList list = new SinglyLinkedList();

        assertEquals(null, list.get(""));

        list.add(new Pair("a", "b"));
        assertEquals(null, list.get("A"));
    }

    @Test
    public void testGetNonexistentElementByIndex() {
        SinglyLinkedList list = new SinglyLinkedList();

        assertEquals(null, list.getByIndex(0));

        list.add(new Pair("a", "b"));
        assertEquals(null, list.getByIndex(1));
    }

}