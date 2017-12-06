package ru.spbau.group202.sharkova.zipfile;

/**
 * This class is necessary for working with
 * command line arguments.
 * It calls a static method of (ZipFinder) class
 * with provided arguments.
 */
public class Main {

    /**
     * The (main) method checks command line arguments
     * and passes them to ZipFinder.
     */
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Incorrect use");
            System.out.println("First argument: path to directory");
            System.out.println("Second argument: regular expession");
            System.exit(0);
        }

        try {
            ZipFinder.zipFiles(args[0], args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
