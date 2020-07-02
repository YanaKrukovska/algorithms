package ua.edu.ukma.ykrukovska.unit5.sorting;

import java.util.Objects;

public class Wrapper implements Comparable {

    private int value;
    private String name;

    @Override
    public int compareTo(Object o) {
        Objects.requireNonNull(o);
        if (!(o instanceof Wrapper)) {
            throw new ClassCastException();
        }
        Wrapper that = (Wrapper) o;
        return Integer.compare(this.value, that.value);
    }

    public Wrapper(String name, int value) {
        Objects.requireNonNull(name);

        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(name).append(" = ").append(value);
        return sb.toString();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
