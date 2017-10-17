package ru.spbau.group202.sharkova.test1110;

public class HashMapEntry<T, U> {
    private T key;
    private U value;
    private HashMapEntry before;
    private HashMapEntry after;

    public HashMapEntry(T key, U value, HashMapEntry<T, U> before, HashMapEntry<T, U> after) {
        this.key = key;
        this.value = value;
        this.before = before;
        this.after = after;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T newKey) {
        key = newKey;
    }

    public U getValue() {
        return value;
    }

    public void setValue(U newValue) {
        value = newValue;
    }

    public HashMapEntry getAfter() {
        return after;
    }

    public HashMapEntry getBefore() {
        return before;
    }

    public void setBefore(HashMapEntry before) {
        this.before = before;
    }

    public void setAfter(HashMapEntry after) {
        this.after = after;
    }
}