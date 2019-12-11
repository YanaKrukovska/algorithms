package ua.edu.ukma.ykrukovska.unit8.symbolTables;

import edu.princeton.cs.algs4.Queue;

public class ST<Key extends Comparable<Key>, Value> {
    private static final int INITIAL_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int size = 0;


    public ST() {
        this(INITIAL_CAPACITY);
    }


    private ST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        Value[] tempValues = (Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        values = tempValues;
        keys = tempKeys;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int amountSmallerThanKey = rank(key);
        if (amountSmallerThanKey < size && keys[amountSmallerThanKey].compareTo(key) == 0) {
            return values[amountSmallerThanKey];
        }
        return null;
    }


    public int rank(Key key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }


    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }


        if (size == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = val;
        size++;


    }


    public void delete(Key key) {
        if (isEmpty()) return;

        int i = rank(key);

        if (i == size || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < size - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        size--;
        keys[size] = null;
        values[size] = null;

        if (size > 0 && size == keys.length / 4) {
            resize(keys.length / 2);
        }

    }


    public void deleteMin() {
        if (!isEmpty()) {
            delete(min());
        }

    }

    public void deleteMax() {
        if (!isEmpty()) {
            delete(max());
        }
    }


    public Key min() {
        return keys[0];
    }


    public Key max() {
        return keys[size - 1];
    }


    public Key select(int k) {
        if (k < 0 || k >= size()) {
            return null;
        }
        return keys[k];
    }


    public Key floor(Key key) {
        if (key == null) {
            return null;
        }
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }


    public Key ceiling(Key key) {
        if (key == null) {
            return null;
        }
        int i = rank(key);
        if (i == size) {
            return null;
        } else {
            return keys[i];
        }
    }


    public int size(Key lo, Key hi) {
        if (lo == null) {
            return 0;
        }
        if (hi == null) {
            return 0;
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }


    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);

        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

}