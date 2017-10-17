package ru.spbau.group202.sharkova.hw5maybe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class checks correctness of Main class methods.
 * Main class handles operations with files, so file-related work
 * (including exceptions) is checked.
 * Correctness of result against expected result is also checked.
 * This class uses provided test file in the 'resources' directory.
 */
public class MainTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * This test checks correctness of the files handling in Main.
     * It uses a supplementary small text test file provided in "resources".
     * Main.main() is called on the provided files,
     * the resulting files contents are compared against the expected results.
     * The resulting file is then deleted.
     */
    @Test
    public void testMain() {
        String[] args = {"src/test/resources/ru/spbau/group202/sharkova/hw5maybe/testfile.txt",
                "src/test/resources/ru/spbau/group202/sharkova/hw5maybe/outfile.txt"};
        Main.main(args);

        try {
            String str = readFile(args[1], Charset.defaultCharset());
            List<String> lines = Arrays.asList(str.split("\n"));
            assertEquals("16", lines.get(0));
            assertEquals("1024", lines.get(1));
            assertEquals("1122.25", lines.get(2));
            for (int i = 3; i < 8; ++i) {
                assertEquals("null", lines.get(i));
            }
            assertEquals("0", lines.get(8));
        } catch (IOException e) {
            fail("Unexpected exception");
        }

        try {
            Files.deleteIfExists(Paths.get(args[1]));
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }

    /**
     * This test checks correctness of incorrect arguments
     * (nonexistent paths) handling.
     * An exception is expected.
     */
    @Test
    public void testIncorrectArguments() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        String[] incorrectArgs = {"src/dir", "src/dir"};
        Main.main(incorrectArgs);
        String message = new String(baos.toByteArray(), Charset.defaultCharset());
        assertEquals("Input/output file does not exist.\n", message);

        System.out.flush();
        System.setOut(old);
    }

    /**
     * This method checks correctness of incomplete arguments handling.
     * It checks whether there is an expected message
     * and an expected exit() code.
     */
    @Test
    public void testIncompleteArguments() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        exit.expectSystemExit();

        String[] incompleteArgs = {"src/test/resources/ru/spbau/group202/sharkova/hw5maybe/testfile.txt"};
        Main.main(incompleteArgs);
        String message = new String(baos.toByteArray(), Charset.defaultCharset());
        assertEquals("Usage: path to input file, path to output file\n", message);

        System.out.flush();
        System.setOut(old);
    }

    /**
     * This private method reads file contents into a string.
     */
    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
