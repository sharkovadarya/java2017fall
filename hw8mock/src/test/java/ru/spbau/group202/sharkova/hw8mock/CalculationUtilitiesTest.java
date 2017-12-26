package ru.spbau.group202.sharkova.hw8mock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This method checks correctness of utility methods used for calculations.
 */
public class CalculationUtilitiesTest {

    @Test
    public void testIsProcessibleOperation() {
        assertEquals(true, CalculationUtilities.isProcessableOperation("+"));
        assertEquals(true, CalculationUtilities.isProcessableOperation("-"));
        assertEquals(true, CalculationUtilities.isProcessableOperation("*"));
        assertEquals(true, CalculationUtilities.isProcessableOperation("/"));
        assertEquals(true, CalculationUtilities.isProcessableOperation("%"));
        assertEquals(false, CalculationUtilities.isProcessableOperation("^"));
        assertEquals(false, CalculationUtilities.isProcessableOperation("div"));
        assertEquals(false, CalculationUtilities.isProcessableOperation("a"));
        assertEquals(false, CalculationUtilities.isProcessableOperation("$"));
    }

    @Test
    public void testIsNumber() {
        assertEquals(true, CalculationUtilities.isNumber("30"));
        assertEquals(true, CalculationUtilities.isNumber("59.0"));
        assertEquals(true, CalculationUtilities.isNumber("3.14"));
        assertEquals(true, CalculationUtilities.isNumber("-33058100111.14116126136"));
        // use the next line if your locale allows for decimal numbers to use comma as a separator (i.e. French locale)
        // assertEquals(true, CalculationUtilities.isNumber("3,14"));
        assertEquals(false, CalculationUtilities.isNumber("a"));
        assertEquals(false, CalculationUtilities.isNumber("+"));
        assertEquals(false, CalculationUtilities.isNumber("-"));
        assertEquals(false, CalculationUtilities.isNumber("/"));
        assertEquals(false, CalculationUtilities.isNumber("*"));
        assertEquals(false, CalculationUtilities.isNumber("%"));
        assertEquals(false, CalculationUtilities.isNumber("^"));
        assertEquals(false, CalculationUtilities.isNumber("modulo"));
    }

    @Test
    public void testGetPrecedence() {
        assertEquals(2, CalculationUtilities.getPrecedence("*"));
        assertEquals(2, CalculationUtilities.getPrecedence("/"));
        assertEquals(2, CalculationUtilities.getPrecedence("%"));
        assertEquals(1, CalculationUtilities.getPrecedence("-"));
        assertEquals(1, CalculationUtilities.getPrecedence("+"));
        assertEquals(0, CalculationUtilities.getPrecedence("("));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGerPrecendeceUnsupportedOperation() {
        CalculationUtilities.getPrecedence("^");
    }

    @Test
    public void testProcessOperation() {
        assertEquals(new Double(89.0), CalculationUtilities.processOperation(30.0, 59.0, "+"));
        assertEquals(new Double(-29.0), CalculationUtilities.processOperation(30.0, 59.0, "-"));
        assertEquals(new Double(89.0), CalculationUtilities.processOperation(30.0, -59.0, "-"));
        assertEquals(new Double(89.0), CalculationUtilities.processOperation(30.0, 59.0, "+"));
        assertEquals(new Double(8.5094), CalculationUtilities.processOperation(3.14, 2.71, "*"));
        assertEquals(new Double(0.8437209302325582), CalculationUtilities.processOperation(7.256, 8.6, "/"));
        assertEquals(new Double(0.25), CalculationUtilities.processOperation(50.0, 200.0, "/"));
        assertEquals(new Double(268), CalculationUtilities.processOperation(67543.0,  897.0, "%"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testProcessIllegalOperation() {
        CalculationUtilities.processOperation(2.0, 3.0, "^");
    }
}
