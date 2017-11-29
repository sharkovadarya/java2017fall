package ru.spbau.group202.sharkova.hw8mock;

import java.util.ArrayList;

/**
 * This class implements a stack data structure.
 * Supported operations: push, pop, peek, check for emptiness.
 * @param <T> template parameter
 */
public class Stack<T> {

    private ArrayList<T> stack = new ArrayList<>();
    private int top = -1;

    /**
     * Pushes the given value to the stack.
     */
    public void push(T value) {
        if (stack.size() <= top + 1) {
            stack.add(value);
            top++;
        } else {
            stack.set(++top, value);
        }
    }

    /**
     * Extracts the last value in the stack.
     */
    public T pop() {
        return stack.get(top--);
    }

    /**
     * Returns the last value in the stack without extraction.
     */
    public T peek() {
        return stack.get(top);
    }

    /**
     * Checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }
}
