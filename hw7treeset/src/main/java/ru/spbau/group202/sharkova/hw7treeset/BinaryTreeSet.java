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

    private BinarySearchTree<T> bst;

    /**
     * Custom comparator constructor.
     */
    public BinaryTreeSet(@NotNull Comparator<T> comparator) {
        bst = new BinarySearchTree<>(comparator);
    }

    /**
     * Default comparator constructor.
     */
    public BinaryTreeSet() {
        bst = new BinarySearchTree<>(T::compareTo);
    }

    /**
     * Utility constructor used for descendingSet() method.
     */
    private BinaryTreeSet(@NotNull BinaryTreeSet<T> other, boolean reverse) {
        bst = new BinarySearchTree<>(other.bst, reverse);
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

        bst.add(data);
        return true;
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
        return bst.contains(data);
    }

    /**
     * This method returns value of the least set element.
     * @return least set element value
     */
    @Override
    public T first() {
        return bst.first();
    }

    /**
     * This method returns value of the greatest set element.
     * @return greatest set element value
     */
    @Override
    public T last() {
        return bst.last();
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

        return bst.lower(t);
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

        return bst.higher(t);
    }

    /**
     * This method removes all elements from the set.
     */
    @Override
    public void clear() {
        while (size() != 0) {
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

        if (bst.isEmpty() || !contains(o)) {
            return false;
        }

        bst.remove(o);
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
        int oldSize = bst.size();
        for (Object o : collection) {
            remove(o);
        }

        return oldSize != size();
    }

    /**
     * This method removes all set elements except for those
     * which are contained in a given collection.
     * @param collection collection of element to be retained
     * @return true if the set changed.
     */
    @Override
    public boolean retainAll(@NotNull final Collection<?> collection) {

        int oldSize = bst.size();

        for (T t : this) {
            if (!collection.contains(t)) {
                remove(t);
            }
        }

        return oldSize != size();
    }

    /**
     * This method returns number of elements in the set.
     */
    @Override
    public int size() {
        return bst.size();
    }

    /**
     * This method returns a set iterator.
     * Iterating is performed in the ascending order.
     * @return set iterator (ascending order)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {

        return bst.iterator();
    }
    /**
     * This method returns a set iterator.
     * Iterating is performed in the descending order.
     * @return set iterator (descending order)
     */
    @NotNull
    @Override
    public Iterator<T> descendingIterator() {

        return bst.descendingIterator();
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

}

