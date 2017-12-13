package ru.spbau.group202.sharkova.test2;

public class InjectionCycleException extends Exception {

    public InjectionCycleException() {
        super();
    }

    public InjectionCycleException(String message) {
        super(message);
    }

    public InjectionCycleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectionCycleException(Throwable cause) {
        super(cause);
    }
}
