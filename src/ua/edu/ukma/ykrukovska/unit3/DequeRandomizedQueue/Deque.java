package ua.edu.ukma.ykrukovska.unit3.DequeRandomizedQueue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = first;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {

        checkNotNull(item);
        Node previousFirst = first;
        first = new Node();
        first.item = item;
        first.next = previousFirst;

        if (last == null) {
            last = first;
        } else {
            previousFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        checkNotNull(item);
        Node previousOld = last;
        last = new Node();
        last.previous = previousOld;
        last.item = item;

        if (first == null) {
            first = last;
        } else {
            previousOld.next = last;
        }

        size++;

    }

    private void checkNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void checkIfDequeEmpty() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
    }

    public Item removeFirst() {
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

    public Item removeLast() {
        checkIfDequeEmpty();
        Item item = last.item;
        if (size > 1) {
            last = last.previous;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

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

    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();

        System.out.println("Is empty?: " + deque.isEmpty());

        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }

        System.out.println("Last to be removed: " + deque.removeLast());
        System.out.println("Size after removing last: " + deque.size());

        while (!deque.isEmpty()) {
            deque.removeFirst();
        }


        deque.addFirst(10);
        System.out.println("Removed first: " + deque.removeFirst());
        deque.addLast(25);
        System.out.println("Removed last: " + deque.removeLast());

    }

}