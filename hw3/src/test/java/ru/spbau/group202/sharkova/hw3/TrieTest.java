package ru.spbau.group202.sharkova.hw3;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class TrieTest {

    /**
     * This test checks whether the size of an empty tree is 0.
     */
    @Test
    public void testEmptyTrieSize()
    {
        Trie trie = new Trie();

        assertEquals(0, trie.size());
    }

    /**
     * This method makes sure nothing is contained in an empty trie.
     */
    @Test
    public void testEmptyTrieContains()
    {
        Trie trie = new Trie();

        assertEquals(false, trie.contains(""));
        assertEquals(false, trie.contains("a"));
        assertEquals(false, trie.contains("A"));
        assertEquals(false, trie.contains("\0"));
        assertEquals(false, trie.contains("asdfghjkl;"));
    }

    /**
     * This method checks that in an empty trie,
     * there are zero strings starting with given prefix.
     */
    @Test
    public void testEmptyTrieHowManyStartsWithPrefix()
    {
        Trie trie = new Trie();

        assertEquals(0, trie.howManyStartsWithPrefix(""));
        assertEquals(0, trie.howManyStartsWithPrefix("a"));
        assertEquals(0, trie.howManyStartsWithPrefix("A"));
        assertEquals(0, trie.howManyStartsWithPrefix("\0"));
        assertEquals(0, trie.howManyStartsWithPrefix("asdfghjkl;"));
    }

    /**
     * This method checks correctness of adding strings to trie.
     * It checks the return value of (add) method
     * and correctness of double insertion.
     */
    @Test
    public void testAdd()
    {
        Trie trie = new Trie();

        assertEquals(true, trie.add(""));
        assertEquals(true, trie.add("\0"));
        assertEquals(true, trie.add("a"));
        assertEquals(false, trie.add(""));
        assertEquals(false, trie.add("a"));
        assertEquals(false, trie.add("\0"));
        assertEquals(true, trie.add("A"));
    }

    /**
     * This test checks correctness of the (contains) method.
     * The method is being tested against strings
     * in upper/lower case, with extra symbols, etc.
     */
    @Test
    public void testContains()
    {
        Trie trie = new Trie();

        assertEquals(true, trie.add(""));
        assertEquals(true, trie.add("\0"));
        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.add("abc"));

        assertEquals(true, trie.contains(""));
        assertEquals(true, trie.contains("\0"));
        assertEquals(true, trie.contains("a"));
        assertEquals(true, trie.contains("abc"));
        assertEquals(false, trie.contains("A"));
        assertEquals(false, trie.contains("Abc"));
        assertEquals(false, trie.contains("ABC"));
        assertEquals(false, trie.contains("a\0bc"));
        assertEquals(false, trie.contains("a b C"));
        assertEquals(false, trie.contains("\0\0"));
    }

    /**
     * This test checks correctness of the (size) method
     * after insertion and double insertion.
     */
    @Test
    public void testSize() {
        Trie trie = new Trie();

        assertEquals(true, trie.add("a"));
        assertEquals(1, trie.size());
        assertEquals(true, trie.add("aa"));
        assertEquals(2, trie.size());
        assertEquals(false, trie.add("a"));
        assertEquals(true, trie.add("asdfghjkl;"));
        assertEquals(3, trie.size());
    }

    /**
     * This test checks if the trie works correctly
     * after a string of a size which is bigger than initial
     * is inserted.
     */
    @Test
    public void testLongStrings()
    {
        Trie trie = new Trie();

        // generating a long test string
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < 500; i++) {
            if (i % 5 == 1) {
                sb.append("a");
            } else if (i % 8 == 4) {
                sb.append("b");
            } else {
                sb.append("c");
            }
        }

        String string = sb.toString();

        assertEquals(true, trie.add(string));
        assertEquals(true, trie.contains(string));
        assertEquals(1, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix(string.substring(0, 15)));
        assertEquals(false, trie.add(string));

        // checking if the trie still works correctly with smaller strings
        assertEquals(true, trie.add("caccbcacc"));
        assertEquals(true, trie.contains("caccbcacc"));
        assertEquals(2, trie.size());
        assertEquals(2, trie.howManyStartsWithPrefix("caccbcacc"));

        assertEquals(true, trie.add("caccbcacb"));
        assertEquals(true, trie.contains("caccbcacb"));
        assertEquals(3, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix("caccbcacb"));

        // checking whether it works with even longer strings
        string = string + string;
        assertEquals(true, trie.add(string));
        assertEquals(true, trie.contains(string));
        assertEquals(4, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix(string));
        assertEquals(2, trie.howManyStartsWithPrefix(string.substring(0, string.length() / 2)));
    }

    /**
     * This test checks correctness of deleting string from the trie,
     * including double deletion, insertion-deletion-insertion.
     */
    @Test
    public void testRemove() {
        Trie trie = new Trie();

        // remove elements from empty trie
        assertEquals(false, trie.remove(""));
        assertEquals(false, trie.remove("\0"));
        assertEquals(false, trie.remove("a"));

        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.add("ab"));
        assertEquals(true, trie.add("abc"));
        assertEquals(true, trie.add("aB"));

        assertEquals(4, trie.size());

        // remove trie elements
        assertEquals(true, trie.remove("a"));
        assertEquals(3, trie.size());
        assertEquals(false, trie.contains("a"));
        assertEquals(true, trie.contains("aB"));
        assertEquals(true, trie.contains("ab"));
        assertEquals(true, trie.contains("abc"));

        // insertion - deletion - insertion - deletion case
        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.contains("a"));
        assertEquals(4, trie.size());
        assertEquals(true, trie.remove("a"));
        assertEquals(3, trie.size());

        assertEquals(true, trie.remove("ab"));
        assertEquals(true, trie.contains("abc"));
        assertEquals(true, trie.contains("aB"));
        assertEquals(false, trie.contains("ab"));

        // double deletion
        assertEquals(true, trie.remove("abc"));
        assertEquals(false, trie.remove("abc"));
    }

    @Test
    public void testHowManyStartsWithPrefix() {
        Trie trie = new Trie();

        assertEquals(true, trie.add(""));
        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.add("ab"));
        assertEquals(true, trie.add("aB"));
        assertEquals(true, trie.add("abc"));

        // assuming strings don't actually start with an empty string
        assertEquals(0, trie.howManyStartsWithPrefix(""));
        assertEquals(4, trie.howManyStartsWithPrefix("a"));
        assertEquals(2, trie.howManyStartsWithPrefix("ab"));
        assertEquals(1, trie.howManyStartsWithPrefix("abc"));
        assertEquals(1, trie.howManyStartsWithPrefix("aB"));

        assertEquals(true, trie.remove("ab"));
        assertEquals(1, trie.howManyStartsWithPrefix("ab"));
        assertEquals(true, trie.remove("aB"));
        assertEquals(1, trie.howManyStartsWithPrefix("ab"));
        assertEquals(true, trie.remove("a"));
        assertEquals(1, trie.howManyStartsWithPrefix("a"));
    }

    /**
     * This test checks correctness of (serialize) and (deserialize) methods.
     * It serializes a trie and deserializes it
     * and checks the behaviour of the deserialized tree.
     */
    @Test
    public void testSerializeAndDeserialize() {
        Trie trie = new Trie();

        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.add("ab"));
        assertEquals(true, trie.add("abc"));
        assertEquals(true, trie.add("aB"));
        assertEquals(true, trie.add("aBC"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            trie.serialize(baos);
        } catch (IOException e) {
            System.out.println("Input/output exception in serialization test.");
        }

        byte[] serializedTrieData = baos.toByteArray();

        /*
         * IOException specifically will not be handled separately,
         * since the data provided to the stream will not be missing,
         * it only might be incorrect.
         */
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedTrieData)) {
            trie.deserialize(bais);
        } catch (Exception e) {
            System.out.println("Illegal argument.");
        }

        /*
         * The Trie class does not allow us to see its contents
         * so we're going to compare againt expected behaviour
         */

        assertEquals(true, trie.contains("a"));
        assertEquals(true, trie.contains("ab"));
        assertEquals(true, trie.contains("abc"));
        assertEquals(true, trie.contains("aB"));
        assertEquals(true, trie.contains("aBC"));
        assertEquals(false, trie.contains("A"));
        assertEquals(5, trie.size());
    }

    /**
     * This test checks deserialization of incorrect data
     * (meaning: not a correctly serialized tree).
     * The (deserialize) method is expected to throw an expection.
     */
    @Test (expected = IOException.class)
    public void testDeserializeIncorrectData() throws IOException {
        Trie trie = new Trie();

        assertEquals(true, trie.add("a"));
        assertEquals(true, trie.add("ab"));
        assertEquals(true, trie.add("abc"));
        assertEquals(true, trie.add("aB"));
        assertEquals(true, trie.add("aBC"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            trie.serialize(baos);
        } catch (IOException e) {
            System.out.println("Input/output exception in serialization test.");
        }

        byte[] serializedTrieData = baos.toByteArray();

        byte[] incorrectData = new byte[serializedTrieData.length / 2];
        System.arraycopy(serializedTrieData, 0, incorrectData, 0, incorrectData.length / 2);

        ByteArrayInputStream bais = new ByteArrayInputStream(incorrectData);
        // attempt of deserialization of incorrect data will result in an exception
        trie.deserialize(bais);
    }


}