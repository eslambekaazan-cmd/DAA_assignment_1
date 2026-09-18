package kz.nu.daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testRandomArraysAgainstArraysSort() {
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int length = random.nextInt(1000) + 1;
            int[] expected = random.ints(length, -10000, 10000).toArray();
            int[] actual = expected.clone();

            Arrays.sort(expected);
            MergeSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty, null);
        assertArrayEquals(new int[]{}, empty);

        int[] single = {42};
        MergeSort.sort(single, null);
        assertArrayEquals(new int[]{42}, single);

        int[] identical = {5, 5, 5, 5, 5};
        MergeSort.sort(identical, null);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, identical);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8};
        MergeSort.sort(sorted, null);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, sorted);
    }
}