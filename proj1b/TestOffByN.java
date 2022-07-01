import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marcus
 * @create 2022-06-12 21:49
 */
public class TestOffByN {
    @Test
    public void testOffByN() {
        CharacterComparator offBy5 = new OffByN(5);
        assertFalse(offBy5.equalChars('a', 'c'));
        assertTrue(offBy5.equalChars('a', 'f'));
    }
}
