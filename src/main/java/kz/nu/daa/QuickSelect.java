package kz.nu.daa;

import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input or index k out of bounds");
        }
        return select(a, 0, a.length - 1, k, metrics);
    }

    private static int select(int[] a, int low, int high, int k, Metrics metrics) {
        while (low <= high) {
            if (low == high) {
                return a[low];
            }

            int pivotIdx = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIdx);
            int pivot = a[low];

            int lt = low;
            int gt = high;
            int i = low + 1;

            while (i <= gt) {
                if (metrics != null) {
                    metrics.incComparisons();
                }
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }

            if (k >= lt && k <= gt) {
                return a[k];
            } else if (k < lt) {
                high = lt - 1;
            } else {
                low = gt + 1;
            }
        }
        return a[k];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}