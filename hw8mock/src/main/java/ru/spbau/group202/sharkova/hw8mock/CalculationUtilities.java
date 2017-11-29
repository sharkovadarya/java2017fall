package ru.spbau.group202.sharkova.hw8mock;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class provided various methods used for parsing and calculating.
 * It implements methods for processing operations
 * (get operation priority, check if operation is supported,
 * calculate result of binary operation application)
 * and for checking whether a string token is a number.
 */
public class CalculationUtilities {

    private static Map<String, Integer> precedence = new HashMap<>();

     static {
        precedence.put("/", 2);
        precedence.put("*", 2);
        precedence.put("%", 2);
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("(", 0);
    }

    /**
     * This method returns the operation priority.
     * @param operation operation which priority is to be evaluated.
     * @return operation priority.
     */
    public static int getPrecedence(String operation) {
        if (!isProcessableOperation(operation)) {
            throw new IllegalArgumentException("Illegal operation.");
        }

        return precedence.get(operation);
    }

    /**
     * This method checks whether the given operation is supported.
     * @param operation operation to be checked.
     * @return true if the operation is supported by this calculator.
     */
    public static boolean isProcessableOperation(String operation) {
         return precedence.containsKey(operation);
    }

    /**
     * This method calculates the result of a given binary operation application
     * to provided arguments.
     * @param firstArgument first operation argument
     * @param secondArgument second operation argument
     * @param operation binary operation to be applied
     * @return result of operation application
     */
    public static Double processOperation(Double firstArgument,
                                          Double secondArgument,
                                          String operation) {

         switch (operation) {
             case "+": return firstArgument + secondArgument;
             case "-": return firstArgument - secondArgument;
             case "*": return firstArgument * secondArgument;
             case "/": return firstArgument / secondArgument;
             case "%": return firstArgument % secondArgument;
         }

        // if none of the previous options applies, the operation is incorrect
        throw new IllegalArgumentException("Illegal operation");
    }

    /**
     * This method checks whether the given token represents a number.
     * @param token token to be checked
     * @return true if the token represents a number
     */
    public static boolean isNumber(String token) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(token);
            number.doubleValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
