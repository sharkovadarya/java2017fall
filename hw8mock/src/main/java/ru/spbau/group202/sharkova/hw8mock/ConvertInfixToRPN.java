package ru.spbau.group202.sharkova.hw8mock;

import java.util.*;

/**
 * This utility class parses a string
 * and converts it to reverse Polish notation.
 * Supported operations: +, -, *, /, %; brackets are supported.
 * The operators need to be separated with spaces.
 */
public class ConvertInfixToRPN {

    /**
     * This method parses given tokens
     * representing an expression in infix notation
     * and converts them to reverse Polish notation.
     * @param infixNotation infix notation expression tokens.
     * @return reverse Polish notation expression tokens.     
     * @throws IllegalArgumentException if the expression is malformed.
     */
    private static List<String> convertInfixToRPN(String[] infixNotation) throws IllegalArgumentException {

        Stack<String> S = new Stack<>();
        ArrayList<String> expression = new ArrayList<>();

        for (String token : infixNotation) {
            if ("(".equals(token)) {
                S.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(S.peek())) {
                    String str = S.pop();
                    expression.add(str);
                }
                S.pop();
                continue;
            }

            // The symbol represents an operation.
            if (CalculationUtilities.isProcessableOperation(token)) {
                while (!S.isEmpty() && CalculationUtilities.getPrecedence(token) 
                       <= CalculationUtilities.getPrecedence(S.peek())) {
                    String str = S.pop();
                    expression.add(str);
                }
                S.push(token);
                continue;
            }

            if (CalculationUtilities.isNumber(token)) {
                expression.add(token);
                continue;
            }

            throw new IllegalArgumentException("Invalid input. Separate all operations with spaces.");
        }

        while (!S.isEmpty()) {
            String str = S.pop();
            expression.add(str);
        }

        return expression;
    }

    /**
     * This method parses a given infix notation expression in string format
     * and return a list of tokens in reverse Polish notation.
     * @param expression expression in infix notation.
     * @return list of tokens in reverse Polish notation.
     * @throws IllegalArgumentException if the expression is malformed.     
     */
    public static List<String> convertToRPN(String expression) throws IllegalArgumentExpression {
        String[] splitString = expression.split(" ");
        ArrayList<String> splitStringToInfixNotation = new ArrayList<>();
        for (String s : splitString) {
            if (s.equals("")) {
                continue;
            }

            int i = 0, j = s.length() - 1;
            while (s.charAt(i++) == '(') {
                splitStringToInfixNotation.add("(");
            }

            if (i-- > s.length()) {
                throw new IllegalArgumentException("Incorrect expression.");
            }

            while (s.charAt(j) == ')')
                j--;

            if (j - i < 0) {
                throw new IllegalArgumentException("Incorrect expression.");
            }

            splitStringToInfixNotation.add(s.substring(i, ++j));

            while (j++ != s.length()) {
                splitStringToInfixNotation.add(")");
            }
        }
        
        String[] infixNotation = new String[splitStringToInfixNotation.size()];
        infixNotation = splitStringToInfixNotation.toArray(infixNotation);

        return convertInfixToRPN(infixNotation);
    }
}
