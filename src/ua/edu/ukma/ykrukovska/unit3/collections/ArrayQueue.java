package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Iterator;

public class ArrayQueue<Item> implements Iterable<Item> {

    private int amountOfItems;
    private Item[] queue;

    public ArrayQueue() {
        queue = (Item[]) new Object[2];
        amountOfItems = 0;
    }

    public boolean isEmpty() {
        return amountOfItems == 0;
    }

    public int size() {
        return amountOfItems;
    }

    public void enqueue(Item item) {
        checkNotNull(item);
        queue[amountOfItems++] = item;
        if (queue.length == amountOfItems) {
            resizeArray(2 * queue.length);
        }
    }


    public Item dequeue() {
        checkIfQueueIsEmpty();
        Item item = queue[0];

        for (int i = 0; i < amountOfItems - 1; i++) {
            queue[i] = queue[i + 1];
        }

        queue[amountOfItems] = null;
        amountOfItems--;

        if (amountOfItems > 0 && amountOfItems == queue.length / 4) {
            resizeArray(queue.length / 2);
        }
        return item;
    }

    public Item peek() {
        checkIfQueueIsEmpty();
        return queue[0];
    }


    private void checkIfQueueIsEmpty() {
        if (amountOfItems == 0) {
            throw new java.util.NoSuchElementException();
        }
    }

    private void checkNotNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void resizeArray(int newLength) {
        Item[] extendedQueue = (Item[]) new Object[newLength];
        for (int i = 0; i < amountOfItems; i++) {
            extendedQueue[i] = queue[i];
        }
        queue = extendedQueue;
    }


    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        private int currentItem = 0;

        public boolean hasNext() {
            return currentItem < amountOfItems;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            return queue[++currentItem];

        }
    }


}
