package ru.spbau.group202.sharkova;

/** This class represents a linked list node;
 * stores given data; can go to the next node in the list.
 */
public class Node {

    private Node next;
    private HashTableEntry data;

    /**
     * Node constructor.
     * Next node does not exist yet so it's value will be null.
     * Stored data will be the provided data.
     * @param info data that will be stored in the node.
     */
    public Node(HashTableEntry info) {
        next = null;
        data = info;
    }

    /**
     * @return the data stored inside the node.
     */
    public HashTableEntry getData() {

        return data;
    }

    /**
     * @param newData new data to be stored instead of the current data.
     */
    public void setData(HashTableEntry newData) {

        data = newData;
    }

    /**
     * @return next node.
     */
    public Node getNext() {

        return next;
    }

    /**
     * @param newNext the node that will be the new next node
     *                instead of the current one.
     */
    public void setNext(Node newNext) {

        next = newNext;
    }


}
