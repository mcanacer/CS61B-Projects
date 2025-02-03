import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private Node prev;
        private T item;
        private Node next;
        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }

    }

    private Node sentBack;
    private Node sentFront;
    private int size;

    public LinkedListDeque() {
        sentFront = new Node(null, null, null);
        sentBack = new Node(sentFront, null, null);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentFront.next = new Node(sentFront, x, sentFront.next);
        sentFront.next.next.prev = sentFront.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        sentBack.prev = new Node(sentBack.prev, x, sentBack);
        sentBack.prev.prev.next = sentBack.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnedList = new ArrayList<>();
        Node p = sentFront.next;
        while (p.item != null) {
            returnedList.add(p.item);
            p = p.next;
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
        T returnedItem = sentFront.next.item;
        sentFront.next = sentFront.next.next;
        sentFront.next.prev = sentFront;
        size -= 1;
        return returnedItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnedItem = sentBack.prev.item;
        sentBack.prev = sentBack.prev.prev;
        sentBack.prev.next = null;
        size -= 1;
        return returnedItem;
    }

    @Override
    public T get(int index) {
        Node n = sentFront.next;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.item;
    }

    private T helperFunc(Node n, int i) {
        if (i < 0 || size <= i) {
            return null;
        }
        if (i == 0) {
            return n.item;
        }
        return helperFunc(n.next, i - 1);
    }

    @Override
    public T getRecursive(int index) {
        return helperFunc(sentFront.next, index);
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1);
        lld.addLast(3);
        lld.addLast(5);
        lld.removeLast();
    }

}