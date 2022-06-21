package synthesizer;

import java.util.Iterator;
/**
 * @author Marcus
 * @create 2022-06-21 14:46
 */
public interface BoundedQueue<T> extends Iterable<T> {
    // return size of the buffer
    int capacity();
    // return number of items currently in the buffer
    int fillCount();
    // add item x to the end
    void enqueue(T X);
    // delete and return item from the front
    T dequeue();
    // return (but do not delete) item from the front
    T peek();
    @Override
    Iterator<T> iterator();
    default boolean isEmpty() {
        return false;
    }
    default boolean isFull() {
        return false;
    }
}
