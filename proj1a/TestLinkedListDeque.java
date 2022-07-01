import org.junit.Test;

/**
 * @author Marcus
 * @create 2022-06-07 20:04
 */
public class TestLinkedListDeque {
    @Test
    public void test() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        /*
        System.out.println(lld1.isEmpty());

        lld1.addLast(98);
        lld1.addFirst(34);
        lld1.addLast(5);
        lld1.addLast(32);
        lld1.addFirst(9);
        lld1.addLast(67);
        lld1.addLast(54);
        lld1.addLast(43);
        lld1.addFirst(79);
        lld1.addLast(78);
        lld1.addFirst(12);
        lld1.addLast(45);
        lld1.addFirst(95);
        lld1.addLast(90);
        lld1.addFirst(87);
        lld1.addLast(91);

        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeFirst());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println(lld1.removeLast());
        System.out.println("size: " + lld1.size());
        System.out.println(lld1.isEmpty());
        System.out.println(lld1.removeFirst());
        System.out.println(lld1.removeFirst());
         */
        lld1.addFirst(0);
        System.out.println(lld1.get(0));
        lld1.addFirst(2);
        lld1.addFirst(3);
        System.out.println(lld1.removeLast());
        System.out.println(lld1.removeLast());
        lld1.addLast(6);
        lld1.addFirst(7);
        lld1.addFirst(8);
        System.out.println(lld1.removeFirst());
        lld1.addFirst(10);
        lld1.addFirst(11);
        System.out.println(lld1.get(3));
        lld1.addLast(13);
        System.out.println(lld1.removeLast());
        lld1.addFirst(15);
        lld1.addFirst(16);
        System.out.println(lld1.removeLast());
    }
}
