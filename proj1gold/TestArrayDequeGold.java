
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Marcus
 * @create 2022-06-13 11:01
 */
public class TestArrayDequeGold {
    @Test
    public void test() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String log = "";

        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer randomInteger = StdRandom.uniform(100);

            if (numberBetweenZeroAndOne < 0.5) {
                sad.addFirst(randomInteger);
                ads.addFirst(randomInteger);
                log += "addFirst(" + randomInteger + ")\n";
            } else {
                sad.addLast(randomInteger);
                ads.addLast(randomInteger);
                log += "addLast(" + randomInteger + ")\n";
            }
        }
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer sadRemoveNumber;
            Integer adsRemoveNumber;
            if (numberBetweenZeroAndOne < 0.5) {
                sadRemoveNumber = sad.removeFirst();
                adsRemoveNumber = ads.removeFirst();
                log += "removeFirst()\n";
                assertEquals(log, adsRemoveNumber, sadRemoveNumber);
            } else {
                sadRemoveNumber = sad.removeLast();
                adsRemoveNumber = ads.removeLast();
                log += "removeLast()\n";
                assertEquals(log, adsRemoveNumber, sadRemoveNumber);
            }
        }
    }
}
