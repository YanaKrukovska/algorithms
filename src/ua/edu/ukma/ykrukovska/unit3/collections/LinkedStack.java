package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public LinkedStack() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void push(Item item) {
        Node previousFirst = first;
        first = new Node();
        first.item = item;
        first.next = previousFirst;
        size++;
    }

    public Item pop() {
        checkIfEmpty();
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public Item peek() {
        checkIfEmpty();
        return first.item;
    }

    private void checkIfEmpty() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}

