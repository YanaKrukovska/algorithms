package ua.edu.ukma.ykrukovska.unit7.collisionSystem;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
    private Key[] priorityQueue;
    private int amount;
    private Comparator<Key> comparator;


    public MinPQ(int initCapacity) {
        priorityQueue = (Key[]) new Object[initCapacity + 1];
        amount = 0;
    }

    public MinPQ() {
        this(1);
    }


    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        priorityQueue = (Key[]) new Object[initCapacity + 1];
        amount = 0;
    }


    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }


    public MinPQ(Key[] keys) {
        amount = keys.length;
        priorityQueue = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < amount; i++) {
            priorityQueue[i + 1] = keys[i];
        }
        for (int k = amount / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMinHeap();
    }


    public boolean isEmpty() {
        return amount == 0;
    }


    public int size() {
        return amount;
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return priorityQueue[1];
    }

    private void resize(int capacity) {
        assert capacity > amount;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= amount; i++) {
            temp[i] = priorityQueue[i];
        }
        priorityQueue = temp;
    }

    public void insert(Key x) {
        if (amount == priorityQueue.length - 1) {
            resize(2 * priorityQueue.length);
        }

        priorityQueue[++amount] = x;
        swim(amount);
        assert isMinHeap();
    }


    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = priorityQueue[1];
        exchange(1, amount--);
        sink(1);
        priorityQueue[amount + 1] = null;
        if ((amount > 0) && (amount == (priorityQueue.length - 1) / 4)) resize(priorityQueue.length / 2);
        assert isMinHeap();
        return min;
    }


    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= amount) {
            int j = 2 * k;
            if (j < amount && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }


    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) priorityQueue[i]).compareTo(priorityQueue[j]) > 0;
        } else {
            return comparator.compare(priorityQueue[i], priorityQueue[j]) > 0;
        }
    }

    private void exchange(int i, int j) {
        Key swap = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = swap;
    }

    // is priorityQueue[1..amount] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= amount; i++) {
            if (priorityQueue[i] == null) return false;
        }
        for (int i = amount + 1; i < priorityQueue.length; i++) {
            if (priorityQueue[i] != null) return false;
        }
        if (priorityQueue[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of priorityQueue[1..amount] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > amount) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= amount && greater(k, left)) return false;
        if (right <= amount && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }


    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<Key>(size());
            else copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= amount; i++)
                copy.insert(priorityQueue[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }


}
