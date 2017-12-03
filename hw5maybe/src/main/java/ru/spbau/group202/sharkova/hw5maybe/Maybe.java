package ru.spbau.group202.sharkova.hw5maybe;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * This class emulates behavior similar to Optional class.
 * It contains either a value or nothing.
 * @param <T> type of the stored value 
 */
public class Maybe<T> {

    private final T data;


    /**
     * This static method creates a Maybe object with a value.
     * @param t value to store in a Maybe object
     * @return a new Maybe object which contains a value
     */
    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        // private constructor call
        return new Maybe<>(t);
    }

    /**
     * This method creates a Maybe object with no value.
     * @return an empty Maybe object
     */
    @NotNull
    public static <T> Maybe<T> nothing() {
        // private constructor call
        return new Maybe<>();
    }

    /**
     * This method returns stored value if present.
     * @throws NullValueException if the value is not present
     * @return stored data
     */
    @NotNull
    public T get() throws NullValueException {
        if (data == null)
            throw new NullValueException("Value not present");
        return data;
    }

    /**
     * This method checks whether there is a value stored.
     * @return true if the object contains a value;
     *         false otherwise
     */
    public boolean isPresent() {
        return data != null;
    }

    /**
     * This method applies the given function to the value inside
     * or return an empty Maybe object if there is no value.
     * @param mapper function to be applied
     * @return Maybe object with a modified value
     * */
    @NotNull
    @SuppressWarnings("unchecked")
    public <U> Maybe<U> map(@NotNull Function<T, U> mapper) {
        /*
         * If mapper is null (which should not happen)
         * an IllegalArgumentException will be thrown automatically
         * if launched in IDEA.
         * However, when launched with 'mvn test', it is not,
         * instead the last line is executed, and an NPE is thrown,
         * so I had to insert this check
         */

        if (mapper == null) {
            throw new IllegalArgumentException("Null mapper function provided");
        }

        if (!isPresent()) {
            return (Maybe<U>) this;
        }

        return just(mapper.apply(data));
    }

    // constructors are private as they are only called from just() or nothing()

    private Maybe(@NotNull T value) {
        data = value;
    }

    private Maybe() {
        data = null; // this needs to be explicitly stated if (data) is final
    }
}
