package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * This test class checks correctness of the Calculator class methods
 * using a mock object imitating a stack.
 * All methods not only check correctness of the result,
 * but also correctness of the order of performed operations.
 */
public class CalculatorTest {
    
    /**
     * This test checks correctness of calculations of a basic expression
     * consisting of a binary operation and its two arguments.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testBasicAddition() {
        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(59.0, 30.0, 89.0);
        when(stack.isEmpty()).thenAnswer(new Answer() {
            private int count = 1;

            public Object answer(InvocationOnMock invocation) {
                return count++ != 1;
            }
        });

        Calculator calculator = new Calculator(stack);
        ArrayList<String> expression = new ArrayList<>();
        expression.add("30");
        expression.add("59");
        expression.add("+");
        assertEquals(new Double(89.0), calculator.evaluateExpression(expression));

        InOrder inOrder = inOrder(stack);
        inOrder.verify(stack).push(30.0);
        inOrder.verify(stack).push(59.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(89.0);
        inOrder.verify(stack).isEmpty();
        inOrder.verify(stack).pop();
        inOrder.verify(stack).isEmpty();
    }

    /**
     * This test checks correctness of calculations of an expression
     * with addition and brackets.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAdditionWithBrackets() {
        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(59.0, 30.0, 100.0, 89.0, 189.0);
        when(stack.isEmpty()).thenAnswer(new Answer() {
            private int count = 1;

            public Object answer(InvocationOnMock invocation) {
                return count++ != 1;
            }
        });

        Calculator calculator = new Calculator(stack);
        ArrayList<String> expression = new ArrayList<>();
        expression.add("30");
        expression.add("59");
        expression.add("+");
        expression.add("100");
        expression.add("+");
        assertEquals(new Double(189.0), calculator.evaluateExpression(expression));

        InOrder inOrder = inOrder(stack);
        inOrder.verify(stack).push(30.0);
        inOrder.verify(stack).push(59.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(89.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(189.0);
        inOrder.verify(stack).isEmpty();
        inOrder.verify(stack).pop();
        inOrder.verify(stack).isEmpty();
    }

    /**
     * This test checks correctness of evaluation of an expression 
     * with double numbers, brackets and all supported operations.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateAllSupportedOperations() {
        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(5.0, 2.0, 6.0, 10.0, 56.0, 28.0, 0.5, 4.0,
                                       3.0, 2.0, -1.0, 5.0, -5.0, 3.0, 8.0, 3.14,
                                       25.12, 4.5, 10.0, -20.62,
                                       -0.620000000000001);
        when(stack.isEmpty()).thenAnswer(new Answer() {
            private int count = 1;

            public Object answer(InvocationOnMock invocation) {
                return count++ != 1;
            }
        });

        Calculator calculator = new Calculator(stack);
        assertEquals(new Double(-0.620000000000001),
                calculator.evaluateExpression(
                        Arrays.asList("2", "5", "*", "6", "%", "28", "56", "/",
                                      "+", "3.14", "3", "5", "2", "3", "-",
                                       "*", "-", "*", "-", "10", "%")));

        InOrder inOrder = inOrder(stack);
        inOrder.verify(stack).push(2.0);
        inOrder.verify(stack).push(5.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(10.0);
        inOrder.verify(stack).push(6.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(4.0);
        inOrder.verify(stack).push(28.0);
        inOrder.verify(stack).push(56.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(0.5);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(4.5);
        inOrder.verify(stack).push(3.14);
        inOrder.verify(stack).push(3.0);
        inOrder.verify(stack).push(5.0);
        inOrder.verify(stack).push(2.0);
        inOrder.verify(stack).push(3.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(-1.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(-5.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(8.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(25.12);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(-20.62);
        inOrder.verify(stack).push(10.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(-0.620000000000001);
        inOrder.verify(stack).isEmpty();
        inOrder.verify(stack).pop();
        inOrder.verify(stack).isEmpty();
    }
}
