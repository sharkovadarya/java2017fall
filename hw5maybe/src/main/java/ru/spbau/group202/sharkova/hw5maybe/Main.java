package ru.spbau.group202.sharkova.hw5maybe;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.lang.Number;

public class Main {

    /**
     * This utility method checks if the given string
     * contains one parse-able number.
     * @param str string to be checked
     * @return true if it's a single parse-able number;
     *         false otherwise
     */
    private static boolean isNumeric(@NotNull String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    /**
     * This utility method provides multiplication
     * for Number objects.
     * It doesn't work with BigDecimals or BigIntegers
     * since Number doesn't have a value() method for those.
     */
    @NotNull
    private static Number multiplyNumbers(@NotNull Number a, @NotNull Number b) {
        if (a instanceof Double || b instanceof Double) {
            return a.doubleValue() * b.doubleValue();
        } else if(a instanceof Float || b instanceof Float) {
            return a.floatValue() * b.floatValue();
        } else if(a instanceof Long || b instanceof Long) {
            return a.longValue() * b.longValue();
        } else {
            return a.intValue() * b.intValue();
        }
    }

    /**
     * This method reads the input file by line,
     * gets a Maybe object based on content of each line,
     * outputs a squared number if Maybe contains a number,
     * or "null" otherwise, to the output file.
     * @throws IOException if incorrect input/output filepath is provided
     * @param inputFilePath path to the input file
     * @param outputFilePath path to the output file
     */
    private static void processNumbersFromFile(@NotNull String inputFilePath,
                            @NotNull String outputFilePath) throws IOException {
        File inputFile = new File(inputFilePath);
        String absoluteInputFilePath = inputFile.getAbsolutePath();
        File outputFile = new File(outputFilePath);
        String absoluteOutputFilePath = outputFile.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(
                new FileReader(new File(absoluteInputFilePath)));
           BufferedWriter bw = new BufferedWriter(
                   new FileWriter(new File(absoluteOutputFilePath)))) {
            for (String line; (line = br.readLine()) != null; ) {
                Maybe<Number> maybe = processStringAsNumber(line);

                if (maybe.isPresent()) {
                    maybe = maybe.map((Number num) -> (multiplyNumbers(num, num)));
                    try {
                        bw.write(maybe.get().toString());
                        bw.newLine();
                    } catch (NullValueException e) {
                        /*
                         * this is not supposed to happen
                         * but if it does, let's pass it as a parsing error
                         */
                        bw.write("null\n");
                    }
                } else {
                    bw.write("null\n");
                }
            }
        }
    }

    /**
     * This method accepts a string a returns a Maybe:
     * if the string is a single parse-able number,
     * the returned Maybe object contains that number,
     * otherwise it contains null.
     * @param str string from which the number is parsed
     * @return Maybe object:
     *         containing a parsed number if possible,
     *         containing null otherwise
     */
    private static Maybe<Number> processStringAsNumber(@NotNull String str) {
        if (isNumeric(str)) {
            try {
                Number num = NumberFormat.getInstance().parse(str);
                return Maybe.just(num);
            } catch (ParseException e) {
                return Maybe.nothing();
            }
        }

        return Maybe.nothing();
    }

    /**
     * This method handles argument parsing
     * and calls the private utility method
     * to read and parse numbers and output their squares
     * or null if there was no number on a line.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: path to input file, path to output file");
            System.exit(0);
        }

        try {
            processNumbersFromFile(args[0], args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Input file does not exist.");
        } catch (IOException e) {
            System.out.println("Incorrect input/output file provided.");
        }
    }
}
