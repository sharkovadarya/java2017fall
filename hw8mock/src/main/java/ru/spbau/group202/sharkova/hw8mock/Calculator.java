package ru.spbau.group202.sharkova.hw8mock;

import java.util.List;

/**
 * This class implements a calculator
 * that supports the following operations:
 * +, -, *, /, %
 * applied to double numbers; brackets are supported.
 * It accepts a stack in its constructor
 * and uses this stack to calculate the result of the expression.
 */
public class Calculator {

    private Stack<Double> stack;

    /**
     * This constructor accepts a stack which will be used for calculations.
     * @param stack stack for calculations
     */
    public Calculator(Stack<Double> stack) {
        this.stack = stack;
    }

    /**
     * This method evaluates the given expression.
     * @param expression list of tokens representing the expression
     * @return result of the expression evaluation
     */
    public Double evaluateExpression(List<String> expression) {
        if (expression.size() < 2) {
            throw new IllegalArgumentException("Incorrect expression.");
        }

        stack.push(Double.parseDouble(expression.get(0)));
        stack.push(Double.parseDouble(expression.get(1)));

        for (int i = 2; i < expression.size(); i++) {
            String token = expression.get(i);
            if (CalculationUtilities.isProcessableOperation(token)) {
                Double secondArgument = stack.pop();
                Double firstArgument = stack.pop();
                Double calculationResult = CalculationUtilities
                        .processOperation(firstArgument, secondArgument, token);
                stack.push(calculationResult);
            } else if (CalculationUtilities.isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                throw new IllegalArgumentException("Illegal token: " + token);
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Incorrect expression/provided stack.");
        }

        Double result = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Incorrect expression/provided stack.");
        }

        return result;
    }
}
