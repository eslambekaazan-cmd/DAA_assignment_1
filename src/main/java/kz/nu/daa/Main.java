package kz.nu.daa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] inputTypes = {"random", "sorted", "duplicates"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (int size : sizes) {
                for (String inputType : inputTypes) {
                    runBenchmarkSuite(writer, "MergeSort", inputType, size);
                    runBenchmarkSuite(writer, "QuickSort", inputType, size);
                    runBenchmarkSuite(writer, "QuickSelect", inputType, size);
                }
            }
            System.out.println("Benchmarks completed! Results saved to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmarkSuite(PrintWriter writer, String algo, String inputType, int size) {
        long[] times = new long[5];
        long[] comparisons = new long[5];
        long[] depths = new long[5];

        for (int run = 0; run < 5; run++) {
            int[] data = generateInput(inputType, size, run);
            Metrics metrics = new Metrics();

            long startTime = System.nanoTime();
            if ("MergeSort".equals(algo)) {
                MergeSort.sort(data, metrics);
            } else if ("QuickSort".equals(algo)) {
                QuickSort.sort(data, metrics);
            } else if ("QuickSelect".equals(algo)) {
                QuickSelect.select(data, size / 2, metrics);
            }
            long endTime = System.nanoTime();

            times[run] = (endTime - startTime) / 1_000_000;
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }


        Arrays.sort(times);
        Arrays.sort(comparisons);
        Arrays.sort(depths);


        long medianTime = times[2];
        long medianComp = comparisons[2];
        long medianDepth = depths[2];

        writer.printf("%s,%s,%d,%d,%d,%d\n", algo, inputType, size, medianTime, medianComp, medianDepth);
    }

    private static int[] generateInput(String type, int size, int seed) {
        Random rnd = new Random(42 + seed);
        int[] arr = new int[size];

        if ("random".equals(type)) {
            for (int i = 0; i < size; i++) arr[i] = rnd.nextInt();
        } else if ("sorted".equals(type)) {
            for (int i = 0; i < size; i++) arr[i] = i;
        } else if ("duplicates".equals(type)) {
            for (int i = 0; i < size; i++) arr[i] = rnd.nextInt(10); // значения строго от 0 до 9
        }

        return arr;
    }
}