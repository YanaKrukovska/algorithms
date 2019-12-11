package ua.edu.ukma.ykrukovska.unit8.symbolTables;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node previous, next;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.value = val;
            this.size = size;
        }
    }


    public BST() {
    }


    public boolean isEmpty() {
        return size() == 0;
    }


    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }


    public boolean contains(Key key) {
        return get(key) != null;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.previous, key);
        } else if (cmp > 0) {
            return get(node.next, key);
        } else {
            return node.value;
        }
    }


    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.previous = put(node.previous, key, val);
        } else if (cmp > 0) {
            node.next = put(node.next, key, val);
        } else {
            node.value = val;
        }
        node.size = 1 + size(node.previous) + size(node.next);
        return node;
    }


    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);

    }

    private Node deleteMin(Node x) {
        if (x.previous == null) return x.next;
        x.previous = deleteMin(x.previous);
        x.size = size(x.previous) + size(x.next) + 1;
        return x;
    }


    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);

    }

    private Node deleteMax(Node x) {
        if (x.next == null) return x.previous;
        x.next = deleteMax(x.next);
        x.size = size(x.previous) + size(x.next) + 1;
        return x;
    }


    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = delete(root, key);

    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.previous = delete(node.previous, key);
        } else if (cmp > 0) {
            node.next = delete(node.next, key);
        } else {
            if (node.next == null) {
                return node.previous;
            }
            if (node.previous == null) {
                return node.next;
            }
            Node t = node;
            node = min(t.next);
            node.next = deleteMin(t.next);
            node.previous = t.previous;
        }
        node.size = size(node.previous) + size(node.next) + 1;
        return node;
    }


    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.previous == null) {
            return node;
        } else {
            return min(node.previous);
        }
    }


    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return max(root).key;

    }

    private Node max(Node x) {
        if (x.next == null) {
            return x;
        } else {
            return max(x.next);
        }
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = floor(root, key);
        if (node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.previous, key);
        }
        Node t = floor(x.next, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }


    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.previous, key);
            if (t != null){
                return t;
            }
            else {
                return x;
            }
        }
        return ceiling(x.next, key);
    }



    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.previous);
        }
        else if (cmp > 0) {
            return 1 + size(x.previous) + rank(key, x.next);
        }
        else {
            return size(x.previous);
        }
    }


    public Iterable<Key> iterator() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return iterator(min(), max());
    }


    public Iterable<Key> iterator(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();

        Queue<Key> queue = new Queue<Key>();
        iterator(root, queue, lo, hi);
        return queue;
    }

    private void iterator(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            iterator(x.previous, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            iterator(x.next, queue, lo, hi);
        }
    }



}
