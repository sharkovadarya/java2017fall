package ru.spbau.group202.sharkova.test2;

public class AmbiguousImplementationException extends Exception {

    public AmbiguousImplementationException() {
        super();
    }

    public AmbiguousImplementationException(String message) {
        super(message);
    }

    public AmbiguousImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmbiguousImplementationException(Throwable cause) {
        super(cause);
    }
}
