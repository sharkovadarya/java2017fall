package ru.spbau.group202.sharkova.hw8mock;

/**
 * This class implements a stack data structure.
 * Supported operations: push, pop, peek, check for emptiness.
 * @param <T> template parameter
 */
public class Stack<T> {

    private int maxSize = 2048;
    private T[] stack = (T[]) new Object[maxSize];
    private int top = -1;

    /**
     * Pushes the given value to the stack.
     */
    public void push(T value) {
        if (top + 1 == maxSize)
            resize();
        stack[++top] = value;
    }

    private void resize() {
        T newStack[] = (T[]) new Object[maxSize * 2];
        for (int i = 0; i < maxSize; ++i)
            newStack[i] = stack[i];
        stack = newStack;
        maxSize *= 2;
    }

    /**
     * Extracts the last value in the stack.
     */
    public T pop() {
        return stack[top--];
    }

    /**
     * Returns the last value in the stack without extraction.
     */
    public T peek() {
        return stack[top];
    }

    /**
     * Checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }
}
