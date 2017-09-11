package ru.spbau.group202.sharkova;

/**
 * This class represents a hash table entry.
 * An entry consists of a key and a value.
 */
public class HashTableEntry {

    private String key;
    private String value;

    /**
     * Hash table entry constructor from a key and a value.
     * @param key the key of the new entry.
     * @param value the value of the new entry.
     */
    public HashTableEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the value of the entry.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param newValue new value to be stored in the entry.
     */
    public void setValue(String newValue) {
        value = newValue;
    }

    /**
     * @return the key of the entry.
     */
    public String getKey() {
        return key;
    }

}
