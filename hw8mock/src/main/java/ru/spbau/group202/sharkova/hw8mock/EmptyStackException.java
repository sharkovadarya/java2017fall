package ru.spbau.group202.sharkova.hw8mock;

/*
 * There is a standard class doing just the same,
 * but I thought it would be better to use the standard class
 * for the standard stack, and provide a custom exception class here.
 */

/**
 * Custom exception class.
 * Used when there is an attempt to pop an element from an empty stack.
 */
public class EmptyStackException extends RuntimeException {

    // all methods just do what the parent methods do

    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStackException(Throwable cause) {
        super(cause);
    }
}