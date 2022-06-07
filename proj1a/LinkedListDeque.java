/**
 * @author Marcus
 * @create 2022-06-06 15:02
 */
public class LinkedListDeque<T> {
    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;
        private StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    /** The first item (if it exists) is at sentinel.next. */
    private int size;
    private StuffNode sentinel;
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        StuffNode first = new StuffNode(item, sentinel, sentinel.next);
        StuffNode temp = sentinel.next;
        sentinel.next = first;
        first.prev = sentinel;
        first.next = temp;
        temp.prev = first;
        if (size == 0) {
            sentinel.prev = first;
        }
        size++;
    }
    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        StuffNode last = new StuffNode(item, sentinel.prev, sentinel);
        StuffNode temp = sentinel.prev;
        sentinel.prev = last;
        last.next = sentinel;
        last.prev = temp;
        temp.next = last;
        if (size == 0) {
            sentinel.next = last;
        }
        size++;
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
        StuffNode ptr = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        StuffNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such items exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        StuffNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }
    /** Gets the item at the given index. If no such item exists, returns null.
     * Must not alter the deque! */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        StuffNode ptr = sentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return getStuffNode(sentinel.next, index);
    }
    private T getStuffNode(StuffNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getStuffNode(node.next, index - 1);
    }
}
