package ru.spbau.group202.sharkova.hw1;

/**
 * This class represents a closed addressing hash table.
 * Collisions are resolved by chaining.
 * It is possible to insert new elements, get elements by key,
 * remove elements by key, remove all elements,
 * check whether the element of the given key is in the table,
 * get an amount of all keys in the table.
 * */
public class  HashTable {

    /* The table has a maximal acceptable size,
     * after achieving which the table will be resized.
     * The maximal size (maxSize variable) is calculated
     * as (THRESHOLD) per cent of tableSize
     */
    private final static double THRESHOLD = 0.75;
    /* good hash table sizes
     * source: http://www.orcca.on.ca/~yxie/courses/cs2210b-2011/htmls/extra/PlanetMath_%20goodhashtable.pdf */
    private final static int goodTableSizes[] = {53, 97, 193, 389, 769,
            1543, 3079, 6151,
            12289, 24593, 49157,
            98317, 196613, 393241,
            786433, 1572869, 3145739,
            6291469};

    private int tableSizesIndex = 0; // index of current size in goodTableSizes
    private int tableSize = goodTableSizes[0];
    private int maxSize = (int) (THRESHOLD * tableSize);
    private int size = 0;

    // the hash table is an array of linked lists which store entries
    private SinglyLinkedList hashTable[];


    /**
     * @param string string the hash value of which will be found.
     * @return hash value of the given string fit for the table.
     */
    private int getHashValue(String string) {
        return (string.hashCode() & 0x7fffffff) % tableSize;
    }

    /**
     * Empty hash table constructor.
     * Each cell is initialized
     * as an empty singly-linked list of hash table entries.
     */
    public HashTable() {
        hashTable = new SinglyLinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new SinglyLinkedList();
        }
    }

    public String get(String key) {
        int hash = getHashValue(key);
        Pair entry = hashTable[hash].get(key);

        return ((entry == null) ? null : entry.getValue());
    }

    /**
     * This utility method resizes the hash table when it gets top big.
     * A new array of bigger size is created;
     * elements of the old array are rehashed and copied.
     */
    private void resize() {
        if (tableSizesIndex >= goodTableSizes.length - 1) {
            tableSize *= 2;
            tableSize--; // not a very good decision but still something
        } else {
            tableSize = goodTableSizes[++tableSizesIndex];
        }

        maxSize = (int) (THRESHOLD * tableSize);

        SinglyLinkedList oldHashTable[] = hashTable;
        hashTable = new SinglyLinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new SinglyLinkedList();
        }
        size = 0;

        for (SinglyLinkedList list : oldHashTable) {
            if (list.getSize() != 0) {
                int index = 0;
                Pair entry = list.getByIndex(index);
                while (entry != null) {
                    /* Since rehashing takes place,
                     * we need to put all entries in again */
                    put(entry.getKey(), entry.getValue());
                    entry = list.getByIndex(++index);
                }
            }
        }

    }

    /**
     * This method inserts a new hash table entry to the table.
     * If an entry with the given key already exists,
     * its value is replaced by the new value.
     * @param key key of the new entry.
     * @param value value of the new entry.
     * @return if the entry with the given key exists,
     *         the old value is returned; null otherwise.
     */
    public String put(String key, String value) {
        int hash = getHashValue(key);

        Pair entry = hashTable[hash].get(key);
        if (entry != null) {
            String oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {
            hashTable[hash].add(new Pair(key, value));
            size++;

            if (size == maxSize) {
                resize();
            }

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
        Pair entry = hashTable[hash].get(key);
        return (entry != null);
    }

    /**
     * This method removes all hash table entries.
     */
    public void clear() {
        for (int i = 0; i < tableSize; i++) {
            hashTable[i].clear();
        }

        size = 0;
    }

}
