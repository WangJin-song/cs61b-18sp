package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] oomagesNum = new int[M];
        for (Oomage o : oomages) {
            oomagesNum[(o.hashCode() & 0x7FFFFFFF) % M]++;
        }
        for (int i = 0; i < M; i++) {
            if (oomagesNum[i] < (double) oomages.size() / 50
                    || oomagesNum[i] > (double) oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
