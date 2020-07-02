package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        checkNotNull(item);

        Node previousOld = last;
        last = new Node();
        last.previous = previousOld;
        last.item = item;

        if (isEmpty()) {
            first = last;
        } else {
            previousOld.next = last;
        }
        size++;
    }

    public Item peek() {
        checkIfDequeEmpty();
        return first.item;
    }

    public Item dequeue() {
        checkIfDequeEmpty();
        Item item = first.item;

        if (size > 1) {
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        size--;

        return item;
    }

    private void checkNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void checkIfDequeEmpty() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}