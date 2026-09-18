package kz.nu.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testRandomArraysAgainstArraysSort() {
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int length = random.nextInt(1000) + 1;
            int[] expected = random.ints(length, -10000, 10000).toArray();
            int[] actual = expected.clone();

            Arrays.sort(expected);
            QuickSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void testMaxDepthOnSortedArray() {
        int n = 100000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Recursion depth exceeded limit: " + metrics.getMaxDepth() + " > " + maxAllowedDepth);
    }
}