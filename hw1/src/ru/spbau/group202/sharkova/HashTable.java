package ru.spbau.group202.sharkova;

/**
 * This class represents a closed addressing hash table.
 * It is possible to insert new elements, get elements by key,
 * remove elements by key, remove all elements,
 * check whether the element of the given key is in the table,
 * get an amount of all keys in the table.
 * */
public class HashTable {
    private final static int TABLE_SIZE = 257;
    private int size;

    // the hash table is an array of linked lists which store entries
    private SinglyLinkedList hashTable[];

    /**
     * Empty hash table constructor.
     * Each cell is initialized
     * as an empty singly-linked list of hash table entries.
     */
    public HashTable() {
        hashTable = new SinglyLinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = new SinglyLinkedList();
        }
        size = 0;
    }

    /**
     * @param string string the hash value of which will be found.
     * @return hash value of the given string fit for the table.
     */
    private int getHashValue(String string) {
        return (string.hashCode() & 0x7fffffff) % TABLE_SIZE;
    }

    /**
     * @param key key of the entry to be found.
     * @return value of the entry with the given key.
     */
    public String get(String key) {
        int hash = getHashValue(key);
        HashTableEntry entry = hashTable[hash].get(key);

        return ((entry == null) ? null : entry.getValue());
    }

    /**
     * This method inserts a new hash table entry to the table.
     * @param key key of the new entry.
     * @param value value of the new entry.
     * @return if the entry with the given key exists,
     *         the old value is returned; null otherwise.
     */
    public String put(String key, String value) {
        int hash = getHashValue(key);

        HashTableEntry entry = hashTable[hash].get(key);
        if (entry != null) {
            String oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {
            hashTable[hash].add(new HashTableEntry(key, value));
            size++;
            return null;
        }
    }

    /**
     * This method removes the hash table entry with the given key.
     * @param key key of the entry to be removed.
     * @return value of the entry if it existed; null otherwise.
     */
    public String remove(String key) {
        int hash = getHashValue(key);

        String returnValue =  hashTable[hash].remove(key);
        if (returnValue != null) {
            size--;
        }

        return returnValue;
    }

    /**
     * @return current amount of entries in the table.
     */
    public int size() {
        return size;
    }

    /**
     * This method checks whether
     * the table contains the entry with the given key.
     * @param key key of the entry to be found.
     * @return true if the table contains the entry; false otherwise.
     */
    public boolean contains(String key) {
        int hash = getHashValue(key);
        HashTableEntry entry = hashTable[hash].get(key);
        return (entry != null);
    }

    /**
     * This method removes all hash table entries.
     */
    public void clear() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i].clear();
        }

        size = 0;
    }

}
