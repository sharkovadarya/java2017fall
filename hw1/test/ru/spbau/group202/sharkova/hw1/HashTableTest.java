package ru.spbau.group202.sharkova.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

// javadoc comments are not provided since the names are self-explanatory
public class HashTableTest {

    @Test
    public void testEmptyTableConstructor() {
        HashTable hashTable = new HashTable();

        assertEquals(0, hashTable.size());
        assertEquals(false, hashTable.contains(""));
        assertEquals(null, hashTable.get(""));
        assertEquals(false, hashTable.contains("asdfghjkl;"));
        assertEquals(null, hashTable.get("asdfghjkl;"));
    }

    @Test
    public void testPutNewElements() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", "\n"));
        assertEquals(null, hashTable.put(" ", "\n\n"));
    }

    @Test
    public void testReplaceElements() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", "\n"));
        assertEquals("\n", hashTable.put("", " "));
    }

    /**
     * This method tries to retrieve a nonexistent element,
     * then this element is added and the retrieval should be successful.
     */
    @Test
    public void testGetNonexistentElement() {
        HashTable hashTable = new HashTable();
        assertEquals(null, hashTable.get(""));
        assertEquals(null, hashTable.put("", " "));
        assertEquals(" ", hashTable.get(""));
    }

    @Test
    public void testGetTableElement() {
        HashTable hashTable = new HashTable();
        assertEquals(null, hashTable.put("", " "));
        assertEquals(" ", hashTable.get(""));
    }

    /**
     * This method tries to check availability of a nonexistent element,
     * then this element is added and the check should be successful.
     */
    @Test
    public void testContainsNonexistentElement() {
        HashTable hashTable = new HashTable();

        assertEquals(false, hashTable.contains(""));
        assertEquals(null, hashTable.put("", " "));
        assertEquals(false, hashTable.contains(" "));
    }

    @Test
    public void testContainsTableElement() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", " "));
        assertEquals(true, hashTable.contains(""));
    }

    @Test
    public void testPutEqualHashElements() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val1"));
        assertEquals(null, hashTable.put("0-41k", "val2"));
    }

    @Test
    public void testGetEqualHashElements() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val2"));

        assertEquals("val1", hashTable.get("0-42L"));
        assertEquals("val2", hashTable.get("0-43-"));
        assertEquals(null, hashTable.get("0-41k"));
    }

    @Test
    public void testContainsEqualHashElements() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val2"));

        assertEquals(true, hashTable.contains("0-42L"));
        assertEquals(true, hashTable.contains("0-43-"));
        assertEquals(false, hashTable.contains("0-41k"));
    }

    @Test
    public void testReplaceEqualHashElements() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val2"));
        assertEquals("val1", hashTable.put("0-42L", "val3"));
        assertEquals("val3", hashTable.get("0-42L"));
        assertEquals("val2", hashTable.get("0-43-"));
    }

    @Test
    public void testSize() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", "1"));
        assertEquals(null, hashTable.put(" ", "2"));
        assertEquals(null, hashTable.put("\n", "3"));

        assertEquals(3, hashTable.size());
    }

    @Test
    public void testClear() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", "1"));
        assertEquals(null, hashTable.put(" ", "2"));
        assertEquals(null, hashTable.put("\n", "3"));

        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertEquals(null, hashTable.get(""));
        assertEquals(null, hashTable.get(" "));
        assertEquals(null, hashTable.get("\n"));
        assertEquals(false, hashTable.contains(""));
        assertEquals(false, hashTable.contains(" "));
        assertEquals(false, hashTable.contains("\n"));
    }

    @Test
    public void testRemoveTableEntries() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.put("", "1"));
        assertEquals(null, hashTable.put(" ", "2"));
        assertEquals(null, hashTable.put("\n", "3"));

        assertEquals("1", hashTable.remove(""));
        assertEquals("2", hashTable.remove(" "));
        assertEquals("3", hashTable.remove("\n"));
    }

    @Test
    public void testRemoveNonexistentEntries() {
        HashTable hashTable = new HashTable();

        assertEquals(null, hashTable.remove(""));

        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.remove("a"));
    }

    @Test
    public void testRemoveEqualHashElements() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val2"));

        assertEquals("val1", hashTable.remove("0-42L"));
        assertEquals(null, hashTable.get("0-42L"));
        assertEquals("val2", hashTable.get("0-43-"));
        assertEquals(false, hashTable.contains("0-42L"));
        assertEquals(true, hashTable.contains("0-43-"));
    }

    @Test
    public void testResize() {
        HashTable hashTable = new HashTable();

        for (int i = 0; i < 38; i++) {
            assertEquals(null,
                    hashTable.put(Integer.toString(2 * i), Integer.toString(2 * i + 1)));
        }

        assertEquals(38, hashTable.size());
        assertEquals(null, hashTable.put("76", "77"));
        assertEquals(39, hashTable.size());

        // then the table resizing happens

        assertEquals(null, hashTable.put("78", "79"));
        assertEquals(40, hashTable.size());

        for (int i = 0; i < 40; ++i) {
            assertEquals(Integer.toString(2 * i + 1),
                    hashTable.get(Integer.toString(2 * i)));
        }
    }

    @Test
    public void testResizeWithEqualHashes() {
        HashTable hashTable = new HashTable();

        // these keys are expected to have equal hash codes
        assertEquals(null, hashTable.put("0-42L", "val1"));
        assertEquals(null, hashTable.put("0-43-", "val2"));
        assertEquals(null, hashTable.put("0-41k", "val3"));

        for (int i = 0; i < 36; i++) {
            assertEquals(null,
                         hashTable.put(Integer.toString(2 * i),
                                       Integer.toString(2 * i + 1)));
        }

        assertEquals(null, hashTable.put(" ", "  "));

        // now the table should've been resized
        assertEquals("val1", hashTable.get("0-42L"));
        assertEquals("val2", hashTable.get("0-43-"));
        assertEquals("val3", hashTable.get("0-41k"));

        for (int i = 0; i < 36; i++) {
            assertEquals(Integer.toString(2 * i + 1),
                    hashTable.get(Integer.toString(2 * i)));
        }
    }

    // Insert a lot of elements and check their availability
    @Test
    public void testBigSizeHashTable() {
        HashTable hashTable = new HashTable();

        for (int i = 0; i < 1000000; i++) {
            assertEquals(null,
                                 hashTable.put(Integer.toString(2 * i),
                                               Integer.toString(2 * i + 1)));
        }

        assertEquals(1000000, hashTable.size());
        for (int i = 0; i < 1000000; i++) {
            assertEquals(Integer.toString(2 * i + 1),
                         hashTable.get(Integer.toString(2 * i)));
        }
    }
}
