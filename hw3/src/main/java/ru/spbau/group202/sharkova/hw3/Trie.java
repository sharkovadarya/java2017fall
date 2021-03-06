package ru.spbau.group202.sharkova.hw3;

import java.io.*;
import java.util.Arrays;

/**
 * This class implements a trie data structure.
 * A trie is used to store and look up strings.
 * A trie is a rooted tree with symbols on its edges.
 * This trie supports insertion and removal of a string,
 * checking whether the trie contains a given string/prefix,
 * counting how many elements start with a given prefix.
 */
public class Trie implements Serializable {
    // maximal string length
    private int maxLength = 256;

    private Vertex[] children;
    private int capacity = 1;
    private int size = 0;

    /**
     * Empty trie constructor.
     * The 0th element of the (children) array is a utility element
     * and thus is not used for storing a symbol directly.
     */
    public Trie() {
        children = new Vertex[maxLength];

        for (int i = 0; i < maxLength; i++) {
            children[i] = new Vertex();
        }

        Arrays.fill(childVertex(0).next, -1);
    }

    /**
     * This method adds a string to the trie.
     * It traverses the trie until there is no prefix matching,
     * then adds new edges until the string is fully stored.
     * Thus the insertion is performed in O(|element|) time.
     * @param element string to be added
     * @return true if the string was not in the trie yet;
     *         false otherwise
     */
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }

        char[] charArray = element.toCharArray();
        int s = 0;

        // traverse the children
        for (char symbol : charArray) {
            int c = index(symbol);
            childVertex(s).prefixes++;
            if (childVertex(s).getNext(c) == -1) {
                if (capacity == maxLength) {
                    resize();
                }

                childVertex(capacity).prefixes = 1;
                Arrays.fill(childVertex(capacity).next, -1);
                childVertex(s).setNext(c, capacity++);
            }

            s = childVertex(s).getNext(c);
        }

        childVertex(s).isLeaf = true;
        size++;

        return true;
    }

    /**
     * This method checks whether the given string is in the trie.
     * The search is performed in O(|element|) time.
     * @param element the string to be found
     * @return true if the string is in the trie;
     *         false otherwise
     */
    public boolean contains(String element) {
        /*
         * The traverse() method gets the index of the vertex
         * corresponding to the (element) string.        
         */
        int index = traverse(element);

        return index != -1 && childVertex(index).isLeaf;
    }

    /**
     * This method removes a given string from the trie.
     * Deletion works in O(|element|) time.
     * @param element the string to be removed
     * @return true if the string was in the trie;
     *         false otherwise
     */
    public boolean remove(String element) {
        if (element.length() == 0 || !(contains(element))) {
            return false;
        }

        size--;
        removeHelper(0, element.toCharArray(), 0, element.length());

        /*
         * if the element was not in the tree, correct value (false)
         *has already been returned
         */
        return true;
    }

    /**
     * This method returns the current number of strings in the trie.
     * The action is performed in O(1) time.
     * @return the trie size
     */
    public int size() {
        return size;
    }

    /**
     * This method calculates the number of currently stored strings
     * with given prefix.
     * The action is performed in O(|prefix|) time.
     * @param prefix prefix to be matched against
     * @return number of string in trie that match the given prefix
     */
    public int howManyStartsWithPrefix(String prefix) {

        if (prefix.length() == 0 || !containsPrefix(prefix)) {
            return 0;
        }

        char[] charArray = prefix.toCharArray();
        int s = 0;
        for (char symbol : charArray) {
            int c = index(symbol);
            s = childVertex(s).getNext(c);
        }

        return contains(prefix) ? childVertex(s).prefixes : childVertex(s).prefixes - 1;
    }

    /**
     * This method serializes the trie.
     * @param out output stream to which the serialized data is written
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);

        oos.writeInt(maxLength);
        for (Vertex v : children) {
            v.serialize(oos);
        }
        oos.writeInt(capacity);
        oos.writeInt(size);

        oos.close();
    }

    /**
     * This method reads a serialized trie from the given stream,
     * deserializes it and replaces the current trie
     * with the deserialized one.
     * @param in input stream from which data to be deserialized is read
     */
    public void deserialize(InputStream in) throws IOException {

        /* IOExceptions are not handled here and will be thrown further
         * as the signature requires it.
         */

        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            maxLength = ois.readInt();
            for (int i = 0; i < maxLength; i++) {
                childVertex(i).deserialize(ois);
            }
            capacity = ois.readInt();
            size = ois.readInt();
        } catch (ClassNotFoundException e) {
            System.out.println("Illegal serialization; " +
                    "the trie cannot be deserialized.");
        }

    }


    /*
    * this utility method is basically a shortcut
    * for calculating the index of a symbol
    * in the (next) array of a vertex
    */
    private int index(char symbol) {
        return (int) symbol;
    }

    /**
     * This utility method allows for x2 longer strings.
     * It copies the old (children) array into a new, longer one.
     */
    private void resize() {
        Vertex[] newTrie = new Vertex[maxLength * 2];
        maxLength *= 2;

        for (int i = 0; i < newTrie.length; i++) {
            newTrie[i] = new Vertex();
        }
        System.arraycopy(children, 0, newTrie, 0, children.length);
        children = newTrie;
    }

    /**
     * This utility method traverses the trie.
     * It is used for checking whether a string is in the trie.
     */
    private int traverse(String string)
    {
        char[] charArray = string.toCharArray();
        int s = 0;

        for (char symbol : charArray) {
            int c = index(symbol);
            if (childVertex(s).getNext(c) == -1) {
                return -1;
            }
            s = childVertex(s).getNext(c);
        }

        return s;
    }

    /**
     * This utility method is used to check
     * whether there are any strings with given prefix.
     */
    private boolean containsPrefix(String prefix) {
        int s = traverse(prefix);

        return s != -1;
    }

    /**
     * This utility method is used for string removal.
     * It unmarks leaf nodes.
     */
    private boolean removeHelper(int idx, char[] charArray, int level, int length) {
        if (level == length) {
            childVertex(idx).isLeaf = false;
            if (charArray.length == 1) {
                childVertex(idx).prefixes--;
            }
            return childVertex(idx).isFree();
        } else {
            int c = index(charArray[level]);
            if (removeHelper(childVertex(idx).getNext(c), charArray, level + 1, length)) {
                childVertex(childVertex(idx).getNext(c)).clear();
                return (!childVertex(idx).isLeaf && childVertex(idx).isFree());
            }
        }

        childVertex(idx).prefixes--;

        return false;
    }



    /**
     * This utility class represents a trie vertex.
     * It stores an array that represents edges
     * and a mark of whether the vertex is a leaf.
     */
    class Vertex implements Serializable {
        private static final int MAX_SIZE = 256;
        public int prefixes;

        int[] next = new int[MAX_SIZE];
        boolean isLeaf;

        /**
         * This method clears the vertex
         * by unmarking it as a leaf and
         * setting the (next) array to its initial state.
         */
        public void clear() {
            isLeaf = false;
            Arrays.fill(next, -1);
        }

        /**
         * This method checks whether the are any edges from this vertex.
         */
        public boolean isFree() {
            for (int e : next) {
                if (e != -1) {
                    return false;
                }
            }
            return true;
        }

        public int getNext(int idx) {
            return next[idx];
        }

        public void setNext(int idx, int value) {
            next[idx] = value;
        }

        /**
         * This utility method is used for trie serialization.
         * @param oos a stream to which data will be written
         */
        public void serialize(ObjectOutputStream oos) throws IOException {
            /*
             * This method is not using try-catch statements,
             * because any exception is not to be handled here.
             * The signature requires for it to be thrown further.
             */
            oos.writeObject(next);
            oos.writeBoolean(isLeaf);
        }

        /**
         * This utility method is used for trie deserialization.
         * @param ois a stream from which data will be read
         * */
        public void deserialize(ObjectInputStream ois)
                throws ClassNotFoundException, IOException {
            next = (int[]) ois.readObject();
            isLeaf = ois.readBoolean();
        }

    }
    
    private Vertex childVertex(int idx) {
        return children[idx];
    }

}
