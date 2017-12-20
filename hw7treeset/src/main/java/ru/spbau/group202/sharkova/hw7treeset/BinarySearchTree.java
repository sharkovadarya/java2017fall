package ru.spbau.group202.sharkova.hw7treeset;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree.
 * Supported operations: insertion, removal, look-up,
 * iteration in ascending/descending order,
 * getting first/last/lower/higher element,
 * getting amount of elements in the tree.
 *
 * Custom comparators are supported.
 *
 * @param <T> tree elements type parameter
 * */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private int size = 0;
    private boolean reverse = false;
    private Node root = null;
    private Comparator<T> comparator;

    /**
     * Custom comparator constructor.
     */
    public BinarySearchTree(@NotNull Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Utility constructor for reversed tree.
     */
    public BinarySearchTree(@NotNull BinarySearchTree<T> other, boolean reverse) {
        comparator = other.comparator;
        root = other.root;
        size = other.size;
        this.reverse = reverse ^ other.reverse;
    }

    /**
     * This method checks whether the given value
     * is present in the tree.
     * @param data value to be found
     * @return true if the value is in the tree;
     *         false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean contains(final Object data) {
        Node current = root;

        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            } else if (comparator.compare(current.data, (T) data) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    /**
     * This method adds a new value to the tree.
     * If the provided value is already present,
     * nothing happens.
     * @param data value to be inserted
     */
    public void add(final T data) {

        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;
            if (comparator.compare(current.data, data) > 0) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    parent.left.parent = parent;
                    size++;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    parent.right.parent = parent;
                    size++;
                    return;
                }
            }
        }
    }

    /**
     * This method removes an element from the tree by provided value.
     * @param o element to be removed
     */
    @SuppressWarnings("unchecked")
    public void remove(final Object o) {

        if (root == null) {
            return;
        }

        Node current = root;
        boolean isLeftChild = false;
        int cmp = comparator.compare(current.data, (T) o);
        while (cmp != 0) {
            if (cmp < 0) {
                isLeftChild = false;
                current = current.right;
            } else {
                isLeftChild = true;
                current = current.left;
            }

            if (current == null) {
                return;
            }

            cmp = comparator.compare(current.data, (T) o);
        }


        Node parent = current.parent;

        // current node has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
                size--;
                return;
            }

            removeNoChildrenNode(parent, isLeftChild);
            // current node doesn't have a right child
        } else if (current.right == null) {
            removeNoRightChildNode(current, parent, isLeftChild);
            // current node doesn't have a left child
        } else if (current.left == null) {
            removeNoLeftChildNode(current, parent, isLeftChild);
            // current node has both children
        } else {
            removeNodeWithBothChildren(current, parent, isLeftChild);
        }

        size--;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    /**
     * This method returns a tree iterator.
     * Iterating is performed in the ascending order.
     * @return tree iterator (ascending order)
     */
    @NotNull
    public Iterator<T> iterator() {

        return new TreeIterator(root, false);
    }

    /**
     * This method returns a tree iterator.
     * Iterating is performed in the descending order.
     * @return tree iterator (descending order)
     */
    @NotNull
    public Iterator<T> descendingIterator() {

        return new TreeIterator(root, true);
    }

    /**
     * This method returns value of the least tree element.
     * @return least tree element value
     */
    public T first() {
        Node current = root;
        while (left(current, reverse) != null) {
            current = left(current, reverse);
        }

        return current.data;
    }

    /**
     * This method returns value of the greatest tree element.
     * @return greatest tree element value
     */
    public T last() {
        Node current = root;
        while (right(current, reverse) != null) {
            current = right(current, reverse);
        }

        return current.data;
    }

    /**
     * This method returns the greatest element strictly less
     * than the given element.
     * @param t element for search
     * @return greatest element strictly less than (t)
     *         or null if no such element
     */
    public T lower(final T t) {

        if (compare(t, first()) <= 0) {
            return null;
        }

        T ret = first();
        Node current = root;
        while (current != null) {
            if (compare(current.data, t) < 0) {
                if (compare(current.data, ret) > 0) {
                    ret = current.data;
                }
                current = right(current, reverse);
            } else {
                current = left(current, reverse);
            }
        }

        return compare(ret, t) == 0 ? null : ret;
    }

    /**
     * This method returns the least element strictly greater
     * than the given element.
     * @param t element for search
     * @return least element strictly greater than (t)
     *         or null if no such element
     */
    public T higher(final T t) {

        if (compare(t, last()) >= 0) {
            return null;
        }

        T ret = last();
        Node current = root;
        while (current != null) {
            if (compare(current.data, t) > 0) {
                if (compare(current.data, ret) < 0) {
                    ret = current.data;
                }
                current = left(current, reverse);
            } else {
                current = right(current, reverse);
            }
        }

        return compare(ret, t) == 0 ? null : ret;
    }

    /*
     * The following are utility methods used for correct iterating over tree elements
     * with respect to possible reversed order of the elements
     */

    private Node left(Node node, boolean reverse) {
        return reverse ? node.right : node.left;
    }

    private Node right(Node node, boolean reverse) {
        return reverse ? node.left : node.right;
    }

    private int compare(T t1, T t2) {
        return reverse ? comparator.compare(t2, t1) : comparator.compare(t1, t2);
    }

    /*
     * This utility method is used for removal of nodes with two children.
     * It returns the leftmost child of node right child.
     */
    private Node getSuccessor(Node node) {
        Node successor = null;
        Node successorParent = null;
        Node current = node.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor != node.right) {
            successorParent.left = successor.right;
            successor.right = node.right;
        }

        return successor;
    }

    /*
     * The following are utility methods for removal.
     * The titles are self-explanatory.
     */

    private void removeNoChildrenNode(Node parent, boolean isLeftChild) {

        if (isLeftChild) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    private void removeNoRightChildNode(Node current, Node parent, boolean isLeftChild) {
        if (current == root) {
            root = current.left;
            root.parent = null;

        } else if (isLeftChild) {
            parent.left = current.left;
            parent.left.parent = parent;
        } else {
            parent.right = current.left;
            parent.right.parent = parent;
        }
    }

    private void removeNoLeftChildNode(Node current, Node parent, boolean isLeftChild) {
        if (current == root) {
            root = current.right;
            root.parent = null;
        } else if (isLeftChild) {
            parent.left = current.right;
            parent.left.parent = parent;
        } else {
            parent.right = current.right;
            parent.right.parent = parent;
        }
    }

    private void removeNodeWithBothChildren(Node current, Node parent, boolean isLeftChild) {
        Node successor = getSuccessor(current);
        if (current == root) {
            root = successor;
            root.parent = null;
        } else if (isLeftChild) {
            parent.left = successor;
            parent.left.parent = parent;
        } else {
            parent.right = successor;
            parent.right.parent = parent;
        }

        successor.left = current.left;
        successor.left.parent = successor;
        if (successor.right != null) {
            successor.right.parent = successor;
        }
    }

    /**
     * This utility class represents a BST node.
     * It stores data and "pointers"
     * to its left and right children and parent.
     */
    private class Node {
        private T data;
        private Node left;
        private Node right;
        private Node parent; // only used for traversals

        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }

        /*
         * This class does not have any getters/setters,
         * as we can access private fields from the outer class anyway.
         */
    }

    /**
     * This utility class implements a tree iterator.
     * Removal by iterator is not supported.
     */
    private class TreeIterator implements Iterator<T> {

        private Node next;
        private boolean reverse = BinarySearchTree.this.reverse;

        /**
         * Default constructor with respect to possible reverse order.
         */
        public TreeIterator(Node root, boolean reverse) {
            next = root;

            if (next == null) {
                return;
            }

            this.reverse ^= reverse;

            while (left(next, this.reverse) != null) {
                next = left(next, this.reverse);
            }

        }

        /**
         * This method returns the next element in the iteration.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node r = next;

            if (right(next, reverse) != null) {
                next = right(next, reverse);
                while (left(next, reverse) != null) {
                    next = left(next, reverse);
                }

                return r.data;
            }

            while (true) {
                if (next.parent == null) {
                    next = null;

                    return r.data;
                }

                if (left(next.parent, reverse) == next) {
                    next = next.parent;

                    return r.data;
                }

                next = next.parent;
            }
        }


        /**
         * This method checks whether there are more elements in the iteration.
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
