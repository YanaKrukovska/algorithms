package ua.edu.ukma.ykrukovska.unit3.DequeRandomizedQueue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int amountOfItems;
    private Item[] queue;

    public RandomizedQueue() {
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

    public Item dequeue() {
        checkIfQueueIsEmpty();
        int random = calculateRandomNumber();
        Item item = queue[random];
        queue[random] = queue[--amountOfItems];
        queue[amountOfItems] = null;

        if (amountOfItems > 0 && amountOfItems == queue.length / 4) {
            resizeArray(queue.length / 2);
        }
        return item;
    }

    private void checkIfQueueIsEmpty() {
        if (amountOfItems == 0) {
            throw new java.util.NoSuchElementException();
        }
    }

    public Item sample() {
        checkIfQueueIsEmpty();
        return queue[calculateRandomNumber()];
    }


    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] items;
        private int currentItem;

        public RandomIterator() {
            copyQueue();
            StdRandom.shuffle(items);
        }

        private void copyQueue() {
            items = (Item[]) new Object[amountOfItems];
            for (int i = 0; i < amountOfItems; i++) {
                items[i] = queue[i];
            }
        }

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
            return items[currentItem++];


        }
    }

    private int calculateRandomNumber() {
        return StdRandom.uniform(0, amountOfItems);
    }


    public static void main(String[] args) {

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }

        System.out.println("Size = " + randomizedQueue.size());

        for (Integer i : randomizedQueue) {
            System.out.println(i);
        }

        System.out.println("sample:" + randomizedQueue.sample());
        System.out.println("sample:" + randomizedQueue.sample());

        System.out.println("dequeue");
        while (!randomizedQueue.isEmpty()) {
            System.out.println(randomizedQueue.dequeue());
        }
        System.out.println("Size = " + randomizedQueue.size());
    }

}