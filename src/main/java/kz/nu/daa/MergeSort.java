package kz.nu.daa;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        int[] buffer = new int[a.length];
        sort(a, buffer, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] buffer, int left, int right, Metrics metrics) {
        if (metrics != null) {
            metrics.enterRecursion();
        }

        try {
            if (right - left + 1 <= CUTOFF) {
                insertionSort(a, left, right, metrics);
                return;
            }

            int mid = left + (right - left) / 2;
            sort(a, buffer, left, mid, metrics);
            sort(a, buffer, mid + 1, right, metrics);
            merge(a, buffer, left, mid, right, metrics);
        } finally {
            if (metrics != null) {
                metrics.exitRecursion();
            }
        }
    }

    private static void merge(int[] a, int[] buffer, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(a, left, buffer, left, right - left + 1);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = buffer[j++];
            } else if (j > right) {
                a[k] = buffer[i++];
            } else {
                if (metrics != null) {
                    metrics.incComparisons();
                }
                if (buffer[j] < buffer[i]) {
                    a[k] = buffer[j++];
                } else {
                    a[k] = buffer[i++];
                }
            }
        }
    }

    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= left) {
                if (metrics != null) {
                    metrics.incComparisons();
                }
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}