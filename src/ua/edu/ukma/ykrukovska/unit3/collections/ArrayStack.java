package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] items;
    private int amountOfItems;

    public ArrayStack() {
        items = (Item[]) new Object[2];
        amountOfItems = 0;
    }

    public boolean isEmpty() {
        return amountOfItems == 0;
    }

    public int size() {
        return amountOfItems;
    }

    private void resize(int newLength) {
        Item[] resizedStack = (Item[]) new Object[newLength];
        for (int i = 0; i < amountOfItems; i++) {
            resizedStack[i] = items[i];
        }
        items = resizedStack;
    }

    public void push(Item item) {
        if (amountOfItems == items.length) resize(2 * items.length);
        items[amountOfItems++] = item;
    }

    public Item pop() {
        checkIfEmpty();
        Item item = items[amountOfItems - 1];
        items[amountOfItems - 1] = null;
        amountOfItems--;
        if (amountOfItems > 0 && amountOfItems == items.length / 4) resize(items.length / 2);
        return item;
    }

    private void checkIfEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public Item peek() {
        checkIfEmpty();
        return items[amountOfItems - 1];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int size;

        public ArrayIterator() {
            size = amountOfItems - 1;
        }

        public boolean hasNext() {
            return size >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[size--];
        }
    }

}
