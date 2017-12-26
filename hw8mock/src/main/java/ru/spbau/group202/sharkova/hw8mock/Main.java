package ru.spbau.group202.sharkova.hw8mock;

import java.util.List;

/**
 * This class implements a console application
 * that accepts an expression as its argument
 * and outputs its result.
 * The expression:
 *  - needs to be put in double quotes ("2 + 5")
 *  - can contain the following operation: +, -, *, /, %
 *  - needs to have its operations separated by spaces
 *    ("2 + 5" is correct, "2+5" is not)
 *  - supports round brackets
 */
public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Incorrect usage: provide an expression");
            return;
        }

        String expression = args[0];
        List<String> tokens = ConvertInfixToRPN.convertToRPN(expression);
        Calculator calculator = new Calculator(new Stack<>());
        System.out.println(calculator.evaluateExpression(tokens));
    }
}
