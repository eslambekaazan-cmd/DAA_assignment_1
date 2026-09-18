package kz.nu.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    void testQuickSelectAgainstSortedArray() {
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int length = random.nextInt(1000) + 1;
            int[] original = random.ints(length, -10000, 10000).toArray();
            int[] sorted = original.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(length);
            int actual = QuickSelect.select(original.clone(), k, new Metrics());

            assertEquals(sorted[k], actual);
        }
    }
}