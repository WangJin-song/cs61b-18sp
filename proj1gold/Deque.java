/**
 * @author Marcus
 * @create 2022-06-12 17:26
 */
public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
