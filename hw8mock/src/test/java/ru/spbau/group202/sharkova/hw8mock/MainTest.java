package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectExpressionSingleNumber() {
        Main.main(new String[]{"1234"});
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectExpressionSingleOperation() {
        Main.main(new String[]{"1234 +"});
    }

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