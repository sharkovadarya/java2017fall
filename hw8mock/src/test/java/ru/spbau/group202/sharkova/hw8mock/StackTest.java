package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This method checks correctness of the Stack class behavior.
 */
public class StackTest {

    /**
     * This method checks correctness of isEmpty() method
     * on an empty stack.
     */
    @Test
    public void testEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
    }

    /**
     * This method checks correctness of push() method.
     */
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

    /**
     * This method checks correctness of pop() method.
     */
    @Test
    public void testPop() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        stack.push("a");
        assertEquals(false, stack.isEmpty());
        assertEquals("a", stack.pop());
        assertEquals(true, stack.isEmpty());
    }

    /**
     * This method checks correctess of peek() method.
     */
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

    @Test
    public void testResize() {
        Stack<String> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        for (int i = 0; i < 2048; ++i)
            stack.push("a");
        stack.push("ab");
        stack.push("a");
        stack.push("abc");
        assertEquals("abc", stack.peek());
        assertEquals(false, stack.isEmpty());
        assertEquals("abc", stack.pop());
        assertEquals("a", stack.pop());
        assertEquals("ab", stack.pop());
        for (int i = 0; i < 2048; ++i)
            assertEquals("a", stack.pop());
        assertEquals(true, stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopEmptyStack() {
        Stack<Double> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        Double d = stack.pop();
    }

    // push elements, pop them all and try to pop some more
    @Test(expected = EmptyStackException.class)
    public void testPopEmptyStackAfterRemoval() {
        Stack<Double> stack = new Stack<>();
        assertEquals(true, stack.isEmpty());
        stack.push(30.0);
        stack.push(11.6);
        stack.push(3.14);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
