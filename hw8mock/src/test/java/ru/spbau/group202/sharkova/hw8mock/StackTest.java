package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void testEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testPush() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        assertEquals(false, stack.isEmpty());
        stack.push("ab");
        stack.push("A");
        stack.push("a");
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void testPop() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        stack.push("a");
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.pop());
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testPeek() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        stack.push("a");
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.peek());
        assertEquals(false, stack.isEmpty());
        stack.push("ab");
        stack.push("a");
        stack.push("A");
        assertEquals("A", stack.peek());
        assertEquals(false, stack.isEmpty());
        assertEquals("A", stack.pop());
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.peek());
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.pop());
        assertEquals(false, stack.isEmpty());
        assertEquals("ab", stack.peek());
        assertEquals(false, stack.isEmpty());
        assertEquals("ab", stack.pop());
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.peek());
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.pop());
        assertEquals(true, stack.isEmpty());
    }

}