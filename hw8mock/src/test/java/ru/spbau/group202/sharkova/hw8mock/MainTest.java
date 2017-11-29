package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * This class tests correctness of Main class behavior, 
 * which is parsing a given expression and calculating its result
 * using reverse Polish notation and a stack.
 */
public class MainTest {

    /**
     * This method checks that expression consisting of a single number
     * is considered incorrect and causes an IllegalArgumentException to be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectExpressionSingleNumber() {
        Main.main(new String[]{"1234"});
    }

    /**
     * This method checks that expression consisting of a single number and an operation
     * is considered incorrect and causes an IllegalArgumentException to be thrown.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectExpressionSingleOperation() {
        Main.main(new String[]{"1234 +"});
    }

    /**
     * This method checks correctness of calculation
     * of a basic arithmetic expression with on operation
     * and its two arguments.
     */
    @Test
    public void testBasicExpression() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[]{"12 + 34"});

        System.out.flush();
        System.setOut(old);


        assertEquals("46.0", baos.toString().trim());
    }

    /**
     * This method checks correctness of simple expression with brackets calculation.
     */
    @Test
    public void testExpressionWithBrackets() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[]{"2 * (30 + 59)"});

        System.out.flush();
        System.setOut(old);
        
        assertEquals("178.0", baos.toString().trim());
    }

    /**
     * This method checks correctness of calculations of an expression
     * which uses brackets, double numbers and all supported operations.
     */
    @Test
    public void testExpressionAllOperations() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[]{"-1 * (4 * 7 / 2 % 3) + (3.14 + 2.71) * 3"});

        System.out.flush();
        System.setOut(old);

        assertEquals(Double.parseDouble("15.55"), Double.parseDouble(baos.toString().trim()), 0.01);
    }

    /**
     * This method checks if an IllegalArgumentException is indeed thrown
     * when there's an attempt to calculate the result of an expression
     * without the proper spacing.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNoNecessarySpaces() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[]{"-1 * (4 * 7 / 2 % 3) + (3.14 +2.71) * 3"});

        System.out.flush();
        System.setOut(old);

        assertEquals("Invalid input. Separate all operations with spaces.", baos.toString().trim());
    }

    /**
     * This method checks if an IllegalArgumentException is indeed thrown
     * when there's an attempt to calculate the result of an expression
     * with an unsupported operation.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectOperation() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[]{"2 ^ 3"});

        System.out.flush();
        System.setOut(old);

        assertEquals("Illegal operation", baos.toString().trim());
    }
}
