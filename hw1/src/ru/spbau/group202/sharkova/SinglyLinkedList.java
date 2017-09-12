package ru.spbau.group202.sharkova;

/**
 * This class represents a singly-linked list of hash table entries
 * which allows to add new elements,
 * get elements by key and delete elements by key.
 */
public class SinglyLinkedList {

    private Node head;

    /**
     * Empty singly-linked list constructor.
     */
    public SinglyLinkedList() {
        head = null;
    }

    /**
     * This method adds a new node containing the provided data
     * at the end of the list.
     * @param data data to be added to the list.
     */
    public void add(HashTableEntry data) {

        Node tmp = new Node(data);
        Node cur = head;

        if (head == null) {
            head = tmp;
        } else {
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }

            cur.setNext(tmp);
        }
    }

    /**
     * @param key the key of the entry to be found.
     * @return the element with the given key stored in the list.
     */
    public HashTableEntry get(String key) {
        Node cur = head;

        if (cur == null) {
            return null;
        }

        while (cur.getNext() != null && !cur.getData().getKey().equals(key)) {
            cur = cur.getNext();
        }

        if (!cur.getData().getKey().equals(key)) {
            return null;
        }

        return cur.getData();
    }

    /**
     * @param key key of the element to be removed.
     */
    public String remove(String key) {

        if (head == null) {
            return null;
        }

        if (head.getData().getKey().equals(key)) {
            String returnValue = head.getData().getValue();
            head = head.getNext();
            return returnValue;
        }

        /* the case where head is to be deleted has already been handled
         * so if the head is the last element,
         * we haven't found the needed element and return null
         */
        if (head.getNext() == null) {
            return null;
        }

        Node cur = head;

        while (cur.getNext().getNext() != null
               && !cur.getNext().getData().getKey().equals(key)) {
            cur = cur.getNext();
        }

        if (cur.getNext().getData().getKey().equals(key)) {
            String returnValue = cur.getNext().getData().getValue();
            cur.setNext(cur.getNext().getNext());
            return returnValue;
        }

        return null;
    }

    /**
     * This method removes all elements from the list.
     */
    public void clear() {

        /* Java has automatic garbage collection
         * so simply setting the list's head to null should be legit */
        head = null;
    }

}
