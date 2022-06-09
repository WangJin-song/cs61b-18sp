/**
 * @author Marcus
 * @create 2022-06-07 15:27
 */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private static final int INITCAPACITY = 8;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[INITCAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, nextFirst + 1, size);
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst + 1, a,
                    capacity - items.length + nextFirst + 1, items.length - nextFirst - 1);
            nextFirst += capacity - items.length;
        }
        items = a;
    }
    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        size++;
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        if (1.0 * size / items.length > 0.8) {
            resize(items.length * 2);
        }
    }
    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        if (1.0 * size / items.length > 0.8) {
            resize(items.length * 2);

        }
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, seperated by space. */
    public void printDeque() {
        if (nextFirst < nextLast){
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.println(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i ++) {
                System.out.println(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i ++) {
                System.out.println(items[i] + " ");
            }
        }
    }
    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (1.0 * size / items.length < 0.4 && items.length > 8) {
            resize(items.length / 2);
        }
        return res;
    }
    /** Removes and returns the item at the back of the deque. If no such items exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
        if (1.0 * size / items.length < 0.4 && items.length > 8) {
            resize(items.length / 2);
        }
        return res;
    }
    /** Gets the item at the given index. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }
        if (nextFirst < nextLast) {
            return items[nextFirst + index + 1];
        } else if (index < items.length - 1 - nextFirst) {
            return items[nextFirst + index + 1];
        } else {
            return items[index - items.length + 1 + nextFirst];
        }
    }
}