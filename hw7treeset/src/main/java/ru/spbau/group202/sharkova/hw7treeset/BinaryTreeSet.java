package ru.spbau.group202.sharkova.hw7treeset;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This class implements a set based on a binary search tree.
 *
 * It implements MyTreeSet interface methods,
 * such as first, last, lower, floor, etc.
 * Supported operations: insertion, removal, look-up,
 * iterating in ascending/descending order.
 *
 * Important: null values are allowed as long as
 * the comparator allows for it,
 * but if not, NPE will be thrown.
 * This is the responsibility of the user.
 *
 * Support of custom comparators is provided.
 *
 * @param <T> set elements type parameter
 */
public class BinaryTreeSet<T extends Comparable<T>> extends AbstractSet<T>
                             implements MyTreeSet<T> {

    private int size = 0;
    private boolean reverse = false;
    private Node root = null;
    private Comparator<T> comparator;

    /**
     * Custom comparator constructor.
     */
    public BinaryTreeSet(@NotNull Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Default comparator constructor.
     */
    public BinaryTreeSet() {
        this.comparator = T::compareTo;
    }

    /**
     * Utility constructor used for descendingSet() method.
     */
    private BinaryTreeSet(@NotNull BinaryTreeSet<T> other, boolean reverse) {
        comparator = other.comparator;
        root = other.root;
        size = other.size;
        this.reverse = reverse ^ other.reverse;
    }


     /**
      * This method adds a new value to the set.
      * If the provided value is already present,
      * nothing happens.      *
      * @param data value to be inserted
      * @return true if the value was not present in the set;
      *         false otherwise
      */
    public boolean add(final T data) {
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
            if (comparator.compare(current.data, data) > 0) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    parent.left.parent = parent;
                    size++;
                    return true;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    parent.right.parent = parent;
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
    @Override
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
     * This method returns value of the least set element.
     * @return least set element value
     */
    @Override
    public T first() {
        Node current = root;
        while (left(current, reverse) != null) {
            current = left(current, reverse);
        }

        return current.data;
    }

    /**
     * This method returns value of the greatest set element.
     * @return greatest set element value
     */
    @Override
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
    @Override
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
     * This method returns the greatest element
     * less than the given element or equal to it.
     * @param t element for search
     * @return greatest element less than (t) or equal to it
     *         or null if no such element
     */
    @Override
    public T floor(final T t) {

        if (contains(t)) {
            return t;
        }

        return lower(t);
    }

    /**
     * This method returns the least element
     * strictly greater than the given element or equal to it.
     * @param t element for search
     * @return least element strictly greater than (t) or equal to it
     *         or null if no such element
     */
    @Override
    public T ceiling(final T t) {

        if (contains(t)) {
            return t;
        }

        return higher(t);
    }

    /**
     * This method returns the least element strictly greater
     * than the given element.
     * @param t element for search
     * @return least element strictly greater than (t)
     *         or null if no such element
     */
    @Override
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

    /**
     * This method removes all elements from the set.
     */
    @Override
    public void clear() {
        while (size != 0) {
            remove(first());
        }
    }

    /**
     * This method removes an element from the set by provided value.
     * @param o element to be removed
     * @return true if the element was present
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(final Object o) {

        if (root == null) {
            return false;
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
                return false;
            }

            cmp = comparator.compare(current.data, (T) o);
        }


        Node parent = current.parent;

        // current node has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
                size--;
                return true;
            }

            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        // current node doesn't have a right child
        } else if (current.right == null) {
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
        // current node doesn't have a left child
        } else if (current.left == null) {
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
        // current node has both children
        } else {
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

        size--;
        return true;

    }

    /**
     * This method removes all elements of the given collection
     * from the set.
     * @param collection collection of element to be removed
     * @return true if the set had changed (meaning some elements were deleted)
     */
    @Override
    public boolean removeAll(@NotNull final Collection<?> collection) {
        int oldSize = size;
        for (Object o : collection) {
            remove(o);
        }

        return oldSize != size;
    }

    /**
     * This method removes all set elements except for those
     * which are contained in a given collection.
     * @param collection collection of element to be retained
     * @return true if the set changed.
     */
    @Override
    public boolean retainAll(@NotNull final Collection<?> collection) {

        int oldSize = size;

        for (T t : this) {
            if (!collection.contains(t)) {
                remove(t);
            }
        }

        return oldSize != size;
    }

    /**
     * This method returns number of elements in the set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method returns a set iterator.
     * Iterating is performed in the ascending order.
     * @return set iterator (ascending order)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {

        return new TreeIterator(root, false);
    }
    /**
     * This method returns a set iterator.
     * Iterating is performed in the descending order.
     * @return set iterator (descending order)
     */
    @NotNull
    @Override
    public Iterator<T> descendingIterator() {

        return new TreeIterator(root, true);
    }

    /**
     * This method returns a MyTreeSet object
     * which contains the same elements as this set,
     * but traverses them in descending (relative to comparator) order.
     * @return MyTreeSet object with same elements in descending order
     */
    @NotNull
    @Override
    public MyTreeSet<T> descendingSet() {

        return new BinaryTreeSet<>(this, true);
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
     * This utility class implements a set iterator.
     * Removal by iterator is not supported.
     */
    private class TreeIterator implements Iterator<T> {

        private Node next;
        private boolean reverse = BinaryTreeSet.this.reverse;

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

