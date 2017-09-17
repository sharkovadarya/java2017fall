package ru.spbau.group202.sharkova.hw1;

/**
 * This class represents a hash table entry.
 * An entry consists of a key and a value.
 */
public class Pair {

    private String key;
    private String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
        value = newValue;
    }

    public String getKey() {
        return key;
    }

}
