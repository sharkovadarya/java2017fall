package ru.spbau.group202.sharkova.test1110;

/**
 * This class represents a singly-linked list of hash table entries
 * which allows to add new elements,
 * get elements by key, delete elements by key,
 * remove all elements, get elements by index,
 * get the size of the list.
 */
public class SinglyLinkedList<T, U> {

    private Node head;
    private int size;

    // used for more effective list traversal in getByIndex method
    private int lastIndex;
    private Node lastNode;

    /**
     * Empty singly-linked list constructor.
     */
    public SinglyLinkedList() {

        head = null;
        size = 0;
        lastIndex = Integer.MAX_VALUE;
        lastNode = null;
    }

    /**
     * This method adds a new node containing the provided data
     * at the end of the list.
     * @param data data to be added to the list.
     */
    public void add(HashMapEntry data) {

        Node tmp = new Node(data);
        Node cur = head;

        if (head == null) {
            head = tmp;
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }

            cur.next = tmp;
        }

        size++;
    }

    /**
     * @param key the key of the entry to be found.
     * @return the element with the given key stored in the list.
     */
    public HashMapEntry get(T key) {
        Node cur = head;

        if (cur == null) {
            return null;
        }

        while (cur.next != null && !cur.data.getKey().equals(key)) {
            cur = cur.next;
        }

        if (!cur.data.getKey().equals(key)) {
            return null;
        }

        return cur.data;
    }

    /**
     * @param key key of the element to be removed.
     */
    @SuppressWarnings("unchecked")
        public HashMapEntry<T, U> remove(T key) {

        if (head == null) {
            return null;
        }

        if (head.data.getKey().equals(key)) {
            HashMapEntry returnValue = head.data;
            head = head.next;
            return returnValue;
        }

        /* the case where head is to be deleted has already been handled
         * so if the head is the last element,
         * we haven't found the needed element and return null
         */
        if (head.next == null) {
            return null;
        }

        Node cur = head;

        while (cur.next.next != null
                && !cur.next.data.getKey().equals(key)) {
            cur = cur.next;
        }

        if (cur.next.data.getKey().equals(key)) {
            HashMapEntry returnValue = cur.next.data;
            cur.next = cur.next.next;
            size--;
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
        size = 0;
    }

    /**
     * This utility method iterates over the list
     * and finds an element of given position.
     * @param index index of the element to be found
     * @return entry occupying the given position in the list;
     *         null otherwise
     */
    public HashMapEntry getByIndex(int index) {
        if (index == 0) {
            return head.data;
        }

        Node cur = lastIndex <= index ? lastNode : head;
        int currentIndex = lastIndex < index ? lastIndex : 0;
        while (cur.next != null && currentIndex < index) {
            cur = cur.next;
            currentIndex++;
        }

        if (currentIndex != index) {
            return null;
        }

        lastIndex = index;
        lastNode = cur;

        return cur.data;
    }

    /**
     * @return number of elements in the list
     */
    public int getSize() {

        return size;
    }

    /** This class represents a linked list node;
     * stores given data; can go to the next node in the list.
     */
    class Node {

        private Node next;
        private HashMapEntry data;

        /**
         * Node constructor.
         * Next node does not exist yet so it's value will be null.
         * @param info data that will be stored in the node.
         */
        public Node(HashMapEntry info) {
            next = null;
            data = info;
        }
    }

}