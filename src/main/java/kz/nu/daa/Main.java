package kz.nu.daa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 5000, 10000, 50000, 100000};

        try (PrintWriter writer = new PrintWriter(new FileWriter("benchmark_results.csv"))) {
            writer.println("Algorithm,InputType,Size,Comparisons,MaxDepth,TimeMs");

            for (int size : sizes) {
                runBenchmarksForSize(size, writer);
            }
            System.out.println("Benchmarks completed! Results saved to benchmark_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmarksForSize(int size, PrintWriter writer) {
        Random rnd = new Random(42);

        int[] randomArr = rnd.ints(size, -100000, 100000).toArray();
        int[] sortedArr = randomArr.clone();
        java.util.Arrays.sort(sortedArr);
        int[] reversedArr = new int[size];
        for (int i = 0; i < size; i++) {
            reversedArr[i] = sortedArr[size - 1 - i];
        }

        testAndRecord("MergeSort", "Random", randomArr, writer);
        testAndRecord("MergeSort", "Sorted", sortedArr, writer);
        testAndRecord("MergeSort", "Reversed", reversedArr, writer);

        testAndRecord("QuickSort", "Random", randomArr, writer);
        testAndRecord("QuickSort", "Sorted", sortedArr, writer);
        testAndRecord("QuickSort", "Reversed", reversedArr, writer);
    }

    private static void testAndRecord(String algo, String inputType, int[] original, PrintWriter writer) {
        int[] data = original.clone();
        Metrics metrics = new Metrics();

        long startTime = System.currentTimeMillis();
        if ("MergeSort".equals(algo)) {
            MergeSort.sort(data, metrics);
        } else if ("QuickSort".equals(algo)) {
            QuickSort.sort(data, metrics);
        }
        long timeMs = System.currentTimeMillis() - startTime;

        writer.printf("%s,%s,%d,%d,%d,%d\n",
                algo, inputType, data.length, metrics.getComparisons(), metrics.getMaxDepth(), timeMs);
    }
}