package synthesizer;

/**
 * @author Marcus
 * @create 2022-06-21 15:08
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public abstract void enqueue(T x);

    @Override
    public abstract T dequeue();

    @Override
    public abstract T peek();

    @Override
    public boolean isEmpty() {
        if (fillCount == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (fillCount == capacity) {
            return true;
        }
        return false;
    }
}
