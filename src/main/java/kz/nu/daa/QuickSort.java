package kz.nu.daa;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        sort(a, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            if (metrics != null) {
                metrics.enterRecursion();
            }

            try {
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

                if ((lt - 1 - low) < (high - (gt + 1))) {
                    sort(a, low, lt - 1, metrics);
                    low = gt + 1;
                } else {
                    sort(a, gt + 1, high, metrics);
                    high = lt - 1;
                }
            } finally {
                if (metrics != null) {
                    metrics.exitRecursion();
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}