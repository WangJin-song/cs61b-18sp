/**
 * @author Marcus
 * @create 2022-06-07 15:27
 */
public class ArrayDeque<Item> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private Item[] items;
    private static final int INITCAPACITY = 8;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[INITCAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, nextFirst + 1, size);
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst + 1, a,
                    capacity - items.length + nextFirst + 1, items.length - nextFirst - 1);
        }
        items = a;
    }
    /** Adds an item to the front of the deque. */
    public void addFirst(Item item) {
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
    }
    /** Adds an item to the back of the deque. */
    public void addLast(Item item) {
        items[nextLast] = item;
        if (nextFirst == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
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
    public Item removeFirst() {
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        size--;
        return items[nextFirst];
    }
    /** Removes and returns the item at the back of the deque. If no such items exists, returns null. */
    public Item removeLast() {
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        size--;
        return items[nextLast];
    }
    /** Gets the item at the given index. If no such item exists, returns null. Must not alter the deque! */
    public Item get(int index) {
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
