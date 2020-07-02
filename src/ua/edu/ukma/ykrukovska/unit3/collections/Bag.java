package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int amountOfItems;

    private class Node {
        private Item item;
        private Node next;
    }

    public Bag() {
        first = null;
        amountOfItems = 0;
    }

    public boolean isEmpty() {
        return amountOfItems == 0;
    }

    public int size() {
        return amountOfItems;
    }

    public void add(Item item) {
        Node previousFirst = first;
        first = new Node();
        first.item = item;
        first.next = previousFirst;
        amountOfItems++;
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
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}