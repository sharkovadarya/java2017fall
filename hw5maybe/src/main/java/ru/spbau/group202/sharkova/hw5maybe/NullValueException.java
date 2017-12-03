package ru.spbau.group202.sharkova.hw5maybe;

/**
 * Custom exception class.
 * Used when there is an attempt to use a value which is not expected to be null,
 * but it actually is a null value.
 * I.e. in Maybe class when there is an attempt to get a value from a 'nothing' object.
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
