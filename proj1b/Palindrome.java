/**
 * @author Marcus
 * @create 2022-06-12 17:41
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else if (deque.removeFirst() == deque.removeLast()) {
            return isPalindrome(newWord(word));
        } else {
            return false;
        }
    }
    private String newWord(String word) {
        return word.substring(1, word.length() - 1);
    }
    /** Return true if the word is a palindrome according to the character comparison
     * test provided by the CharacterComparator passed in as argument cc. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return isPalindrome(newWord(word), cc);
        } else {
            return false;
        }
    }
}
