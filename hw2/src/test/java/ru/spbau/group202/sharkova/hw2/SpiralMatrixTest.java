package ru.spbau.group202.sharkova.hw2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SpiralMatrixTest {
    /**
     * This utility method converts results of
     * the spiral matrix (printElementsInSpiralOrder) method
     * to a string
     */
    private String getStringFromSpiralOutput(SpiralMatrix spiralMatrix) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        spiralMatrix.printElementsInSpiralOrder(ps);

        return new String(baos.toByteArray());
    }

    @Test
    public void testEmptyMatrixConstructor() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(3);

        // convert output to string for checking equality
        String s = getStringFromSpiralOutput(spiralMatrix);

        assertEquals("0 0 0 0 0 0 0 0 0 ", s);

        spiralMatrix.sortColumnsByFirstElements();

        s = getStringFromSpiralOutput(spiralMatrix);

        assertEquals("0 0 0 0 0 0 0 0 0 ", s);
    }

    @Test
    public void testSpiralOutputOneElement() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(1);

        spiralMatrix.setValue(0, 0, 1);

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("1 ", s);
    }

    @Test
    public void testSpiralOutputSmall() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spiralMatrix.setValue(i, j, i * 3 + j);
            }
        }

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("4 5 2 1 0 3 6 7 8 ", s);
    }

    @Test
    public void testSpiralOutput() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(5);

        /*
         * in order for tests to be self-contained
         * this matrix will be filled manually
         */
        spiralMatrix.setValue(0, 0, 11);
        spiralMatrix.setValue(0, 1, 7);
        spiralMatrix.setValue(0, 2, 10);
        spiralMatrix.setValue(0, 3, 1);
        spiralMatrix.setValue(0, 4, 23);
        spiralMatrix.setValue(1, 0, 6);
        spiralMatrix.setValue(1, 1, 12);
        spiralMatrix.setValue(1, 2, 6);
        spiralMatrix.setValue(1, 3, 11);
        spiralMatrix.setValue(1, 4, 67);
        spiralMatrix.setValue(2, 0, 8);
        spiralMatrix.setValue(2, 1, 4);
        spiralMatrix.setValue(2, 2, 1);
        spiralMatrix.setValue(2, 3, 23);
        spiralMatrix.setValue(2, 4, 6);
        spiralMatrix.setValue(3, 0, 2);
        spiralMatrix.setValue(3, 1, 7);
        spiralMatrix.setValue(3, 2, 3);
        spiralMatrix.setValue(3, 3, 33);
        spiralMatrix.setValue(3, 4, 55);
        spiralMatrix.setValue(4, 0, 15);
        spiralMatrix.setValue(4, 1, -2);
        spiralMatrix.setValue(4, 2, 0);
        spiralMatrix.setValue(4, 3, -7);
        spiralMatrix.setValue(4, 4, 5);

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("1 23 11 6 12 4 7 3 33 55 6 67 23 1 10 7 11 6 8 2 15 -2 0 -7 5 ", s);
    }

    @Test
    public void testSortColumnsOneElement() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(1);

        spiralMatrix.setValue(0, 0, 1);

        spiralMatrix.sortColumnsByFirstElements();

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("1 ", s);
    }

    @Test
    public void testSortColumnsSorted() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spiralMatrix.setValue(i, j, i * 3 + j);
            }
        }

        spiralMatrix.sortColumnsByFirstElements();

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("4 5 2 1 0 3 6 7 8 ", s);
    }

    @Test
    public void testSortColumnsSmall() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spiralMatrix.setValue(i, j, -1 * (i * 3 + j));
            }
        }

        spiralMatrix.sortColumnsByFirstElements();

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("-4 -3 0 -1 -2 -5 -8 -7 -6 ", s);
    }

    @Test
    public void testSortColumns() {
        SpiralMatrix spiralMatrix = new SpiralMatrix(5);

        /*
         * in order for tests to be self-contained
         * this matrix will be filled manually
         */
        spiralMatrix.setValue(0, 0, 11);
        spiralMatrix.setValue(0, 1, 7);
        spiralMatrix.setValue(0, 2, 10);
        spiralMatrix.setValue(0, 3, 1);
        spiralMatrix.setValue(0, 4, 23);
        spiralMatrix.setValue(1, 0, 6);
        spiralMatrix.setValue(1, 1, 12);
        spiralMatrix.setValue(1, 2, 6);
        spiralMatrix.setValue(1, 3, 11);
        spiralMatrix.setValue(1, 4, 67);
        spiralMatrix.setValue(2, 0, 8);
        spiralMatrix.setValue(2, 1, 4);
        spiralMatrix.setValue(2, 2, 1);
        spiralMatrix.setValue(2, 3, 23);
        spiralMatrix.setValue(2, 4, 6);
        spiralMatrix.setValue(3, 0, 2);
        spiralMatrix.setValue(3, 1, 7);
        spiralMatrix.setValue(3, 2, 3);
        spiralMatrix.setValue(3, 3, 33);
        spiralMatrix.setValue(3, 4, 55);
        spiralMatrix.setValue(4, 0, 15);
        spiralMatrix.setValue(4, 1, -2);
        spiralMatrix.setValue(4, 2, 0);
        spiralMatrix.setValue(4, 3, -7);
        spiralMatrix.setValue(4, 4, 5);

        spiralMatrix.sortColumnsByFirstElements();

        String s = getStringFromSpiralOutput(spiralMatrix);
        assertEquals("1 8 6 6 12 4 7 3 2 55 6 67 23 11 10 7 1 11 23 33 -7 -2 0 15 5 ", s);
    }
}