package ru.spbau.group202.sharkova.hw5maybe;

/**
 * Custom exception class.
 */
public class NullValueException extends Exception {

    // all methods just do what the parent methods do

    public NullValueException() {
        super();
    }

    public NullValueException(String message) {
        super(message);
    }

    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullValueException(Throwable cause) {
        super(cause);
    }
}
