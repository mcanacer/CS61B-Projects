import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    
    private static final int RFACTOR = 2;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int half = (items.length / 2) + 1;
        int helper = size - half;
        int second = 0;
        for (int i = 0; i < size; i++) {
            if (i < half) {
                a[i] = items[helper];
                helper += 1;
            } else {
                a[i] = items[second];
                second += 1;
            }
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = x;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
            return;
        }
        nextFirst -= 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = x;
        size += 1;
        if (nextLast == items.length - 1) {
            nextLast = 0;
            return;
        }
        nextLast += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnedList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnedList.add(items[i]);
        }
        return returnedList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst + 1 == items.length) {nextFirst = 0;}
        T returnedItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size -= 1;
        if (size * 100 / items.length < 25 && items.length > 16) {
            resize(items.length / 2);
        }
        return returnedItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnedItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        size -= 1;
        if (size * 100 / items.length < 25 && items.length > 16) {
            resize(items.length / 2);
        }
        return returnedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[index];
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }

}