package ru.spbau.group202.sharkova.zipfile;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This class is used for testing the ZipFinder class.
 * It will not work without the provided resources
 * in src/test/resources directory.
 */
public class ZipFinderTest {

    /**
     * This method checks correctness of incorrect regex handling.
     * The program is supposed to output a certain string
     * when an incorrect regex is provided.
     */
    @Test
    public void testIncorrectRegularExpression() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        File file = new File("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir");


        ZipFinder.zipFiles(file.getAbsolutePath(), "*.txt");

        String message = new String(baos.toByteArray(), Charset.defaultCharset());
        assertTrue(message.equals("Incorrect regular expression.\n")
                  || message.equals("Incorrect regular expression.\r\n"));
        //assertEquals("Incorrect regular expression.\n", message);

        System.out.flush();
        System.setOut(old);

        deleteUtilityFiles();
    }

    /**
     * This method checks correctness of nonexistent directories handling.
     * If the directory does not exist,
     * ZipFinder is supposed to throw an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNonexistentPath() throws IOException {
        File file = new File("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir1");

        ZipFinder.zipFiles(file.getAbsolutePath(), "[\\s\\S]*.txt");

        deleteUtilityFiles();
    }

    /**
     * This method checks correctness of working with paths to non-directories.
     * ZipFinder is supposed to throw an exception.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNotADirectory() throws IOException {
        File file = new File("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/ver1.zip");

        ZipFinder.zipFiles(file.getAbsolutePath(), "[\\s\\S]*.txt");

        deleteUtilityFiles();
    }

    /**
     * This method makes ZipFinder extract all .txt files
     * and checks whether they have been extracted indeed
     * and whether they match the provided samples.
     */
    @Test
    public void testCorrectDataAnyTxtFile() throws IOException
    {
        File file = new File("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir");

        ZipFinder.zipFiles(file.getAbsolutePath(), "[\\s\\S]*.txt");
        Path path1 = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file1.txt");
        assertEquals(true, Files.exists(path1));
        Path path1correct = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/cfile1.txt");

        // it is ok to use the following for small files
        compareFiles(path1, path1correct);

        Path path2 = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/1file.txt");
        assertEquals(true, Files.exists(path2));
        Path path2correct = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/c1file.txt");

        // it is ok to use the following for small files
        compareFiles(path2, path2correct);

        Path path3 =  Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/Selection_114.png");
        assertEquals(false, Files.exists(path3));

        deleteUtilityFiles();
    }

    /**
     * This method makes ZipFinder extract all files that do not have
     * digits in their names. It checks whether the files
     * that were supposed to be extracted exist and
     * whether they match the provided samples.
     */
    @Test
    public void testCorrectDataNoDigitsRegex() throws IOException {
        File file = new File("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir");

        ZipFinder.zipFiles(file.getAbsolutePath(), "[^0-9]*.[a-z]*");
        Path path1 = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file1.txt");
        assertEquals(false, Files.exists(path1));

        Path path2 = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/1file.txt");
        assertEquals(false, Files.exists(path2));

        Path path3 =  Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/Selection_114.png");
        assertEquals(false, Files.exists(path3));

        Path path4 = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file.txt");
        assertEquals(true, Files.exists(path4));

        /*
         * Expected file contents can be found in cfile.txt;
         * the contents of the extracted file will be compared against cfile.txt.
         * compareFiles() method accepts paths to two files and compares file contents.
         */
        Path path4correct = Paths.get("src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/cfile.txt");
        assertEquals(true, compareFiles(path4, path4correct));

        deleteUtilityFiles();

    }



    /**
     * This private method compares two small files.
     * @param correctPath path to the file with expected contents
     * @param path path to the file the contents of which should match
     *             the correctPath file contents
     */
    private boolean compareFiles(Path path, Path correctPath) throws IOException {
        byte[] f1 = Files.readAllBytes(path);
        byte[] f2 = Files.readAllBytes(correctPath);
        return Arrays.equals(f1, f2);
    }

    /**
     * This private method is used to delete any files that might have been
     * generated by the result of ZipFinder actions.
     */
    private void deleteUtilityFiles() throws IOException {
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/1file.txt"));
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file.txt"));
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file1.txt"));
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/Selection_114.png"));
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/file2.txt"));
        Files.deleteIfExists(Paths.get(
                "src/test/resources/ru/spbau/group202/sharkova/zipfile/dir/2file.txt"));
    }


}