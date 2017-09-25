package ru.spbau.group202.sharkova.hw2;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents a matrix of elements.
 * Matrix size is set by user.
 * Available options:
 *  - output matrix elements in a spiral-like order
 *  - sort matrix columns by their first elements
 */
public class SpiralMatrix {

    private final int size;
    private int matrix[][];

    public SpiralMatrix(int n) {
        matrix = new int[n][n];
        size = n;
    }

    /**
     * This method assigns the given value
     * to matrix cell (i-th row, j-th column).
     * It will be used for testing other class methods.
     * @param i row
     * @param j column
     * @param value new value
     */
    public void setValue(int i, int j, int value) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Row/column number is out of bounds.");
        }

        matrix[i][j] = value;
    }

    /**
     * This method prints out matrix elements in a spiral-like order.
     * The spiral starts in the centre of the matrix.
     * @param stream the output stream
     */
    public void printElementsInSpiralOrder(PrintStream stream) {
        // matrix centre coordinates
        int i = (size - 1) / 2;
        int j = (size - 1) / 2;
        stream.printf("%d ", matrix[i][j]);

        // spiral directions
        int xDir[] = {1, 0, -1, 0};
        int yDir[] = {0, -1, 0, 1};

        // spiral-like traversal of the matrix
        int k = 1;
        while (true) {
            // traverse the directions
            for (int t = 0; t < 4; t++) {   
                // go in a certain direction and print elements
                for (int c = 1; c <= k; c++) {
                    if (j + c * xDir[t] == size) {
                        return;
                    }

                    stream.printf("%d ", matrix[i + c * yDir[t]][j + c * xDir[t]]);
                }

                // last visited element coordinates
                j += k * xDir[t];
                i += k * yDir[t];

                /*
                 * The "distance" covered by the spiral in one direction
                 * increases after every two walks
                 */
                if (t % 2 == 1) {
                    k++;
                }
            }
        }
    }

    /**
     * This private method will be used as a utility method
     * for sorting matrix columns.
     * It transposes the given matrix.
     * @param matrix matrix to be transposed
     * @return the transposed matrix
     */
    private int[][] transposeMatrix(int[][] matrix) {
        int newMatrix[][] = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[j][i];
            }
        }

        return newMatrix;
    }


    /**
     * This method sorts the matrix columns
     * by the values of their first elements.
     */
    public void sortColumnsByFirstElements() {

        /*
         * It's easy to sort rows instead of columns
         * so we will transpose the matrix, sort its rows
         * then transpose it back and assign the result
         * to the (matrix) array.
         */

        int transposedMatrix[][] = transposeMatrix(matrix);

        // sort rows of the transposed matrix
        Arrays.sort(transposedMatrix, new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                return array1[0] - array2[0];
            }
        });

        // transpose the matrix back
        matrix = transposeMatrix(transposedMatrix);
    }
}