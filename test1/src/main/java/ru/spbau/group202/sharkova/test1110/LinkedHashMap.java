package ru.spbau.group202.sharkova.test1110;

import com.sun.istack.internal.Nullable;

import java.util.*;

public class LinkedHashMap<T, U> implements Map {

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

    private HashMapEntry header;
    private HashMapEntry last;

    public LinkedHashMap() {
        hashTable = new SinglyLinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new SinglyLinkedList();
        }
    }

    /**
     * This method removes an entry from a table by a provided key.
     * @param o key of the element to be deleted
     * @return value of the deleted element or null if not present
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object remove(Object o) {
        if (!containsKey(o)) {
            return false;
        }

        int hash = getHashValue(o);
        HashMapEntry removed = hashTable[hash].remove(o);
        if (removed == null) {
            return null;
        }

        removed.getAfter().setBefore(removed.getBefore());
        removed.getBefore().setAfter(removed.getAfter());

        Object returnValue = removed.getValue();
        size--;

        return returnValue;
    }

    /**
     * This method finds object by given key.
     * @param o key of the element to be found
     * @return found object or null if key not found
     */
    @Override
    @SuppressWarnings("unchecked")
    @Nullable
    public Object get(Object o) {
        try {
            int hash = getHashValue(o);
            HashMapEntry entry = hashTable[hash].get(o);

            return ((entry == null) ? null : entry.getValue());
        } catch (Exception e) {
            System.out.println("Incorrect key");
        }

        return null;
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

    /**
     * This method returns a set of hash table entry keys.
     */
    public Set keySet() {
        return keySet();
    }

    /**
     * This method checks if the table contains
     * entry with the given key.
     * @param o key of the element to be found
     * @return true if found; false otherwise
     * */
    @SuppressWarnings("unchecked")
    public boolean containsKey(Object o) {
        int hash = getHashValue(o);
        HashMapEntry entry = hashTable[hash].get(o);
        return (entry != null);
    }

    /**
     * This method inserts new hash table entry;
     * If the entry with the given key has already been inserted;
     * old value is returned and replaces with new one;
     * otherwise null is returned
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public Object put(Object o, Object o2) {

        if (o == null) {
            return null;
        }

        int hash = getHashValue(o);

        HashMapEntry entry = hashTable[hash].get(o);
        if (entry != null) {
            U oldValue = (U) entry.getValue();
            entry.setValue(o2);
            return oldValue;
        } else {

            HashMapEntry newEntry = new HashMapEntry(o, o2, last, null);
            hashTable[hash].add(newEntry);
            last.setAfter(newEntry);
            last = newEntry;
            size++;

            if (size == maxSize) {
                resize();
            }

            return null;
        }
    }

    public void putAll(Map map) {

    }
    
    public int size() {
        return size;
    }

    /**
     * This method returns a set of hash table entries.
     */
    public Set<Map.Entry> entrySet() {
        return entrySet();
    }

    /**
     * This method checks if there is an entry with the given value.
     */
    public boolean containsValue(Object o) {
        for (SinglyLinkedList s : hashTable) {
            int listSize = s.getSize();
            for (int i = 0; i < listSize; ++i) {
                HashMapEntry entry = s.getByIndex(i);
                if (entry.getValue().equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method returns a collection of hash table values.
     */
    @Override
    public Collection values() {
        return new Values();
    }

    /**
     * This utility method calculated object hash values.
     */
    private int getHashValue(Object o) {
        String string = o.toString();
        return (string.hashCode() & 0x7fffffff) % tableSize;
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
                HashMapEntry entry = list.getByIndex(index);
                while (entry != null) {
                    /* Since rehashing takes place,
                     * we need to put all entries in again */
                    put(entry.getKey(), entry.getValue());
                    entry = list.getByIndex(++index);
                }
            }
        }

    }
        
    class HashTableIterator implements Iterator {

        HashMapEntry current;
        HashMapEntry next;

        public HashTableIterator(HashMapEntry current, HashMapEntry next) {
            this.current = current;
            this.next = next;
        }

        public HashTableIterator() {
            this.current = header;
            this.next = header.getAfter();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            return next;
        }
    }

    @SuppressWarnings("unchecked")
    class KeyIterator extends HashTableIterator {
        public T next() {
            return (T) next.getKey();
        }
    }

    @SuppressWarnings("unchecked")
    class ValueIterator extends HashTableIterator {
        public T next() {
            return (T) next.getValue();
        }
    }

    @SuppressWarnings("unchecked")
    class EntryIterator extends HashTableIterator {
        public HashMapEntry<T, U> next() {
            return (HashMapEntry) next;
        }
    }

    class KeySet extends AbstractSet<T> {

        @Override
        @SuppressWarnings("unchecked")
        public Iterator<T> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    class EntrySet extends AbstractSet<Entry<T, U>> {
        @Override
        @SuppressWarnings("unchecked")
        public Iterator<Entry<T, U>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    @SuppressWarnings("unchecked")
    class Values extends AbstractCollection<U> {
        @Override
        public Iterator<U> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }



}

