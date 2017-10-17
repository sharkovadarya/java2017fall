package ru.spbau.group202.sharkova.hw5set;

import org.jetbrains.annotations.NotNull;

/**
 * This class implements a set data structure based on BST.
 * It supports insertion and lookup
 * and allows to get the current amount of elements in the set.
 * All set elements are distinct.
 */
public class Set<T extends Comparable> {

    private int size = 0;
    private Node root = null;

    /**
     * This method adds a new value to the set.
     * If the provided value is already present,
     * nothing happens.
     * @param data value to be inserted
     * @return true if the value was not present in the set;
     *         false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean add(@NotNull T data) {
        if (contains(data)) {
            return false;
        }

        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            size++;
            return true;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;
            if (current.data.compareTo(data) > 0) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    size++;
                    return true;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    size++;
                    return true;
                }
            }
        }
    }

    /**
     * This method checks whether the given value
     * is present in the set.
     * @param data value to be found
     * @return true if the value is in the set;
     *         false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean contains(@NotNull T data) {
        Node current = root;

        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            } else if (current.data.compareTo(data) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    /**
     * This method returns the current amount of elements
     * stored in the set.
     * @return number of elements in the set
     */
    public int size() {
        return size;
    }

    /**
     * This utility class represents a BST node.
     * It stores data and "pointers" to its left child
     * and right child.
     */
    private class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
        
        /*
         * This class does not have any getters/setters,
         * as we can access private fields from the outer class anyway.
         */
    }
}
