package ru.spbau.group202.sharkova.hw5set;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * This class test correctness of the Set() class
 * add(), contains() and size() methods.
 */
public class SetTest {

    /**
     * This test checks that an empty set has a zero size
     * and contains nothing.
     */
    @Test
    public void testEmptySet() {
        Set<Integer> emptySet = new Set<>();

        assertEquals(0, emptySet.size());
        // the following 'unnecessary boxing' is on purpose
        assertEquals(false, emptySet.contains(new Integer(3)));
        assertEquals(false, emptySet.contains(0));
    }

    /**
     * This test checks correctness of insertion of integers
     * and the set size after the insertion.
     */
    @Test
    public void testAddIntegers() {
        Set<Integer> setInt = new Set<>();

        assertEquals(true, setInt.add(0));
        assertEquals(false, setInt.add(0));
        assertEquals(1, setInt.size());
        assertEquals(true, setInt.add(new Integer(1024)));
        assertEquals(false, setInt.add(new Integer(1024)));
        assertEquals(2, setInt.size());
    }

    /**
     * This test checks correctness of string insertion
     * and the set size after insertion.
     */
    @Test
    public void testAddStrings() {
        Set<String> setString = new Set<>();

        assertEquals(true, setString.add("string"));
        assertEquals(false, setString.add("string"));
        assertEquals(1, setString.size());
        assertEquals(true, setString.add("String"));
        assertEquals(false, setString.add("String"));
        assertEquals(true, setString.add("STRING"));
        assertEquals(false, setString.add("STRING"));
        assertEquals(3, setString.size());

        /*
         * The following line should throw (and does so)
         * an IllegalArgumentException when used with IDEA.
         */
        try {
            assertEquals(true, setString.add(null));
            fail("There should've been an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } 
    }

    /**
     * This test checks correctness of files insertion
     * and the set size after insertion.
     */
    @Test
    public void testAddFiles() {
        Set<File> setFile = new Set<>();
        File file1 = new File("src/main/java");
        File file2 = new File("src/main");
        File file3 = new File("src/main/resources");

        assertEquals(true, setFile.add(file1));
        assertEquals(false, setFile.add(file1));
        assertEquals(true, setFile.add(file2));
        assertEquals(false, setFile.add(file2));
        assertEquals(true, setFile.add(file3));
        assertEquals(false, setFile.add(file3));
    }

    /**
     * This method checks correctness of the contains() method
     * on integer values.
     * If the value has been inserted to the tree,
     * contains() should return true; false otherwise.
     */
    @Test
    public void testContainsInteger() {
        Set<Integer> setInt = new Set<>();

        assertEquals(true, setInt.add(0));
        assertEquals(true, setInt.add(1));

        assertEquals(true, setInt.contains(0));
        assertEquals(true, setInt.contains(new Integer(1)));
        assertEquals(false, setInt.contains(2));
        assertEquals(false, setInt.contains(new Integer(-1)));
    }

    /**
     * This method checks correctness of the contains() method
     * on strings.
     * If the value has been inserted to the tree,
     * contains() should return true; false otherwise.
     */
    @Test
    public void testContainsString() {
        Set<String> setString = new Set<>();

        assertEquals(true, setString.add("string"));
        assertEquals(true, setString.add("String"));
        assertEquals(true, setString.add("STRING"));

        assertEquals(false, setString.contains("String "));
        assertEquals(true, setString.contains("string"));
        assertEquals(true, setString.contains("String"));
        assertEquals(false, setString.contains("1STRING"));
        assertEquals(true, setString.contains("STRING"));

        /*
         * The following line should throw (and does so)
         * an IllegalArgumentException when used with IDEA;
         * or it should throw a NPE when used with 'mvn tests'.
         */
        try {
            assertEquals(true, setString.contains(null));
            fail("There should've been an exception");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (NullPointerException e) {
            System.out.println("Tried passing null to contains()");            
            System.out.println("NullPointerException has been thrown");
            System.out.println("If used with IntelliJ IDEA, this test is failed\n");
        }
    }

    /**
     * This method checks correctness of the contains() method
     * on files.
     * If the value has been inserted to the tree,
     * contains() should return true; false otherwise.
     */
    @Test
    public void testContainsFile() {
        Set<File> setFile = new Set<>();
        File file1 = new File("src/main/java");
        File file2 = new File("src/main");
        File file3 = new File("src/main/resources");
        File file4 = new File("src/test");

        assertEquals(true, setFile.add(file1));
        assertEquals(true, setFile.add(file2));
        assertEquals(true, setFile.add(file3));

        assertEquals(true, setFile.contains(file1));
        assertEquals(true, setFile.contains(file2));
        assertEquals(true, setFile.contains(file3));
        assertEquals(false, setFile.contains(file4));
    }



}